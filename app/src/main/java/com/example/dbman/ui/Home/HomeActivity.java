package com.example.dbman.ui.Home;

import android.app.ActionBar;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.example.dbman.ui.PowerIndicator.PowerIndicatorActivity;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractBaseActivity;
import com.example.dbman.ui.core.AbstractBaseUIActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.core.ui_state.UIState;
import com.example.dbman.ui.core.ui_state.UIStateBrief;
import com.example.dbman.ui.core.ui_state.UIStateManager;
import com.example.dbman.ui.core.menu.CircleMenuLayout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HomeActivity extends AbstractUIStateBindingActivityWithNavMenu {

    @Override
    protected int getActivityId() {
        return R.layout.home_activity;
    }

    @Override
    protected Class getPresentationModelClass() {
        return HomeModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public String getShortName() {
        return "首页";
    }


}
