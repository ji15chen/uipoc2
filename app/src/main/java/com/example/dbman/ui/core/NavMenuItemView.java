package com.example.dbman.ui.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dbman.ui.R;

/**
 * Created by ChenJi on 2016/11/11.
 */
@Deprecated
public class NavMenuItemView extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    public NavMenuItemView(Context context, String title, int icon) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.nav_menu_item, this);
        //imageView = (ImageView) this.findViewById(R.id.id_nav_menu_item_image);
//        textView = (TextView) this.findViewById(R.id.id_nav_menu_item_text);
//        imageView.setImageResource(icon);
    }

}
