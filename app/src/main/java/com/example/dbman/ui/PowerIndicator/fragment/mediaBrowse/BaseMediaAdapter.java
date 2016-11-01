package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Utils;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.audio.AudioMediaAdapter;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.image.ImageMediaAdapter;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.video.VideoMediaAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.dbman.core.Utils.getMIMEType;

/**
 * Created by ChenJi on 2016/11/1.
 */

public abstract class BaseMediaAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final SysFileInfoDao sysFileInfoDao  = (SysFileInfoDao) BaseDatabase.getInstance().getDaoImpl("SysFileInfo");

    private List<SysFileInfo> fileInfo = new ArrayList<>();
    private Context mContext;

    private VideoMediaAdapter videoMediaAdapter = new VideoMediaAdapter();
    private AudioMediaAdapter audioMediaAdapter = new AudioMediaAdapter();
    private ImageMediaAdapter imageMediaAdapter = new ImageMediaAdapter();
    private DummyMediaAdapter dummyMediaAdapter = new DummyMediaAdapter();

    public BaseMediaAdapter(Context context) {
        this.mContext = context;
    }

    public BaseMediaAdapter(Context context, List<SysFileInfo> actors) {
        this.mContext = context;
        this.fileInfo = actors;
    }

    public SysFileInfo getItem(int i){
        return fileInfo.get(i);
    }

    public void refresh(List<SysFileInfo> actors){
        this.fileInfo = actors;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SysFileInfo sysFileInfo = getItem(i);
        IMediaAdapterIntf impl = getImpl(sysFileInfo);
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(impl.getLayoutId(i), viewGroup, false);
        return impl.createViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int i) {
        SysFileInfo sysFileInfo = getItem(i);
        IMediaAdapterIntf impl = getImpl(sysFileInfo);
        SysFileInfo p = fileInfo.get(i);
        impl.bindViewHolder(mContext, viewHolder, p);
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return fileInfo == null ? 0 : fileInfo.size();
    }

    private IMediaAdapterIntf getImpl(SysFileInfo sysFileInfo){
        SysFileInfoDao.SysFileType fileType = sysFileInfoDao.getFileType(sysFileInfo);
        if (fileType == SysFileInfoDao.SysFileType.FILE_TYPE_IMAGE){
            return imageMediaAdapter;
        }
        if (fileType == SysFileInfoDao.SysFileType.FILE_TYPE_VIDEO){
            return videoMediaAdapter;
        }

        if (fileType == SysFileInfoDao.SysFileType.FILE_TYPE_AUDIO){
            return audioMediaAdapter;
        }

        return  dummyMediaAdapter;
    }
}
