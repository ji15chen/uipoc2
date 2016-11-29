package com.example.dbman.ui.ScanStoreDetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseFileManager;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.model.StoreInfoModel;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.PowerIndicator.PowerIndicatorDetailActivity;
import com.example.dbman.ui.PowerIndicator.fragment.PowerIndicatorBriefViewFragment;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.scanner.DeviceActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.PowerIndicatorActivityBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailActivityBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailDialogBinding;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailActivity extends DeviceActivity implements View.OnClickListener{
    private ScanStoreDetailActivityBinding binding;
    private ScanStoreDetailBasicTableAdapter scanStoreDetailBasicTableAdapter;

    @Override
    protected void onNewStoreDetailFound(String epc, StoreInfoModelEntry entry) {
        scanStoreDetailBasicTableAdapter.addData(entry);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            StoreInfoModel model =  StoreInfoModel.loadEquipStoreDetail("");
            List<StoreInfoModelEntry> data = model.getLstEquipStoreInfo();
            scanStoreDetailBasicTableAdapter.setData(data);
        }catch (Exception e){
            e.printStackTrace();
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
                View view = ScanStoreDetailSaveDialog.createView(ScanStoreDetailActivity.this, scanStoreDetailBasicTableAdapter.getModel());
                new AlertDialog.Builder(ScanStoreDetailActivity.this).setTitle("装备动态 确认完成").setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
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

    private void handleRemove(StoreInfoModelEntry entry){
        if (entry == null) return;
        scanStoreDetailBasicTableAdapter.delData(entry);
    }

    private void handlePowerIndicator(StoreInfoModelEntry entry){
        if (entry == null) return;
        Intent intent = new Intent(this, PowerIndicatorDetailActivity.class);
        intent.putExtra("id", entry.getUuid());
        intent.putExtra("name", entry.getColumnValues()[1]);
        startActivity(intent);
    }


    private void handleStoreInfoDetail(StoreInfoModelEntry entry){
        if (entry == null) return;

        View view = ScanStoreDetailDialog.createView(this,entry);
        new AlertDialog.Builder(this).setTitle(entry.getColumnValues()[1]).setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
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

        StoreInfoModelEntry entry = null;
        try{
            entry = (StoreInfoModelEntry)scanStoreDetailBasicTableAdapter.getItem(index,0);
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


}
