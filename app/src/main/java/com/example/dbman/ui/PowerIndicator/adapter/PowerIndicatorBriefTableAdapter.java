package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.ScanStoreDetailBasicTableAdapter;

import java.util.Iterator;
import java.util.List;

public class PowerIndicatorBriefTableAdapter extends SampleTableAdapter  {
    private  int width;
    private  int height;
    List<EquipTypeBriefModel> model = null;
    private static final String [] headers = new String[]{"序号", "装备型号", "计量单位", "管理类别", "使用年限", "退保比例", "保修期", "详情"};
    private View.OnClickListener onClickListener;
    private Resources resources;

    public PowerIndicatorBriefTableAdapter(Context context, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
        this.resources = context.getResources();
        width = resources.getDimensionPixelSize(R.dimen.table_width);
        height = resources.getDimensionPixelSize(R.dimen.table_height);
    }

    @Override
    public Object getItem(int row, int col) {
        return model.get(row);
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
            case 7:
                return "详情";
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
                layoutResource = R.layout.powerindicator_table_detail;
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
            if (column >= headers.length -2){
                return 2;
            }else {
                return 1;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }


}