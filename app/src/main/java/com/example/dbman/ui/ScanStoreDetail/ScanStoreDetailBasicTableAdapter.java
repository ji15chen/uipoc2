package com.example.dbman.ui.ScanStoreDetail;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.PowerIndicator.adapter.SampleTableAdapter;
import com.example.dbman.ui.R;
import java.util.List;

public class ScanStoreDetailBasicTableAdapter extends SampleTableAdapter {
    private  int width;
    private  int height;
    List<StoreInfoModelEntry> model = null;
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
    public void delData(StoreInfoModelEntry entry){
        model.remove(entry);
        this.notifyDataSetChanged();
    }
    public void setData(List<StoreInfoModelEntry> model) {
        this.model = model;
        this.notifyDataSetChanged();
    }
    public void addData(StoreInfoModelEntry entry){
        this.model.add(entry);
        this.notifyDataSetChanged();
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
        StoreInfoModelEntry rowData = model.get(row );
        if (rowData == null){
            return "";
        }

        switch( column + 1){
            case 0:
                return String.valueOf(row+1);
            case 1:
                return rowData.getColumnValues()[1];
            case 2:
                return rowData.getColumnValues()[4];
            case 3:
                return rowData.getTime();
            case 4:
                return rowData.getColumnValues()[2];
            case 5:
                return rowData.getColumnValues()[3];
            case 6:
                return rowData.getColumnValues()[4];
            case 7:
                return "性能指标";
            default:
                return "移除";
        }
    }

    @Override
    public View getView(int row, int column, View converView, ViewGroup parent) {
        if (converView == null) {
            int resId = getLayoutResource(row, column);
            converView = getInflater().inflate(getLayoutResource(row, column), parent, false);
            if (
                    (resId == R.layout.scan_store_detail_cell_detail)
                    ||
                    (resId == R.layout.table_cell_remove)
                )
            {
                converView.setTag(getItem(row,column));
                converView.setOnClickListener(onClickListener);
            }
        }
        setText(converView, getCellString(row, column));
        return converView;
    }

    @Override
    public int getLayoutResource(int row, int column) {
        final int layoutResource;
        switch (getItemViewType(row, column)) {
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
            if (column < headers.length -3){
                return 1;
            }
            else
            if (column == headers.length -2)
            {
                return 2;
            }else{
                return 3;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }
}