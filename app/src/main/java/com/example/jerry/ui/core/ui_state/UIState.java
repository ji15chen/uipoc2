package com.example.jerry.ui.core.ui_state;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class UIState implements Serializable{
    private Class uiClass;
    private Class uiModelClass;
    private byte [] rawModelData;
    private long timestamp;

    public Class getUiClass() {
        return uiClass;
    }

    public void setUiClass(Class uiClass) {
        this.uiClass = uiClass;
    }

    public Class getUiModelClass() {
        return uiModelClass;
    }

    public void setUiModelClass(Class uiModelClass) {
        this.uiModelClass = uiModelClass;
    }

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
}
