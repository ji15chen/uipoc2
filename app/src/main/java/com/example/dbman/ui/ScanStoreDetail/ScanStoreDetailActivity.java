package com.example.dbman.ui.ScanStoreDetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseFileManager;
import com.example.dbman.core.Constants;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.model.EquipTypeParamSetModel;
import com.example.dbman.db.model.StoreInfoModel;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.PowerIndicator.PowerIndicatorDetailActivity;
import com.example.dbman.ui.PowerIndicator.bean.T1EntryBean;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT1;
import com.example.dbman.ui.PowerIndicator.fragment.PowerIndicatorBriefViewFragment;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailSaveModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;
import com.example.dbman.ui.ScanStoreDetail.scanner.DeviceActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailActivityBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailDialogBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailSaveDialogBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailActivity extends DeviceActivity implements View.OnClickListener{
    private ScanStoreDetailActivityBinding binding;
    private ScanStoreDetailBasicTableAdapter scanStoreDetailBasicTableAdapter;


    public void update(){
        scanStoreDetailBasicTableAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onNewStoreDetailFound(String msg) {
        LogUtils.d("EPC:"+msg);
        ScanStoreDetailStatEntry statEntry;
        statEntry = scanStoreDetailBasicTableAdapter.getItem(msg);
        if (statEntry == null){
            try {
                String query = StoreInfoModel.buildEquipCardQuery(msg);
                if (query == null){
                    Toast.makeText(this,"查询无此EPC:"+msg,Toast.LENGTH_SHORT).show();
                    return;
                }

                StoreInfoModel model =  StoreInfoModel.loadEquipStoreDetail(query);
                List<StoreInfoModelEntry> data = model.getLstEquipStoreInfo();
                if (data.size() <= 0 ) {
                    Toast.makeText(this, "查询无此数据", Toast.LENGTH_SHORT).show();
                    return;

                }
                StoreInfoModelEntry entry = data.get(0);
                entry.setEpc(msg);
                statEntry = new ScanStoreDetailStatEntry(entry);
                scanStoreDetailBasicTableAdapter.putItem(msg,statEntry);
            }catch (Exception e){
                e.printStackTrace();
                statEntry = null;
            }
        }
        if (statEntry != null) {
            statEntry.add(1);
            update();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.test_card){
            Thread thread = new Thread(new Runnable() {
                private int count = 0;
                @Override
                public void run() {
                    try {
                        while (count < 10) {
                            Thread.sleep(30000);
                            new LoadDataTask().execute();
                            count++;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public boolean isSupportSaveState() {
        return false;
    }

    @Override
    protected int getActivityId() {
        return R.layout.scan_store_detail_activity;
    }

    @Override
    protected Class getPresentationModelClass() {
        return ScanStoreDetailModel.class;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {
        binding = (ScanStoreDetailActivityBinding) viewDataBinding;
        scanStoreDetailBasicTableAdapter = new ScanStoreDetailBasicTableAdapter(this,this);
        binding.table.setAdapter(scanStoreDetailBasicTableAdapter);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = ScanStoreDetailSaveDialog.createView(ScanStoreDetailActivity.this, scanStoreDetailBasicTableAdapter.getModel());
                new AlertDialog.Builder(ScanStoreDetailActivity.this).setTitle("装备动态 确认完成").setView(view).setPositiveButton("保存装备动态退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScanStoreDetailSaveDialogBinding binding = (ScanStoreDetailSaveDialogBinding)view.getTag();
                        ScanStoreDetailSaveModel model = binding.getModelData();
                        try {
                            model.validate();
                            model.save();
                            dialog.dismiss();
                            Toast.makeText(ScanStoreDetailActivity.this,"装备动态保存成功",Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(ScanStoreDetailActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("退出", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
    }

    @Override
    public String getShortName() {
        return "设备扫描";
    }


    @Override
    protected void onStop(){
        super.onStop();
    }

    private void handleRemove(ScanStoreDetailStatEntry entry){
        if (entry == null) return;
        scanStoreDetailBasicTableAdapter.delData(entry);
    }

    private void handlePowerIndicator(ScanStoreDetailStatEntry  entry){
        if (entry == null) return;
        Intent intent = new Intent(this, PowerIndicatorDetailActivity.class);
        intent.putExtra("id", entry.getEntry().getUuid());
        intent.putExtra("name", entry.getEntry().getColumnValues()[1]);
        startActivity(intent);
    }


    private void handleStoreInfoDetail(ScanStoreDetailStatEntry  entry){
        if (entry == null) return;

        View view = ScanStoreDetailDialog.createView(this,entry.getEntry());
        new AlertDialog.Builder(this).setTitle(entry.getEntry().getColumnValues()[1]).setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    @Override
    public void onClick(View v) {
        Pair<Integer,Integer> tag = (Pair<Integer,Integer>) v.getTag();
        Integer action = tag.first;
        Integer index = tag.second;

        ScanStoreDetailStatEntry  entry = null;
        try{
            entry = (ScanStoreDetailStatEntry )scanStoreDetailBasicTableAdapter.getItem(index,0);
        }catch (Exception e){
            entry = null;
        }

        LogUtils.d("clicked:"+action);

        switch (action)
        {
            case 2:
            {
                handleRemove(entry);
            }
            break;
            case 1:
            {
                handlePowerIndicator(entry);
            }
            break;
            default:
            {
                handleStoreInfoDetail(entry);
            }
            break;
        }
    }



    private  class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
        protected void onPostExecute(Void result) {
            onNewStoreDetailFound("111112222233333");
        }
    }

}
