package com.example.dbman.ui.ScanStoreDetail;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.PowerIndicator.adapter.SampleTableAdapter;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ScanStoreDetailBasicTableAdapter extends SampleTableAdapter {
    private  int width;
    private  int height;
    List<ScanStoreDetailStatEntry> model = new ArrayList<>();
    HashMap<String, ScanStoreDetailStatEntry> hashMapEPC = new HashMap<>();
    private static final String [] headers = new String[]{"序号", "装备名称","现有数量","扫描时间", "装备型号", "编号", "数量", "性能指标", "移除"};
    View.OnClickListener onClickListener;
    private Resources resources;

    public ScanStoreDetailBasicTableAdapter(Context context, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
        resources = context.getResources();
        width = resources.getDimensionPixelSize(R.dimen.table_width);
        height = resources.getDimensionPixelSize(R.dimen.table_height);
    }

    public ScanStoreDetailStatEntry getItem(String epc){
        return hashMapEPC.get(epc);
    }

    public void putItem(String epc, ScanStoreDetailStatEntry entry){
        hashMapEPC.put(epc,entry);
        model.add(entry);
        notifyDataSetChanged();
    }
    public void delData(ScanStoreDetailStatEntry entry){
        model.remove(entry);
        hashMapEPC.remove(entry.getEntry().getEpc());
        this.notifyDataSetChanged();
    }
    @Override
    public Object getItem(int row, int col) {
        return model.get(row);
    }

    public int find(Object obj){
        for (int i=0;i<model.size();i++){
            if (obj == model.get(i)){
                return i;
            }
        }
        return -1;
    }


    public void setData(List<ScanStoreDetailStatEntry> model) {
        this.model = model;
        this.notifyDataSetChanged();
    }


    public List<ScanStoreDetailStatEntry> getModel() {
        return model;
    }

    @Override
    public int getRowCount() {
        return (model == null)?0:model.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length - 1;
    }

    @Override
    public int getWidth(int column) {
        return width;
    }

    @Override
    public int getHeight(int row) {
        return height;
    }


    @Override
    public String getCellString(int row, int column) {
        if (row < 0){
            return headers[column + 1];
        }
        if (model == null){
            return "";
        }
        ScanStoreDetailStatEntry rowData = model.get(row );
        if (rowData == null){
            return "";
        }

        StoreInfoModelEntry entry = rowData.getEntry();

        switch( column + 1){
            case 0:
                return String.valueOf(row+1);
            case 1:
                return entry.getColumnValues()[2];
            case 2:
                return String.valueOf(rowData.getCount());
            case 3:
                return entry.getTime();
            case 4:
                return entry.getColumnValues()[1];
            case 5:
                return entry.getColumnValues()[3];
            case 6:
                return String.valueOf(rowData.getCur_total());
            case 7:
                return "性能指标";
            default:
                return "移除";
        }
    }
    @Override
    protected void setText(View v, String text) {
        switch (v.getId())
        {
            case R.layout.table_cell_remove:
            {

            }
            break;
            case R.layout.scan_store_detail_cell_detail:
            {

            }
            break;
            default:
            {
                super.setText(v,text);
            }
            break;
        }
    }
    @Override
    public View getView(int row, int column, View converView, ViewGroup parent) {
        int resId = getLayoutResource(row, column);
        if (converView == null) {

            converView = getInflater().inflate(getLayoutResource(row, column), parent, false);

            if (resId == R.layout.scan_store_detail_cell_detail){
                converView.setTag(new Pair<Integer,Integer>(1,row));
            }else
            if (resId == R.layout.table_cell_remove){
                converView.setTag(new Pair<Integer,Integer>(2,row));
            }else{
                converView.setTag(new Pair<Integer,Integer>(0,row));
            }
            converView.setOnClickListener(onClickListener);
        }
        if (
                (resId != R.layout.scan_store_detail_cell_detail)
                        &&
                        (resId != R.layout.table_cell_remove)
                ) {
            setText(converView, getCellString(row, column));
        }

        return converView;
    }

    @Override
    public int getLayoutResource(int row, int column) {
        final int layoutResource;
        int type = getItemViewType(row, column);
        //LogUtils.d(row+","+column+"="+type);
        switch (type) {
            case 0:
                layoutResource = R.layout.powerindicator_table_header;
                break;
            case 1:
                layoutResource = R.layout.powerindicator_table_body;
                break;
            case 2:
                layoutResource = R.layout.scan_store_detail_cell_detail;
                break;
            case 3:
                layoutResource = R.layout.table_cell_remove;
                break;
            default:
                throw new RuntimeException("wtf?");
        }
        return layoutResource;
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (row < 0) {
            return 0;
        } else
        {
            if (column < (headers.length -3) ){
                return 1;
            }
            else {
                if (column == headers.length - 3) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }
}