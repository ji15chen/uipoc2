package com.example.dbman.ui.PowerIndicator;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractBaseUIActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorActivity extends AbstractUIStateBindingActivityWithNavMenu {
    private com.example.dbman.ui.databinding.PowerIndicatorActivityBinding binding ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityId() {
        return R.layout.power_indicator_activity;
    }

    @Override
    protected Class getPresentationModelClass() {
        return PowerIndicatorModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {
        binding = (com.example.dbman.ui.databinding.PowerIndicatorActivityBinding) viewDataBinding;
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
