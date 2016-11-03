package com.example.jerry.ui.core;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.example.jerry.ui.R;
import com.example.jerry.ui.core.ui_state.UIStateBrief;
import com.example.jerry.ui.core.ui_state.UIStateManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public abstract class AbstractBaseUIActivity extends AbstractBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //设置ActionBar 浮动到view 上层来
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);


        ActionBar actionBar = getActionBar();
        // 是否显示应用程序图标，默认为true
        actionBar.setDisplayShowHomeEnabled(true);
        // 是否显示应用程序标题，默认为true
        actionBar.setDisplayShowTitleEnabled(true);
        /*
         * 是否将应用程序图标转变成可点击的按钮，默认为false。
         *
         * 如果设置了DisplayHomeAsUpEnabled为true，
         *
         * 则该设置自动为 true。
         */
        actionBar.setHomeButtonEnabled(true);
        /*
         * 在应用程序图标的左边显示一个向左的箭头，
         *
         * 并且将HomeButtonEnabled设为true。
         *
         * 默认为false。
         */
        //actionBar.setDisplayHomeAsUpEnabled(true);

        //设置ActionBar 背景色 透明
//		 getActionBar().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        //设置半透明的底色
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));
//google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
//为了让下面的背景色一致，还需要添加一行代码：
        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));
        forceShowOverflowMenu();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        /*
         * 将actionBar的HomeButtonEnabled设为ture，
         *
         * 将会执行此case
         */
            case android.R.id.home:
                finish();
                break;
            case R.id.id_menu_item_scan:
                Toast.makeText(this, "添加", Toast.LENGTH_LONG).show();
                break;
            // 其他省略...
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void forceShowOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 显示OverflowMenu的Icon
     *
     * @param featureId
     * @param menu
     */
    private void setOverflowIconVisible(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.d("OverflowIconVisible", e.getMessage());
                }
            }
        }
    }
}
