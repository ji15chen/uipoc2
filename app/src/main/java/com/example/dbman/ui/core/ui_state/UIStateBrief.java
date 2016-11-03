package com.example.dbman.ui.core.ui_state;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.dbman.core.Utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jerry on 2016/10/26.
 */

public class UIStateBrief {
    private static final String dtPattern = "yyyy-MM-dd HH:mm:ss";

    private Class activityClass;

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }

    private String createTime;
    private String briefName;
    private Bitmap snapshot;
    private Object modelData;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getModelData() {
        return modelData;
    }

    public void setModelData(Object modelData) {
        this.modelData = modelData;
    }

    public UIStateBrief(final UIState uiState){
        setActivityClass(uiState.getUiClass());
        setCreateTime(uiState.getTimestamp());
        setBriefName(uiState.getName());
        setSnapshot(Utils.Bytes2Bimap(uiState.getJpegSnapshot()));
        setModelData( Utils.toObject(uiState.getRawModelData()));
        setId(uiState.toString());
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setCreateTime(long time){
        SimpleDateFormat format=new SimpleDateFormat(dtPattern);
        Date d1=new Date(time);
        String t1=format.format(d1);
        setCreateTime(t1);
    }
    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public Bitmap getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Bitmap snapshot) {
        this.snapshot = snapshot;
    }

    public static Bundle createBundleFromStateBrief(UIStateBrief stateBrief){
        Bundle bundle = new Bundle();
        bundle.putSerializable(UIStateManager.LAST_UI_STATE, (Serializable) stateBrief.getModelData());
        return bundle;
    }
}
