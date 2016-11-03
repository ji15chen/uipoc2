package com.example.dbman.ui.PowerIndicator;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;

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
