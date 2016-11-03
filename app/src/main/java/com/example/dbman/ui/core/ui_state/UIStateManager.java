package com.example.dbman.ui.core.ui_state;

import android.content.Intent;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseApplication;

import java.io.Serializable;
import java.util.List;

import com.snappydb.DB;
import com.snappydb.KeyIterator;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class UIStateManager {
    private static final String BUNDLE_UI_STATE=".BUNDLE_UI_STATE";
    private static final int BATCH_SIZE=16;
    public static final String LAST_UI_STATE=".LAST_UI_STATE";
    public static final String UI_STATE_DB=".UI_STATE_DB";
    public static final String UI_STATE_KEY=".UI_STATE_KEY";
    private static UIStateList uiStateList = new UIStateList();

//    private HashMap<String,String> activityMap = Maps.newHashMap();

    private static final UIStateManager uiStateManager = new UIStateManager();

    private DB uiStateDB;

    protected UIStateManager() {
        uiStateDB = BaseApplication.getApp().getUiStateDB();
        try{
            if (uiStateDB.exists(UI_STATE_KEY)) {
                UIStateList tmpList = uiStateDB.getObject(UI_STATE_KEY,UIStateList.class);
                uiStateList = tmpList;
            }
        }catch (Exception e){

        }
    }

    public synchronized static UIStateManager getInstance() {
        return uiStateManager;
    }


    public List<UIState> getUIStates(String category){
        KeyIterator it;

        try {
            String [] arr;
            if ((category == null) || (category.length() <= 0)) {
                return uiStateList;
            } else {
                UIStateList tmpList = new UIStateList();
                for (UIState state : tmpList) {
                    if (state.getName().equals(category)) {
                        tmpList.add(state);
                    }
                }
                return tmpList;
            }
        }catch (Exception ex){
            LogUtils.e("fail to get states", ex);
            return null;
        }
    }

    public synchronized void addUIState(UIState state){
        try {
            uiStateList.add(state);
            uiStateDB.put(UI_STATE_KEY, uiStateList);
        }catch (Exception e){
            LogUtils.e("fail to add states", e);
        }
    }

    public synchronized void delUIStates(UIState state){
        try {
            uiStateList.remove(state);
            uiStateDB.put(UI_STATE_KEY, uiStateList);
        }catch (Exception ex){
            LogUtils.e("fail to del states", ex);
        }
    }



    public  Object loadModelData(final Intent intent){
        if (intent == null){
            return null;
        }
        Bundle savedInstanceState = intent.getBundleExtra(LAST_UI_STATE);

        if (savedInstanceState == null){
            LogUtils.i("invalid bundle");
            return null;
        }

//        Serializable bundle = savedInstanceState.getBundle(UIStateManager.BUNDLE_UI_STATE);
        Serializable rawData = savedInstanceState.getSerializable(UIStateManager.LAST_UI_STATE);
        if (rawData == null){
            LogUtils.i("empty bundle");
            return null;
        }

        return rawData;
    }


}
