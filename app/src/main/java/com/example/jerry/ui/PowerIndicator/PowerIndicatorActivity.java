package com.example.jerry.ui.PowerIndicator;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.example.jerry.ui.R;
import com.example.jerry.ui.core.AbstractUIStateBindingActivity;
import com.example.jerry.ui.databinding.PowerIndicatorActivityBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorActivity extends AbstractUIStateBindingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.power_indicator_activity);
        PowerIndicatorActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.power_indicator_activity);
        PowerIndicatorModel user = new PowerIndicatorModel();
        binding.setModelData(user);
    }

    @Override
    protected Class getPresentationModelClass() {
        return PowerIndicatorModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {

    }
}
