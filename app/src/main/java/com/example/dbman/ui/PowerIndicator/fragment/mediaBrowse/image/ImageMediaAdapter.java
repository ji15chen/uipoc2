package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.image;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbman.core.BaseFileManager;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.BaseMediaAdapter;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.BaseViewHolder;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.IMediaAdapterIntf;
import com.example.dbman.ui.R;

import java.util.List;

/**
 * Created by ChenJi on 2016/11/1.
 */

public class ImageMediaAdapter implements IMediaAdapterIntf {

    @Override
    public int getLayoutId(int i) {
        return R.layout.media_image_view;
    }

    @Override
    public BaseViewHolder createViewHolder(View v) {
        return new ImageViewHolder(v);
    }

    @Override
    public void bindViewHolder(Context context, BaseViewHolder viewHolder, SysFileInfo sysFileInfo) {
        ImageViewHolder holder = (ImageViewHolder) viewHolder;
        holder.mTextView.setText(sysFileInfo.getTitle());
        holder.mImageView.setImageURI(Uri.fromFile(BaseFileManager.getFilePath(sysFileInfo,false)));
    }


    public class ImageViewHolder extends BaseViewHolder {
        TextView mTextView;
        ImageView mImageView;

        ImageViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mImageView = (ImageView) v.findViewById(R.id.media_view);
        }
    }
}
