package com.example.dbman.ui.PowerIndicator;

import android.app.Fragment;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.dbman.db.model.EquipHirarchyModel;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractBaseUIActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorActivity extends AbstractUIStateBindingActivityWithNavMenu {
    private com.example.dbman.ui.databinding.PowerIndicatorActivityBinding binding ;
    public TreeNode treeRootNode = EquipHirarchyModel.getInstance().getRootNode();
    private AndroidTreeView tView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tView = new AndroidTreeView(this, treeRootNode);
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
        binding = (com.example.dbman.ui.databinding.PowerIndicatorActivityBinding) viewDataBinding;
        binding.slidemenuEquipType.setMode(SlidingMenu.RIGHT);
        binding.slidemenuEquipType.addView(tView.getView());
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
}
