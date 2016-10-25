package com.example.jerry.ui.core.ui_state;

import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class UIStateManager {
    public static final String BUNDLE_UI_STATE=".BUNDLE_UI_STATE";
    private static final String LAST_UI_STATE=".LAST_UI_STATE";

    public static Object loadModelData(final Bundle savedInstanceState){
        if (savedInstanceState == null) return null;

//        Serializable bundle = savedInstanceState.getBundle(UIStateManager.BUNDLE_UI_STATE);
        Serializable rawData = savedInstanceState.getSerializable(UIStateManager.LAST_UI_STATE);
        if (rawData == null) return null;

        try{
            UIState uiState = (UIState) rawData;
            Class modelClass = uiState.getUiModelClass();
            byte [] modelData = uiState.getRawModelData();
            ByteArrayInputStream bais = new ByteArrayInputStream(modelData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            return o;
        }catch (Exception e){
            return null;
        }


    }
}
