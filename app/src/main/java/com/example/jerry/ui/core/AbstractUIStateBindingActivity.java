package com.example.jerry.ui.core;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.jerry.core.Utils;
import com.example.jerry.ui.R;
import com.example.jerry.ui.core.ui_state.UIState;
import com.example.jerry.ui.core.ui_state.UIStateBrief;
import com.example.jerry.ui.core.ui_state.UIStateManager;

import java.io.Serializable;
import java.util.Objects;


/**
 * Created by ChenJi on 2016/10/25.
 */

public abstract class AbstractUIStateBindingActivity extends AbstractBaseActivity {

    private ViewDataBinding binding;
    private Object modelData;


    public UIState getActivityUIState(){
        UIState uiState = new UIState();
        uiState.setUiClass(this.getClass());
        uiState.setRawModelData(Utils.toByteArray(getModelData()));
        Bitmap bitmap = Utils.takeScreenShot(this);
        if (bitmap != null){
            uiState.setJpegSnapshot(Utils.Bitmap2Bytes(bitmap));
        }
        return uiState;
    }


    protected abstract Class getPresentationModelClass();
    protected abstract void onFinishUIBinding(ViewDataBinding viewDataBinding);

    protected synchronized Object getModelData() {
        return modelData;
    }

    protected synchronized void setModelData(Object modelData) {
        this.modelData = modelData;
    }

    protected synchronized void updateModelData(Object modelData) {
        this.modelData = modelData;
    }




    private Object createDefaultPresentationModelObject(){
        try {
            Class cls = getPresentationModelClass();
            Object obj = cls.newInstance();
            return obj;
        }catch (Exception e){
            return null;
        }
    }

    protected final void onCreate(Bundle savedInstanceState, int activityId){
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, activityId);
        Object modelObject = UIStateManager.getInstance().loadModelData(savedInstanceState);
        if (modelObject == null){
            Object defObject = createDefaultPresentationModelObject();
            if (defObject != null) {
                binding.setVariable(R.string.id_presentation_data, defObject);
            }
        }else{
            binding.setVariable(R.string.id_presentation_data, modelObject);
        }

        onFinishUIBinding(binding);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
