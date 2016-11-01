package com.example.dbman.ui.ScanStoreDetail.scanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 这个类的所有工作，建立和管理蓝牙
 *与其他设备的连接。它有一个线程监听
 *传入连接，一个设备连接线程，和
 *当连接进行数据传输线程。
 */
public class BluetoothService {
    // 调试输出信息
    private static final String TAG = "BluetoothService";
    private static final boolean D = true;

    // 蓝牙连接服务器类型
    private static final String NAME_SECURE = "蓝牙安全连接";
    private static final String NAME_INSECURE = "蓝牙不安全连接";

    //此应用程序的唯一的UUID  
    //#蓝牙串口服务
    //SerialPortServiceClass_UUID = ‘{00001101-0000-1000-8000-00805F9B34FB}’
    //LANAccessUsingPPPServiceClass_UUID = ‘{00001102-0000-1000-8000-00805F9B34FB}’
    private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID MY_UUID_INSECURE =UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // 成员字段
    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private AcceptThread mSecureAcceptThread;
    private AcceptThread mInsecureAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;    //服务器当前状态

    // 表明当前连接状态的常量
    public static final int STATE_NONE = 0;       // 什么都没做
    public static final int STATE_LISTEN = 1;     // 监听连接
    public static final int STATE_CONNECTING = 2; // 发起一个连接
    public static final int STATE_CONNECTED = 3;  // 现在连接到远程设备

    
    // 从BluetoothService处理程序发送的消息类型
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    
    
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
  

    /**
     * 构造一个全新的蓝牙通讯服务
     * @param context  界面层上下文
     * @param handler  一个handler将消息发送回用户界面
     */
    public BluetoothService(Context context, Handler handler) {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        mHandler = handler;
    }

    /**
     * 设置连接的当前状态
     * @param state  当前连接状态
     */
    private synchronized void setState(int state) {
        if (D) Log.d(TAG, "setState() " + mState + " -> " + state);
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "服务器状态："+state+ " -> " + state).sendToTarget();
        mState = state;
    }

    /**
     * 返回当前的连接状态. 
     */
    public synchronized int getState() {
        return mState;
    }

    /**
     * 开始蓝牙服务。具体来说开始AcceptThread开始
     * 会议在听取（服务器）模式。由onResume（）激活 
     */
    public synchronized void start() {
        if (D) Log.d(TAG, "start");

        // 取消任何试图进行连接的线程
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // 取消任何线程正在运行的连接
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        setState(STATE_LISTEN);

        // 启动监听线程
        if (mSecureAcceptThread == null) {
            mSecureAcceptThread = new AcceptThread(true);
            mSecureAcceptThread.start();
        }
        if (mInsecureAcceptThread == null) {
            mInsecureAcceptThread = new AcceptThread(false);
            mInsecureAcceptThread.start();
        }
    }

    /**
     * 启动ConnectThread发起一个连接到远程设备。
     * @param device 连接的蓝牙设备
     * @param secure 连接安全类型 - Secure (true) , Insecure (false)
     */
    public synchronized void connect(BluetoothDevice device, boolean secure) {
        if (D) Log.d(TAG, "connect to: " + device);
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "连接设备："+device.getAddress()).sendToTarget();
        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) 
        {
            if (mConnectThread != null) 
            {mConnectThread.cancel(); mConnectThread = null;}
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) 
        {
        	mConnectedThread.cancel(); 
        	mConnectedThread = null;
        }

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device, secure);
        mConnectThread.start();
        
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "成功开启连接设备线程！").sendToTarget();
        setState(STATE_CONNECTING);
    }

    /**
     * 开始ConnectedThread开始一个蓝牙连接
     * @param socket  连接上的BluetoothSocket
     * @param device  已连接的BluetoothDevice
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice
            device, final String socketType) {
        if (D) Log.d(TAG, "connected, Socket Type:" + socketType);
        //mHandler.obtainMessage(OtherServerActivity.MESSAGE_STATE_CHANGE, "连接成功, Socket Type:" + socketType).sendToTarget();
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Cancel the accept thread because we only want to connect to one device
        if (mSecureAcceptThread != null) {
            mSecureAcceptThread.cancel();
            mSecureAcceptThread = null;
        }
        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread.cancel();
            mInsecureAcceptThread = null;
        }

        // 开始线程管理连接和执行的线程
        mConnectedThread = new ConnectedThread(socket, socketType);
        mConnectedThread.start();

        mHandler.obtainMessage(MessageType.Msg_OK,device.getName()+"[" +device.getAddress()+"]").sendToTarget();//连接上设备
        mHandler.obtainMessage(0, "成功连接上设备:" + device.getName()).sendToTarget();
        setState(STATE_CONNECTED);
    }

    /**
     * 停止所有线程
     */
    public synchronized void stop() {
        if (D) Log.d(TAG, "stop");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        if (mSecureAcceptThread != null) {
            mSecureAcceptThread.cancel();
            mSecureAcceptThread = null;
        }

        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread.cancel();
            mInsecureAcceptThread = null;
        }
        setState(STATE_NONE);
    }

    /**
     * 在非同步的方式ConnectedThread写数据
     * @param out 数据
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    /**
     * 连接失败通知界面
     */
    private void connectionFailed() {
        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TOAST, "Unable to connect device");
        msg.setData(bundle);
        mHandler.obtainMessage(MessageType.Msg_Other,"连接失败！" ).sendToTarget();//连接上设备
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "无法连接到设备！" ).sendToTarget();
        BluetoothService.this.start();
    }

    /**
     * 连接已断开通知界面
     */
    private void connectionLost() {
        mHandler.obtainMessage(0, "与设备之间的连接已断开！" ).sendToTarget();
        BluetoothService.this.start();
    }

    /**
     * 监听传入连接
     */
    private class AcceptThread extends Thread {
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;
        private String mSocketType;
        public AcceptThread(boolean secure) {
            BluetoothServerSocket tmp = null;
            mSocketType = secure ? "Secure":"Insecure";
            try {
                if (secure) 
                {
                    tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_SECURE,MY_UUID_SECURE);
                } 
                else 
                {
                    tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_INSECURE, MY_UUID_INSECURE);
                }
            } catch (IOException e) {
                Log.e(TAG, "Socket Type: " + mSocketType + "listen() failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            if (D) Log.d(TAG, "Socket Type: " + mSocketType +
                    "BEGIN mAcceptThread" + this);
            setName("AcceptThread" + mSocketType);

            BluetoothSocket socket = null;

            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "Socket Type: " + mSocketType + "accept() failed", e);
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (BluetoothService.this) {
                        switch (mState) {
                        case STATE_LISTEN:
                        case STATE_CONNECTING:
                            // Situation normal. Start the connected thread.
                            connected(socket, socket.getRemoteDevice(),mSocketType);
                            break;
                        case STATE_NONE:
                        case STATE_CONNECTED:
                            // Either not ready or already connected. Terminate new socket.
                            try {
                                socket.close();
                            } catch (IOException e) {
                                Log.e(TAG, "Could not close unwanted socket", e);
                            }
                            break;
                        }
                    }
                }
            }
            if (D) Log.i(TAG, "END mAcceptThread, socket Type: " + mSocketType);

        }

        public void cancel() {
            if (D) Log.d(TAG, "Socket Type" + mSocketType + "cancel " + this);
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Socket Type" + mSocketType + "close() of server failed", e);
            }
        }
    }


    /**
     * 连接设备线程
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private String mSocketType;

        public ConnectThread(BluetoothDevice device, boolean secure) {
            mmDevice = device;
            BluetoothSocket tmp = null;
            mSocketType = secure ? "Secure" : "Insecure";
            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                if (secure) 
                {
                    tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
                } 
                else 
                {
                    tmp = device.createRfcommSocketToServiceRecord(MY_UUID_INSECURE);
                }
            } 
            catch (IOException e) {
                Log.e(TAG, "Socket Type: " + mSocketType + "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectThread SocketType:" + mSocketType);
            setName("ConnectThread" + mSocketType);
            mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "开始 mConnectThread SocketType:" + mSocketType).sendToTarget();
            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();
            
            try {           	
                mmSocket.connect();
            } 
            catch (IOException e) 
            {
                // Close the socket
            	 mHandler.obtainMessage(MESSAGE_STATE_CHANGE, "打开蓝牙连接出现异常！" ).sendToTarget();
                try {
                    mmSocket.close();
                } 
                catch (IOException e2) {
                    Log.e(TAG, "unable to close() " + mSocketType +
                            " socket during connection failure", e2);
                }
                connectionFailed();
                return;
            }
            synchronized (BluetoothService.this) 
            {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice, mSocketType);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect " + mSocketType + " socket failed", e);
            }
        }
    }

    
    /**
     * 数据转换所需
     */
    private static final byte[] HEX_CHAR_TABLE = {
        (byte)'0', (byte)'1', (byte)'2', (byte)'3',
        (byte)'4', (byte)'5', (byte)'6', (byte)'7',
        (byte)'8', (byte)'9', (byte)'A', (byte)'B',
        (byte)'C', (byte)'D', (byte)'E', (byte)'F'
      };
    
    /**
     * 将BYTE转换成HEX
     * @param raw 数据
     * @return HEX字符串
     * @throws UnsupportedEncodingException
     */
    public static String getHexString(byte[] raw) 
      throws UnsupportedEncodingException 
      {
    	  
    	  if (raw == null || raw.length < 1)
    	  {
    		  return new String("FF");
    	  }
   	  
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
          int v = b & 0xFF;
          hex[index++] = HEX_CHAR_TABLE[v >>> 4];
          hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, "ASCII");
      }
    
    
	/**
     * 一个设备连接的监听与发送线程
     * 同时也负责所有的收发传输。
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        /**
         * 构造
         * @param socket 连接
         * @param socketType 连接类型
         */
        public ConnectedThread(BluetoothSocket socket, String socketType) {
            Log.d(TAG, "create ConnectedThread: " + socketType);
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        /**
         * 接受数据监听
         * 这里会返回一个消息给主界面，有数据获得
         */
        public void run() {
            Log.i(TAG, "开启数据接受线程");
            byte[] buffer ;
            // 持续监听数据
            while (true) {
            	buffer = new byte[1024];
                try 
                {
                    //从 InputStream 中获取数据
                	int numReadedBytes = mmInStream.read(buffer, 0, buffer.length); 
                    byte[] byteGet= new byte[numReadedBytes]; 
                    System.arraycopy(buffer, 0, byteGet, 0, byteGet.length); 
                    mHandler.obtainMessage(MessageType.MESSAGE_READ, byteGet.length, -1, (Object)getHexString(byteGet)).sendToTarget();
                } 
                catch (IOException e) 
                {
                    Log.e(TAG, "连接失败！！", e);
                    connectionLost();
                    BluetoothService.this.start();
                    break;
                }
            }
        }

        /**
         * 发送数据入口
         * @param buffer  要发送的bytes数据
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);
            } catch (IOException e) 
            {
                Log.e(TAG, "Exception during write", e);
                mHandler.obtainMessage(0, "发送数据失败！！！").sendToTarget();
            }
        }
       /**
        * 关闭线程时执行
        * */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}