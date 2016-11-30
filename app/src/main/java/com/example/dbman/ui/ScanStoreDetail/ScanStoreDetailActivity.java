package com.example.dbman.ui.ScanStoreDetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.Constants;
import com.example.dbman.db.model.StoreInfoModel;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.PowerIndicator.PowerIndicatorDetailActivity;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailSaveModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;
import com.example.dbman.ui.ScanStoreDetail.scanner.DeviceActivity;
import com.example.dbman.ui.databinding.ScanStoreDetailActivityBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailSaveDialogBinding;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailActivity extends DeviceActivity implements View.OnClickListener{
    private ScanStoreDetailActivityBinding binding;
    private ScanStoreDetailBasicTableAdapter scanStoreDetailBasicTableAdapter;
    private static final String [] test_epcs={
            "111112222233333",
            "211112222233333",
            "311112222233333",
    };
    private static int test_index = 0;

    public void update(){
        scanStoreDetailBasicTableAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onNewStoreDetailFound(String msg) {
       new AsyncNewCardSearchTask().execute(msg);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.test_card){
            Thread thread = new Thread(new Runnable() {
                private AtomicInteger atomicInteger = new AtomicInteger(0);
                @Override
                public void run() {
                    try {
                        while (atomicInteger.intValue()<10) {
                            Thread.sleep(4000);
                            onNewStoreDetailFound(test_epcs[(atomicInteger.intValue())%test_epcs.length]);
                            atomicInteger.incrementAndGet();
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
                if (scanStoreDetailBasicTableAdapter.getRowCount() <= 0){
                    return;
                }
                ScanStoreDetailSaveDialog saveDialog = new ScanStoreDetailSaveDialog(ScanStoreDetailActivity.this, scanStoreDetailBasicTableAdapter.getModel());
                saveDialog.show();
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
        update();
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

    private  class AsyncNewCardSearchTask extends AsyncTask<String, Void, ScanStoreDetailStatEntry> {
        @Override
        protected ScanStoreDetailStatEntry doInBackground(String... params) {
            String msg = params[0];
            LogUtils.d("EPC:"+msg);
            ScanStoreDetailStatEntry statEntry;
            statEntry = scanStoreDetailBasicTableAdapter.getItem(msg);
            if (statEntry == null){
                try {
                    String query = StoreInfoModel.buildEquipCardQuery(msg);
                    if (query == null){
                        return null;
                    }

                    StoreInfoModel model =  StoreInfoModel.loadEquipStoreDetail(query);
                    List<StoreInfoModelEntry> data = model.getLstEquipStoreInfo();
                    if (data.size() <= 0 ) {
                        return null;
                    }
                    StoreInfoModelEntry entry = data.get(0);
                    entry.setEpc(msg);
                    statEntry = new ScanStoreDetailStatEntry(entry);
                    scanStoreDetailBasicTableAdapter.putItem(msg,statEntry);
                }catch (Exception e){
                    e.printStackTrace();
                    statEntry = null;
                }
            }else{
                statEntry.add(1);
            }
            return  statEntry;
        }
        protected void onPostExecute(ScanStoreDetailStatEntry result) {
            if (result != null){
                update();
            }
        }
    }
}
