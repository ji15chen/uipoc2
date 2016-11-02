package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.db.model.CpntDesc;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.R;

import java.util.List;

public class PowerIndicatorDetailCpntAdapter extends SampleTableAdapter {
    private  int width;
    private  int height;
    List<CpntDesc> model = null;
    private static final String [] headers = new String[]{"序号", "名称", "单位", "数量", "年限"};

    public PowerIndicatorDetailCpntAdapter(Context context) {
        super(context);
        Resources resources = context.getResources();

        width = resources.getDimensionPixelSize(R.dimen.table_width);
        height = resources.getDimensionPixelSize(R.dimen.table_height);
    }

    @Override
    public Object getItem(int row, int col) {
        return model.get(row);
    }

    public void setData(List<CpntDesc> model) {
        this.model = model;
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
        CpntDesc rowData = model.get(row );
        if (rowData == null){
            return "";
        }

        switch( column + 1){
            case 0:
                return String.valueOf(row+1);
            case 1:
                return rowData.getCpntTypes().getCpntName();
            case 2:
                return rowData.getCpntTypes().getCpntUnit();
            case 3:
                return String.valueOf( rowData.getCount());
            case 4:
                return String.valueOf(rowData.getCpntTypes().getCpntyear());
            default:
                return "";
        }
    }

    @Override
    public View getView(int row, int column, View converView, ViewGroup parent) {
        if (converView == null) {
            int resId = getLayoutResource(row, column);
            converView = getInflater().inflate(getLayoutResource(row, column), parent, false);
            if (resId == R.layout.powerindicator_table_detail){
                converView.setTag(getItem(row,column));
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
            default:
                throw new RuntimeException("wtf?");
        }
        return layoutResource;
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (row < 0) {
            return 0;
        } else {
                return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}