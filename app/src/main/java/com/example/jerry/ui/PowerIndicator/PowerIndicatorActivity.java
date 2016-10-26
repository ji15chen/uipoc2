package com.example.jerry.ui.PowerIndicator;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.example.jerry.ui.R;
import com.example.jerry.ui.core.AbstractUIStateBindingActivity;
import com.example.jerry.ui.core.ui_state.UIState;
import com.example.jerry.ui.core.ui_state.UIStateManager;
import com.example.jerry.ui.databinding.PowerIndicatorActivityBinding;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorActivity extends AbstractUIStateBindingActivity {
    private PowerIndicatorActivityBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.power_indicator_activity);
    }

    @Override
    protected Class getPresentationModelClass() {
        return PowerIndicatorModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {
        binding = (PowerIndicatorActivityBinding) viewDataBinding;
        binding.dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PowerIndicatorModel)getModelData()).setTextValue("111");
                saveUIState();
            }
        });
    }

    @Override
    public String getShortName() {
        return "性能指标";
    }


    @Override
    protected void onStop(){
        super.onStop();
    }
}
