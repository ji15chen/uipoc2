package com.example.dbman.ui.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.Utils;
import com.example.dbman.ui.BR;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.ui_state.UIState;
import com.example.dbman.ui.core.ui_state.UIStateManager;

import java.util.List;


/**
 * Created by ChenJi on 2016/10/25.
 */

public abstract class AbstractUIStateBindingActivity extends AbstractBaseUIActivity {

    private ViewDataBinding binding;
    private Object modelData;

    abstract public boolean isSupportSaveState();
    abstract protected int getActivityId();

    //get current UIState
    private UIState getActivityUIState(){
        UIState uiState = new UIState();
        uiState.setName(getShortName());
        uiState.setUiClass(this.getClass());
        uiState.setRawModelData(Utils.toByteArray(getModelData()));
        Bitmap bitmap = Utils.takeScreenShot(this);
        if (bitmap != null){
            uiState.setJpegSnapshot(Utils.Bitmap2Bytes(bitmap));
        }
        return uiState;
    }

    public void saveUIState(){
        LogUtils.i("saving ui for:"+this.getShortName());
        try {
            UIState uiState = getActivityUIState();
            UIStateManager.getInstance().addUIState(uiState);
        }catch (Exception e){
            LogUtils.e("save ui fail", e);
        }
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

    public  UIState getLatestUIState(){
        List<UIState> uiStateList = UIStateManager.getInstance().getUIStates(getShortName());
        if (uiStateList.size() <= 0){
            return null;
        }else {
            return uiStateList.get(0);
        }
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

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, getActivityId());
        modelData = UIStateManager.getInstance().loadModelData(getIntent());
        if (modelData == null){
            LogUtils.i("new activity, using default model data");
            modelData = createDefaultPresentationModelObject();
            if (modelData != null) {
                binding.setVariable(BR.modelData, modelData);
            }
        }else{
            LogUtils.i("loding history model data");
            binding.setVariable(BR.modelData, modelData);
        }

        onFinishUIBinding(binding);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
