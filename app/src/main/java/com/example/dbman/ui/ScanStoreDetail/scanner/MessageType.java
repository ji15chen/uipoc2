package com.example.dbman.ui.ScanStoreDetail.scanner;

public final class MessageType 
{
    //��BluetoothService��������͵���Ϣ����
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    
	// ���弸����Ϣ
    public static final int Msg_Hard = 10;   //����������Ϣ       
    public static final int Msg_ReadEpc = 20;      //����������EPC��Ϣ
    public static final int Msg_Do = 30;   //������ָͨ����Ϣ
    public static final int Msg_Err = 40;   //������ָͨ����Ϣ
    public static final int Msg_ALL = 50;   //����ȫ��ָ��
    public static final int Msg_OK = 60;   //�ɹ������豸
    public static final int Msg_Other = 70;   //����ʧ��
    
}
