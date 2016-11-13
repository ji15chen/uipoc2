package com.example.dbman.db.model;

/**
 * Created by jerry on 2016/11/7.
 */
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.Where;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

public class EquipTypeBriefModel implements  Serializable{
    private static EquipTypeDao equipTypeDao = (EquipTypeDao) BaseDatabase.getInstance().getDaoImpl("EquipType");
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


    /*
* @show 查找某个装备概要信息
* */
    public static List<EquipTypeBriefModel> lookupBriefEquipTypeInfo(Where<EquipType,UUID> query)  throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();
        final String sql = "SELECT EquipType.PkTypeID,EquipType.TypeName, SysParameter.ParaName,EquipType.LimitedYear,EquipType.Scale,EquipType.WarrantyPeriod FROM EquipType LEFT OUTER  JOIN SysParameter ON EquipType.Unit= SysParameter.ParaID ";
        stringBuffer.append(sql);
        stringBuffer.append(" WHERE ");
        stringBuffer.append(query.getStatement());
        GenericRawResults<EquipTypeBriefModel> view = equipTypeDao.queryRaw(stringBuffer.toString(), new RawRowMapper<EquipTypeBriefModel>() {
            @Override
            public EquipTypeBriefModel mapRow(String[] col, String[] result) throws SQLException {
                EquipTypeBriefModel view = new EquipTypeBriefModel();
                view.setId(UUID.fromString(result[0]));
                view.setTypeName(DBUtil.translateString(result[1]));
                view.setUnit(DBUtil.translateString(result[2]));
                view.setLimitedyear(DBUtil.translateString(result[3]));
                view.setScale(DBUtil.translateString(result[4]));
                view.setCategoryid("单品");
                view.setWarrantyperiod(DBUtil.translateString(result[5]));
                return view;
            }
        });
        return view.getResults();
    }
}
