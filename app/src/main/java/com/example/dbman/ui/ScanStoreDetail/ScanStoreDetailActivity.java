package com.example.dbman.ui.ScanStoreDetail;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
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
        scanStoreDetailBasicTableAdapter.delData(entry);
    }

    private void handlePowerIndicator(StoreInfoModelEntry entry){
        Intent intent = new Intent(this, PowerIndicatorDetailActivity.class);
        intent.putExtra("id", entry.getUuid());
        intent.putExtra("name", entry.getColumnValues()[1]);
        startActivity(intent);
    }

    private void handleStoreInfoDetail(StoreInfoModelEntry entry){
        ScanStoreDetailDialog dialog = new ScanStoreDetailDialog(this, entry);
        dialog.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.layout.table_cell_remove:
            {
                handleRemove((StoreInfoModelEntry)v.getTag());
            }
            break;
            case R.layout.scan_store_detail_cell_detail:
            {
                handlePowerIndicator((StoreInfoModelEntry)v.getTag());
            }
            break;
            default:
            {
                handleStoreInfoDetail((StoreInfoModelEntry)v.getTag());
            }
            break;
        }
    }


}
