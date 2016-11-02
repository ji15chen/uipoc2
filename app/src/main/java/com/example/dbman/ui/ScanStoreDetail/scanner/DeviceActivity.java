package com.example.dbman.ui.ScanStoreDetail.scanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.db.model.StoreInfoModel;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;

/**
 * @author 胡小海
 * 程序加载入口(选项卡窗体)
 */
public abstract class DeviceActivity extends AbstractUIStateBindingActivity {
	// 调试信息
    private static final String TAG = "设备查找界面";
    private static final boolean D = false;
    
    //成员字段
    private BluetoothAdapter mBtAdapter=null;   //蓝牙适配器
    private BluetoothService mBtService = null;     //蓝牙服务器
//    private ArrayAdapter<String> mPairedDevicesArrayAdapter;  //配对过的设备列表
//    private ArrayAdapter<String> mNewDevicesArrayAdapter;  //新找到的设备列表
    
    private static final int REQUEST_ENABLE_BT = 3;  //打开蓝牙设备
    
    public Handler messageHandler;    //消息对象

	protected abstract void onNewStoreDetailFound(final String epc, final StoreInfoModelEntry entry);
	//保持屏幕常亮
	private PowerManager powerManager = null; 
	private WakeLock wakeLock = null; 
	
	//EPC显示的列表
//    private ArrayList<HashMap<String,Object>> listRturnEPC = new ArrayList<HashMap<String,Object>>();//数据返回
	HashMap<String, StoreInfoModelEntry> hashMapEPC = new HashMap<>();
    private String LastString="";//剩余的字符
    private String StrUnThink;//记忆上次没有解析完成的数据
    private Boolean  IsUseXY=true;

	
	/** 当界面初次加载事执行 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Looper looper = Looper.myLooper();   
        messageHandler = new MessageHandler(looper);  //消息处理
        //得到本地蓝牙适配器
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        //如果获得的蓝牙适配器为空则可能是手机不支持。
        if ((mBtAdapter == null)||( mBtAdapter.getBondedDevices().size() <=0))
        {
            Toast.makeText(this, "蓝牙没有打开！！！", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        //屏幕常亮控制
        this.powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);  
        this.wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock"); 

    }
    
    /**
     * 界面开始运行
     */
    @Override
    public void onStart() {
        super.onStart();
        if(D) LogUtils.e(TAG, "++ ON START ++");
        if (!mBtAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } 
        else 
        {
            if (mBtService == null) 
            {
            	mBtService = new BluetoothService(this, messageHandler);
            	messageHandler.obtainMessage(0,"初始化蓝牙服务器！").sendToTarget();
            }
        }
		BluetoothDevice device = null;
		for (BluetoothDevice dev: mBtAdapter.getBondedDevices()){
			device = dev;
			break;
		}
		connectDevice(device.getAddress(),false);
    }
    
    /**
     * 界面获得焦点检测蓝牙服务器状态开始打开蓝牙服务器
     */
     @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) LogUtils.e(TAG, "+ ON RESUME +");
        this.wakeLock.acquire();  
        messageHandler.obtainMessage(0,"进入- ON RESUME -方法！").sendToTarget();
        if (mBtService != null) {
            if (mBtService.getState() == BluetoothService.STATE_NONE) {
              messageHandler.obtainMessage(0,"开启蓝牙服务器！").sendToTarget();
              mBtService.start();
              
            }
        }
    }
     
     @Override  
     protected void onPause() {  
         super.onPause();  
         this.wakeLock.release();  
     } 

    /**
     * 其他界面传递到本界面数据分析
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
        case REQUEST_ENABLE_BT:
        	//执行蓝牙打开后的操作
        	if (!mBtAdapter.isEnabled()) 
        	{
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            } else {
                if (mBtService == null) 
                {
                	mBtService = new BluetoothService(this, messageHandler);
                }
            }
        	break;
        	//messageHandler.obtainMessage(0,"打开蓝牙！").sendToTarget();
        default: 
        	
        }
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
        	if(D) LogUtils.e(TAG,"尝试连接设备失败！！");
        }
    }
      	
    /** 
     * 程序关闭时
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(D) LogUtils.e(TAG,"程序触发关闭（onDestroy）！");
        // 关闭查找
        if (mBtAdapter != null) {mBtAdapter.cancelDiscovery(); }
        // 注销广播
        if (mBtService != null) mBtService.stop();
    }

    /**
     * 开始设备查找
     */
    private void doDiscovery() {
        if (D) LogUtils.d(TAG, "触发设备查找(doDiscovery())");
        //如果正在查找则关闭
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
        //返回查找结果
        mBtAdapter.startDiscovery();
    }


	/**
	 * 构建一个对话框
	 * @param context
	 * @return  
	 */
	private ProgressDialog buildProgressDialog(Context context,int Retid,String Title,String Messge) 
	{
		// 创建ProgressDialog对象
		ProgressDialog xh_pDialog = new ProgressDialog(context);
		// 设置进度条风格，风格为圆形，旋转的
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置ProgressDialog 标题
		xh_pDialog.setTitle(Title);
		// 设置ProgressDialog提示信息
		xh_pDialog.setMessage(Messge);
		// 设置ProgressDialog标题图标
		xh_pDialog.setIcon(Retid);
		// 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
		xh_pDialog.setIndeterminate(false);
		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(true);
		// 让ProgressDialog显示
		return xh_pDialog; 
	}
	
	
	   /**
	    * EPC数据添加
	    * @param msg EPC数据
	    */
	    private void addItemepc(String msg) 
	    {
			if (!hashMapEPC.containsKey(msg)){
				LogUtils.d("EPC:"+msg);
				try {
					String query = StoreInfoModel.buildEquipCardQuery(msg);
					StoreInfoModel model =  StoreInfoModel.loadEquipStoreDetail(query);
					List<StoreInfoModelEntry> data = model.getLstEquipStoreInfo();
					if (data.size() <= 0 ){
						Toast.makeText(this,"查询无此数据",Toast.LENGTH_SHORT).show();
					}else{
						StoreInfoModelEntry entry = data.get(0);
						hashMapEPC.put(msg,entry);
						entry.setEpc(msg);
						onNewStoreDetailFound(msg, entry);
					}
				}catch (Exception e){
					e.printStackTrace();
				}

			}

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
					if (D) LogUtils.d(TAG, MyMershId);
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
					if (D) LogUtils.d(TAG, MyCRC);
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
					if (D) LogUtils.d(TAG, "指令解析出现错误：："+e.toString());
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
		                    crcdata = Integer.toHexString((int)Crc8005.GetCRC16(bytedata, bytedata.length));//计算CRC
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
					if (D) LogUtils.d(TAG, "数据处理出现故障："+e.toString());
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
		    	    	   SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    	    	   if(D) LogUtils.e(TAG,DateTimeFormat.format(new Date()) + ":获得数据");
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
	    	    		   if (D) LogUtils.d(TAG, "更新界面数据显示出现错误："+e.toString());
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
	            	  if(D) LogUtils.e(TAG, (String) msg.obj);
    	          }
    	      }   
     }

}
