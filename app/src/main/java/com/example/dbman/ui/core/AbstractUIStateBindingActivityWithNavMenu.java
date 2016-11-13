package com.example.dbman.ui.core;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseApplication;
import com.example.dbman.core.Utils;
import com.example.dbman.ui.BR;
import com.example.dbman.ui.PowerIndicator.PowerIndicatorActivity;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.ui_state.UIState;
import com.example.dbman.ui.core.ui_state.UIStateManager;

import java.util.List;


/**
 * Created by ChenJi on 2016/10/25.
 */

public abstract class AbstractUIStateBindingActivityWithNavMenu extends AbstractUIStateBindingActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private static final Class<? extends AbstractBaseActivity> mItemClasses [] = new Class[]{
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class
    };
    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        if (position >= 0 && position < mItemClasses.length) {
            Class<? extends AbstractBaseActivity> act = mItemClasses[position];
            startActivity(act);
            BaseApplication.setSelectedNavId(position);
        }
    }

}
