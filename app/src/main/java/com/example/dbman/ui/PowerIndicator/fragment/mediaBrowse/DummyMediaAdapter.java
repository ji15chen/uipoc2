package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbman.core.BaseFileManager;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.ui.R;

/**
 * Created by ChenJi on 2016/11/1.
 */

public class DummyMediaAdapter implements IMediaAdapterIntf {

    @Override
    public int getLayoutId(int i) {
        return R.layout.media_dummy_view;
    }

    @Override
    public BaseViewHolder createViewHolder(View v) {
        return new DummyViewHolder(v);
    }

    @Override
    public void bindViewHolder(Context context, BaseViewHolder viewHolder, SysFileInfo sysFileInfo) {
    }


    public class DummyViewHolder extends BaseViewHolder {

        DummyViewHolder(View v) {
            super(v);
        }
    }
}
