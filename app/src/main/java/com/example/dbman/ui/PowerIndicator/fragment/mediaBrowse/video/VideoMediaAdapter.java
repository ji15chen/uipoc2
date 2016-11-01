package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.video;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
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

public class VideoMediaAdapter  implements IMediaAdapterIntf, View.OnClickListener {


    @Override
    public int getLayoutId(int i) {
        return R.layout.media_video_view;
    }

    @Override
    public BaseViewHolder createViewHolder(View v) {
        return new VideoViewHolder(v);
    }

    @Override
    public void bindViewHolder(Context context, BaseViewHolder viewHolder, SysFileInfo sysFileInfo) {
        VideoViewHolder holder = (VideoViewHolder) viewHolder;
        holder.mTextView.setText(sysFileInfo.getTitle());
        holder.mVideoView.setVideoURI(Uri.fromFile(BaseFileManager.getFilePath(sysFileInfo.getFileName(),false)));
        holder.mVideoView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof VideoView){
            VideoView videoView = (VideoView) v;
            if (videoView.isPlaying()){
                videoView.stopPlayback();
            }else {
                videoView.start();
            }
        }
    }


    public class VideoViewHolder extends BaseViewHolder {
        TextView mTextView;
        VideoView mVideoView;
        private MediaController mc;
        VideoViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mVideoView = (VideoView) v.findViewById(R.id.media_view);
            mc = new MediaController(v.getContext());
            mVideoView.setMediaController(mc);
        }
    }
}
