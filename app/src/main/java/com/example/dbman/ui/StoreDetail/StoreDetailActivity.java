package com.example.dbman.ui.StoreDetail;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;

import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;
import com.example.dbman.ui.databinding.StoreDetailActivityBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StoreDetailActivity extends AbstractUIStateBindingActivityWithNavMenu {
    public static final int REQUEST_QUERY = 1;
    private StoreDetailActivityBinding binding;
    StoreDetailViewFragment fragment;
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
        return R.layout.store_detail_activity;
    }

    @Override
    protected Class getPresentationModelClass() {
        return StoreDetailModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {
        binding = (StoreDetailActivityBinding) viewDataBinding;
        //binding.PowerIndicatorBriefViewFragmentSearch.
        fragment = (StoreDetailViewFragment)this.getFragmentManager().findFragmentById(R.id.content);
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
        return "实力明细";
    }


    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
