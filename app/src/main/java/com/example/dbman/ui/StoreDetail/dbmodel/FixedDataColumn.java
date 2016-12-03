package com.example.dbman.ui.StoreDetail.dbmodel;

import java.util.HashMap;

/**
 * Created by jerry on 2016/12/2.
 */

public class FixedDataColumn implements IDataColumn{
    private static final String[] keys ={
            "使用单位",
            "装备",
            "型号",
            "编号",
            "数量",
            "单位",
            "单价",
            "状态",
            "质量等级",
            "生产日期",
            "采购日期",
            "装备日期",
            "来源",
            "来源日期",
            "责任人",
            "所属库室",
            "厂家"
    };
    private static final String [] values={
            "DeptName",
            "GenericName",
            "TypeName",
            "StoreCode",
            "Total",
            "UnitName",
            "Price",
            "StatusName",
            "QualityName",
            "ProduceDate",
            "PurchaseDate",
            "UseDate",
            "AddTypeName",
            "OtherDate",
            "PersonName",
            "RoomName",
            "FactoryName"
    };
    private static final HashMap<String,String> columnMap = new HashMap<>();

    static {
        for (int i=0;i<keys.length;i++) {
            columnMap.put(values[i],keys[i]);
        }
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public String getDataRefID(int index) {
        return values[index];
    }

    @Override
    public String getColumnName(String dataRefID) {
        return columnMap.get(dataRefID);
    }
}
