package com.example.dbman.ui.PowerIndicator;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import com.example.dbman.ui.PowerIndicator.fragment.PowerIndicatorBriefViewFragment;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorActivity extends AbstractUIStateBindingActivityWithNavMenu implements  PowerIndicatorBriefViewFragment.OnFragmentInteractionListener{
    public static final int REQUEST_QUERY = 1;
    private PowerIndicatorActivityBinding binding;
    PowerIndicatorBriefViewFragment fragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isSupportSaveState() {
        return true;
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
        binding = (PowerIndicatorActivityBinding) viewDataBinding;
        //binding.PowerIndicatorBriefViewFragmentSearch.
        fragment = (PowerIndicatorBriefViewFragment)this.getFragmentManager().findFragmentById(R.id.PowerIndicatorBriefViewFragment_Content);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case REQUEST_QUERY:
                if (resultCode == RESULT_OK ) {
                    String query = data.getStringExtra("query");//str即为回传的值
                    fragment.updateQuery(query);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String getShortName() {
        return "性能指标";
    }


    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
