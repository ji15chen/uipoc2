package com.example.dbman.ui.StoreDetail.dbmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jerry on 2016/12/3.
 */

public class VariableDataColumnInfo implements Serializable{
    private String Name;
    private String uuid;
    private String unit;
    private List<String> selectValues;

    public String getName() {
        return Name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(List<String> selectValues) {
        this.selectValues = selectValues;
    }
}
