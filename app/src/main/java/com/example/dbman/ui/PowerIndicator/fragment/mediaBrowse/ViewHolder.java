package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbman.ui.R;

// 重写的自定义ViewHolder
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            ImageView mImageView;

            ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.name);
                mImageView = (ImageView) v.findViewById(R.id.pic);
            }
        }