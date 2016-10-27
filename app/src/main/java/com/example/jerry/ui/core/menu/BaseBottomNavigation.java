package com.example.jerry.ui.core.menu;

import android.content.Context;
import android.graphics.Color;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.jerry.ui.R;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.parseColor;

/**
 * Created by jerry on 2016/10/28.
 */

public class BaseBottomNavigation  {
    private static final String[] itemText={"返回","收藏","历史","设置"};
    private static final int []   itemImage={R.mipmap.nav_home, R.mipmap.nav_favorite_set, R.mipmap.nav_favorite_get, R.mipmap.nav_config};

    public static void createMenu(AHBottomNavigation nav) {
        for (int i=0;i<itemText.length;i++){
            AHBottomNavigationItem item = new AHBottomNavigationItem(itemText[i], itemImage[i], Color.parseColor("#8B6B62"));
            nav.addItem(item);
        }
    }
}
