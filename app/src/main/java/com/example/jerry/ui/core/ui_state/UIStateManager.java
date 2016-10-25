package com.example.jerry.ui.core.ui_state;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.jerry.core.BaseApplication;
import com.example.jerry.core.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import com.snappydb.DBFactory;
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

//    private HashMap<String,String> activityMap = Maps.newHashMap();
    private DB uiStateDB;

    private static final UIStateManager uiStateManager = new UIStateManager();


    protected UIStateManager(){
        try {
            uiStateDB = DBFactory.open(BaseApplication.getApp().getApplicationContext(), UI_STATE_DB);
        }catch (Exception e){
            //TODO:
        }
    }

    public synchronized static UIStateManager getInstance() {
        return uiStateManager;
    }


    public List<UIState> getUIStates(String category){
        KeyIterator it;
        List<UIState> states = Lists.newArrayList();

        try {
            if ((category == null) || (category.length() <= 0)) {
                it = uiStateDB.allKeysIterator();
            } else {
                it = uiStateDB.findKeysIterator(category);
            }
            for (String[] batch : uiStateDB.findKeysIterator(category).byBatch(BATCH_SIZE)) {
                for (String key : batch) {
                    UIState state = uiStateDB.getObject(key, UIState.class);
                    states.add(state);
                }
            }
        }catch (Exception ex){
            return states;
        }
        return states;
    }

    public void addUIState(UIState state){
        try {
            uiStateDB.put(state.toString(), state);
        }catch (Exception e){

        }
    }

    public void delUIStates(UIState state){
        try {
            uiStateDB.del(state.toString());
        }catch (Exception ex){

        }
    }



    public  Object loadModelData(final Bundle savedInstanceState){
        if (savedInstanceState == null) return null;

//        Serializable bundle = savedInstanceState.getBundle(UIStateManager.BUNDLE_UI_STATE);
        Serializable rawData = savedInstanceState.getSerializable(UIStateManager.LAST_UI_STATE);
        if (rawData == null) return null;

        try{
            UIState uiState = (UIState) rawData;
            byte [] modelData = uiState.getRawModelData();
            Object o = Utils.toObject(modelData);
            return o;
        }catch (Exception e){
            return null;
        }
    }


}
