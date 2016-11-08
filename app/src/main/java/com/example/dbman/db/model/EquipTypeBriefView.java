package com.example.dbman.db.model;

/**
 * Created by jerry on 2016/11/7.
 */
import java.io.Serializable;
import java.util.UUID;
import java.math.BigDecimal;

public class EquipTypeBriefView implements  Serializable{
    private UUID id;
    private String typeName;//装备型号
    private String unit;//计量单位
    private String categoryid="单品";//管理类别
    private String limitedyear;//使用年限
    private String warrantyperiod;//保修时间
    private String scale;//退保比例

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getLimitedyear() {
        return limitedyear;
    }

    public void setLimitedyear(String limitedyear) {
        this.limitedyear = limitedyear;
    }

    public String getWarrantyperiod() {
        return warrantyperiod;
    }

    public void setWarrantyperiod(String warrantyperiod) {
        this.warrantyperiod = warrantyperiod;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
