package com.example.dbman.core.scanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.ui.R;


public class ScanneDevice  {
    
	// 调试信息
    private static final String TAG = "设备查找界面(SageznDeviceActivity)";
    private static final boolean D = false;
    
    //成员字段
    private BluetoothAdapter mBtAdapter=null;   //蓝牙适配器
    private BluetoothService mBtService = null;     //蓝牙服务器
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;  //配对过的设备列表
    private ArrayAdapter<String> mNewDevicesArrayAdapter;  //新找到的设备列表
    private Context mContext;
    private static final int REQUEST_ENABLE_BT = 3;  //打开蓝牙设备
    
    public Handler messageHandler;    //消息对象

	//保持屏幕常亮
	private PowerManager powerManager = null; 
	private WakeLock wakeLock = null; 
	
	//EPC显示的列表
    private ArrayList<HashMap<String,Object>> listRturnEPC = new ArrayList<HashMap<String,Object>>();//数据返回
    private static int ADD_ITEM_ID = 1;  //序号
    private String LastString="";//剩余的字符
    private String StrUnThink;//记忆上次没有解析完成的数据
    private Boolean  IsUseXY=true;
    
	//2次按返回退出程序
	 private static Boolean isExit = false;
	 private static Boolean hasTask = false;
	 private Timer tExit = new Timer();
	 private TimerTask task = new TimerTask() 
	    {
	        @Override
	        public void run() {
	            isExit = false;
	            hasTask = true;
	        }
	  };

    private BluetoothDevice getFirstDevice(BluetoothAdapter adapter){
        BluetoothDevice device = null;
       Set<BluetoothDevice> adapters =  adapter.getBondedDevices();
        for (BluetoothDevice dev:adapters){
            device = dev;
        }
        return device;
    }

    public ScanneDevice(final Context context){
        mContext = context;

        Looper looper = Looper.myLooper();
        messageHandler = new MessageHandler(looper);  //消息处理
        //得到本地蓝牙适配器
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    public boolean start(){

        if (!mBtAdapter.isEnabled()){
            return false;
        }

        if (mBtService == null)
        {
            mBtService = new BluetoothService(mContext, messageHandler);
            messageHandler.obtainMessage(0,"初始化蓝牙服务器！").sendToTarget();
        }
        BluetoothDevice device = getFirstDevice(mBtAdapter);
        if (device == null){
            return false;
        }

        connectDevice(device.getAddress(),false);
        return true;
    }

	/**
	 * 连接蓝牙设备
	 * @param address 设备MAC地址
	 * @param secure 设备连接模式
	 */
    private void connectDevice(String address, boolean secure) {

        BluetoothDevice device = mBtAdapter.getRemoteDevice(address);
        // 尝试连接到设备
        if(device!=null)
        {
        	mBtService.connect(device, secure);
        }
        else
        {
        	LogUtils.d(TAG,"尝试连接设备失败！！");
        }
    }
      	
	public void stop(){
		// 关闭查找
		if (mBtAdapter != null) {mBtAdapter.cancelDiscovery(); }
		// 注销广播
		mContext.unregisterReceiver(mReceiver);
		if (mBtService != null) mBtService.stop();
	}

    /**
     * 开始设备查找
     */
    private void doDiscovery() {
        //如果正在查找则关闭
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
        //返回查找结果
        mBtAdapter.startDiscovery();
    }


    /**
     * 广播消息处理
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 当超找到一个设备时
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 从 Intent中获得蓝牙适配器
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 如果它已经配对，跳过它，因为它被列入
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            // 当查找结束更改标题
            } 
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) 
            {
            	LogUtils.d("蓝牙查找结束");
            }
        }
    };

	   /**
	    * EPC数据添加
	    * @param msg EPC数据
	    */
	    private void addItemepc(String msg) 
	    {   
	    	int ADD_ITEM = 1;
	    	HashMap<String,Object> item = new HashMap<String,Object>(); 
	    	for(int i=0;i<listRturnEPC.size();i++)
			{
					String textnum=listRturnEPC.get(i).get("textnum").toString();
					String textepc=listRturnEPC.get(i).get("textepc").toString();
					if(msg.equals(textepc))
					{
						textnum=Integer.toString((Integer.valueOf(textnum)+1));
						listRturnEPC.get(i).put("textnum", textnum);  //读到的数量加1
						//Notes.notifyDataSetChanged();
						return;
					}
			}
	    	item.put( "textid","序号："+ADD_ITEM_ID );   
	    	item.put( "textnum",ADD_ITEM );
	    	item.put( "textepc",msg );
            LogUtils.d(item);
	    	listRturnEPC.add( item ); 
	    	//Notes.notifyDataSetChanged();
	    	ADD_ITEM_ID++;
	    	//slistViewShow.setSelection(listRturnEPC.size() - 1);
	    }
	    
	    
	    /**
	     * 数据分析通过蓝牙获得的数据进行拆分（96位标签）
	     * @param msg
	     */
	    private void ListOfEpc(String msg) 
	    { 
	    	msg=LastString+msg;
	    	int LenMag=msg.length();
	    	int NumEpc=LenMag/24;
	    	int LastNum=LenMag%24;
	    	
	    	for (int EpcNumTemp = 0; EpcNumTemp < NumEpc; EpcNumTemp++)    //根据EPC个数获取EPC
            {
                String EpcDataStr = msg.substring((24* EpcNumTemp),(24* (EpcNumTemp+1)));
                addItemepc(EpcDataStr);
            }
	    	LastString=msg.substring((LenMag-LastNum),LenMag);
	    }
	    

	    /**
	     * StrUnThink访问器
	     * @return
	     */
		public String getStrUnThink() {
			return StrUnThink;
		}
		
		/**
		 * 设置反馈剩余
		 * @param strUnThink
		 */
		public void setStrUnThink(String strUnThink) {
			StrUnThink = strUnThink;
		}
		
		
		/**
	  	 * 16进制的字符串表示转成字节数组
	  	 * @param hexString    16进制格式的字符串
	  	 * @return 转换后的字节数组
	  	 **/
	  	public static byte[] toByteArray(String hexString) {
	  		if (hexString.length()<1)
	  			throw new IllegalArgumentException("要转换的字符不能为空！！");
	     		hexString = hexString.toLowerCase();
	  		final byte[] byteArray = new byte[hexString.length() / 2];
	  		int k = 0;
	  		for (int i = 0; i < byteArray.length; i++) {
	                          //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
	  			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
	  			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
	  			byteArray[i] = (byte) (high << 4 | low);
	  			k += 2;
	  		}
	  		return byteArray;
	  	}
	    
	    
		/**
		 * 开始解析数据(数据解析方法 )
		 * @param StrToThink 要解析的数据
		 */
		public void ThinkData(String StrToThink)
		{
			String MyStrToThink=StrUnThink+StrToThink;
			StrUnThink="";
			ArrayList<String> getstrdo=new ArrayList<String>(); //用于保存解析出来的每条指令
			while(MyStrToThink.length()>0)
			{
				try{
				int LenMyStrToThink =MyStrToThink.length();
				int FristAA53=MyStrToThink.indexOf("AA53");
				int DataLen=0;
				
				//获得数据头
				if(FristAA53>=0&&(LenMyStrToThink - FristAA53 - 8) >= 0)
				{
					String MyMershId = MyStrToThink.substring(FristAA53+4, FristAA53+8);	
					LogUtils.d(TAG, MyMershId);
				}
				else
				{
					this.setStrUnThink(MyStrToThink);//保存到没有解析的字符中
					break;
				}
				
				//获得数据长度
				if ((LenMyStrToThink - FristAA53 - 12) >= 0) 
				{  
					DataLen = Integer.valueOf(MyStrToThink.substring(FristAA53 + 8, FristAA53+12), 16)*2; 
				}    
	            else 
	            { 
	            	this.setStrUnThink(MyStrToThink);//保存到没有解析的字符中
	            	break; 
	            }
				
				//获得CRC
				if ((LenMyStrToThink - FristAA53 - 16 - DataLen) >= 0)
				{
					String MyCRC = MyStrToThink.substring(FristAA53 + 12 + DataLen, FristAA53 + 16 + DataLen);
					LogUtils.d( MyCRC);
				}    
				else 
	            { 
	            	this.setStrUnThink(MyStrToThink);//保存到没有解析的字符中
	            	break; 
	            }
				
				//一个完整指令处理完后从呆处理数据中移除内容
				int MyOrderLen = FristAA53 + DataLen + 16;     //拼装出一个反馈指令所消耗整个字符串长度
				getstrdo.add(MyStrToThink.substring(FristAA53, MyOrderLen));//保存此条完整针
				MyStrToThink = MyStrToThink.substring(MyOrderLen, LenMyStrToThink); //处理完成后所剩下的字符
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					MyStrToThink="";
				}
			}
			
			//对每条指令进行具体解析处理
			for (int MunInt = 0; MunInt < getstrdo.size(); MunInt++)//历遍所有返回的指令
	        {
				try{
					String AllorderStr = "";
		            String CRC_DATA = "";
		            String crcdata ="";
		            if (getstrdo.get(MunInt).trim()!= "")
		            {
		                AllorderStr = getstrdo.get(MunInt);
		                byte[] bytedata = toByteArray(AllorderStr);
		                CRC_DATA = AllorderStr.substring(AllorderStr.length() - 4, AllorderStr.length());
		                if (CRC_DATA.equals("6666"))  //CRC为6666是特例情况，直接通过
		                {
		                    crcdata = "0";
		                }
		                else
		                {
		                    crcdata = Integer.toHexString((int) Crc8005.GetCRC16(bytedata, bytedata.length));//计算CRC
		                }
		            }
		            if (crcdata.equals("0")&& AllorderStr.length() > 0) //计算获得CRC正确，说明反馈命令是正确的
		            {
		            	String RturnOrderID=AllorderStr.substring(12, 14); //获得指令码
		                if (RturnOrderID.equals("FF") )  //（FF）为EPC数据反馈
		                {
		                    int EpcLenStr = Integer.valueOf( AllorderStr.substring(14, 16),16);  //Epc数据长度
		                    int EpcNumInt = (AllorderStr.length() - 20) / (EpcLenStr * 4); //Epc个数
		                    for (int EpcNumTemp = 0; EpcNumTemp < EpcNumInt; EpcNumTemp++)    //根据EPC个数获取EPC
		                    {
		                        String EpcDataStr = AllorderStr.substring((16+ EpcLenStr * 4 * EpcNumTemp),(16+ EpcLenStr * 4 * (EpcNumTemp+1)));
		                        addItemepc(EpcDataStr);
		                    }
		                    continue;
		                }
		            }
		        }
				catch (Exception e) 
				{
					e.printStackTrace();
					continue;
				}
	        }
		}
		

    /**
     * @author 胡小海
     *  数据消息处理方法
     */
    public class MessageHandler extends Handler 
    	{   
    	      public MessageHandler(Looper looper) 
    	      {   
    	    	  super(looper);   
    	      }   
    	      @Override  
    	      public void handleMessage(Message msg) 
    	      {   
    	    	  //处理消息
    	    	 switch (msg.what) 
    	          {
	    	       case MessageType.MESSAGE_READ:  //读到数据
	    	    	   try
	    	    	   {
		    	    	   //SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    	    	   String MyEpcData=(String)msg.obj;

		    	    	   if(IsUseXY)
		    	    	   {
		    	    		   ThinkData(MyEpcData);
		    	    	   }
		    	    	   else
		    	    	   {
		    	    		   ListOfEpc(MyEpcData);
		    	    	   }
	    	    	   }
	    	    	   catch (Exception e) 
	   				   {
	    	    		   e.printStackTrace();
	   				   }
	    	    	   break;
 	               case MessageType.Msg_Hard:
	                   break;
	               case MessageType.Msg_ReadEpc:
	            	   break;
	               case MessageType.Msg_Do:
	            	  break;
	               case MessageType.Msg_ALL:
	            	  break;
	               case MessageType.Msg_OK:
	            	  break;
	               case MessageType.Msg_Other:
	            	  break;
	               default:
                       break;
    	          }
    	      }   
     }

}
