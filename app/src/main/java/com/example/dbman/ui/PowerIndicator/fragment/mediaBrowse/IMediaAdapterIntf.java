package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.view.View;

import com.example.dbman.db.genupdate.schema.SysFileInfo;

/**
 * Created by ChenJi on 2016/11/1.
 */

public interface IMediaAdapterIntf {
    int getLayoutId(int i);
    BaseViewHolder createViewHolder(View v);
    abstract void bindViewHolder(Context context, BaseViewHolder viewHolder, SysFileInfo sysFileInfo);
}
