package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.model.EquipTypeParamSetModel;
import com.example.dbman.ui.PowerIndicator.bean.ActorBean;
import com.example.dbman.ui.PowerIndicator.bean.T1EntryBean;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentBase;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT1;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Use the {@link power_indicator_media_browse_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class power_indicator_media_browse_fragment extends FragmentBase {
    private static final String ARG_PARAM1="param1";
    private static final SysFileInfoDao sysFileInfoDao  = (SysFileInfoDao)BaseDatabase.getInstance().getDaoImpl("SysFileInfo");

    private String eqUUID="";
    private RecyclerView mMediaList;
    private BaseMediaAdapter mediaAdapter;

    public power_indicator_media_browse_fragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param uuid Parameter 1.
     * @return A new instance of fragment power_indicator_media_browse_fragment.
     */
    public static power_indicator_media_browse_fragment newInstance(String uuid) {
        power_indicator_media_browse_fragment fragment = new power_indicator_media_browse_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, uuid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void refreshData(String eqUUID){
        if (this.eqUUID.equals(eqUUID)) return;
        new LoadDataTask().execute(eqUUID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_power_indicator_media_browse_fragment, container, false);
        mMediaList = (RecyclerView) root.findViewById(R.id.photoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMediaList.setLayoutManager(linearLayoutManager);
        mMediaList.setItemAnimator(new DefaultItemAnimator());
        mMediaList.setHasFixedSize(false);
        mediaAdapter = new BaseMediaAdapter(getActivity());
        mMediaList.setAdapter(mediaAdapter);

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private  class LoadDataTask extends AsyncTask<String, Void, List<SysFileInfo>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected List<SysFileInfo> doInBackground(String... uuids) {
            String uuid = uuids[0];
            List<SysFileInfo> sysFileInfos = new ArrayList<>();
            List<SysFileInfo> fileList;
            try {
                fileList = sysFileInfoDao.findSysFile(UUID.fromString(uuid));
                for (SysFileInfo file:fileList){
                    SysFileInfoDao.SysFileType type = sysFileInfoDao.getFileType(file);
                    if (type == SysFileInfoDao.SysFileType.FILE_TYPE_OTHER) continue;
                    sysFileInfos.add(file);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            return sysFileInfos;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(List<SysFileInfo> result) {
            if (result != null) {
                mediaAdapter.refresh(result);
            }
        }
    }

}
