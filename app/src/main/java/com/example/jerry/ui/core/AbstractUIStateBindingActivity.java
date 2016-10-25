package com.example.jerry.ui.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.example.jerry.ui.PowerIndicator.PowerIndicatorModel;
import com.example.jerry.ui.R;
import com.example.jerry.ui.core.ui_state.UIStateManager;
import com.example.jerry.ui.databinding.PowerIndicatorActivityBinding;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ChenJi on 2016/10/25.
 */

public abstract class AbstractUIStateBindingActivity extends AbstractBaseActivity {
    private ViewDataBinding binding;

    protected abstract Class getPresentationModelClass();
    protected abstract void onFinishUIBinding(ViewDataBinding viewDataBinding);

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
        Object modelObject = UIStateManager.loadModelData(savedInstanceState);
        if (modelObject == null){
            Object defObject = createDefaultPresentationModelObject();
            if (defObject != null) {
                binding.setVariable(R.string.id_presentation_data, createDefaultPresentationModelObject());
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
