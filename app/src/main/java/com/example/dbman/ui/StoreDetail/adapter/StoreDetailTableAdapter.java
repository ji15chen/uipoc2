package com.example.dbman.ui.StoreDetail.adapter;

import android.content.Context;
import android.content.res.Resources;

import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.R;

import java.util.List;

public class StoreDetailTableAdapter extends SampleTableAdapter {
    private  int width;
    private  int height;
    List<EquipTypeBriefModel> model = null;
    private static final String [] headers = new String[]{"序号", "装备型号", "计量单位", "管理类别", "使用年限", "退保比例", "保修期", "详情"};


    public StoreDetailTableAdapter(Context context) {
        super(context);

        Resources resources = context.getResources();

        width = resources.getDimensionPixelSize(R.dimen.table_width);
        height = resources.getDimensionPixelSize(R.dimen.table_height);
    }

    public void setData(List<EquipTypeBriefModel> model) {
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
        EquipTypeBriefModel rowData = model.get(row );
        if (rowData == null){
            return "";
        }

        switch( column + 1){
            case 0:
                return String.valueOf(row+1);
            case 1:
                return rowData.getTypeName();
            case 2:
                return rowData.getUnit();
            case 3:
                return rowData.getCategoryid();
            case 4:
                return rowData.getLimitedyear();
            case 5:
                return rowData.getScale();
            case 6:
                return rowData.getWarrantyperiod();
            default:
                return "";
        }
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