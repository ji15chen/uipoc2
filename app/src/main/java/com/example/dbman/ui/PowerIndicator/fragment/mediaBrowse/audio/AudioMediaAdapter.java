package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

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

public class AudioMediaAdapter implements IMediaAdapterIntf , View.OnClickListener {

    @Override
    public int getLayoutId(int i) {
        return R.layout.media_audio_view;
    }

    @Override
    public BaseViewHolder createViewHolder(View v) {
        return new AudioViewHolder(v);
    }

    @Override
    public void bindViewHolder(Context context,BaseViewHolder viewHolder, SysFileInfo sysFileInfo) {
        AudioViewHolder holder = (AudioViewHolder) viewHolder;
        holder.mTextView.setText(sysFileInfo.getTitle());
        holder.mImageView.setImageResource(R.drawable.music);
        try {
            holder.mp.setDataSource(context, Uri.fromFile(BaseFileManager.getFilePath(sysFileInfo, false)));
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.mImageView.setOnClickListener(this);
        holder.mImageView.setTag(holder);
    }

    @Override
    public void onClick(View v) {
        try {
            AudioViewHolder audioViewHolder = (AudioViewHolder) v.getTag();
            if (audioViewHolder.mp.isPlaying() ){
                audioViewHolder.mp.stop();
            }else{
                audioViewHolder.mp.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class AudioViewHolder extends BaseViewHolder {
        TextView mTextView;
        ImageView mImageView;
        MediaPlayer mp;
        AudioViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mImageView = (ImageView) v.findViewById(R.id.media_view);
            mp = new MediaPlayer();
        }
    }
}
