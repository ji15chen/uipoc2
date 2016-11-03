package com.example.dbman.ui.core.ui_state;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class UIState implements Serializable,Cloneable{
    private Class uiClass;
    private String name;

    private byte [] rawModelData;
    private byte [] jpegSnapshot;//
    private long timestamp;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UIState(){
        setTimestamp(System.currentTimeMillis());
    }

    public Class getUiClass() {
        return uiClass;
    }

    public void setUiClass(Class uiClass) {
        this.uiClass = uiClass;
    }

//    public Class getUiModelClass() {
//        return uiModelClass;
//    }
//
//    public void setUiModelClass(Class uiModelClass) {
//        this.uiModelClass = uiModelClass;
//    }

    public byte[] getRawModelData() {
        return rawModelData;
    }

    public void setRawModelData(byte[] rawModelData) {
        this.rawModelData = rawModelData;
    }




    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getJpegSnapshot() {
        return jpegSnapshot;
    }

    public void setJpegSnapshot(byte[] jpegSnapshot) {
        this.jpegSnapshot = jpegSnapshot;
    }

    public String toString(){
        return getName()+":"+getTimestamp();
    }
}
