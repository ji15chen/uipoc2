package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.CpntTypesDao;
import com.example.dbman.db.genupdate.dao.EqmtCpntInfoDao;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.CpntTypes;
import com.j256.ormlite.dao.GenericRawResults;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/9.
 */

public class EquipTypeCpntSetModel {
    private static final EqmtCpntInfoDao eciDao = (EqmtCpntInfoDao) BaseDatabase.getInstance().getDaoImpl("EqmtCpntInfo");
    private static final CpntTypesDao cpntDao = (CpntTypesDao)BaseDatabase.getInstance().getDaoImpl("CpntTypes");
    private static final EquipTypeDao eqDao = (EquipTypeDao)BaseDatabase.getInstance().getDaoImpl("EquipType");
    private List<CpntDesc> lstEquipCpnts = new ArrayList<CpntDesc>();


    public List<CpntDesc> getLstEquipCpnts() {
        return lstEquipCpnts;
    }

    public void setLstEquipCpnts(List<CpntDesc> lstEquipCpnts) {
        this.lstEquipCpnts = lstEquipCpnts;
    }

    public static EquipTypeCpntSetModel loadEquipCpntSet(final UUID uuid, boolean isMaster) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();
        EquipTypeCpntSetModel view = new EquipTypeCpntSetModel();

        //[1] 查询相关设备类型相关的工厂信息
        final String sql = "SELECT CpntTypes.*,EqmtCpntInfo.CpntCount, EqmtCpntInfo.IsMaster FROM EqmtCpntInfo\n" +
                "  LEFT OUTER JOIN CpntTypes ON CpntTypes.CpntID=EqmtCpntInfo.CpntID\n" +
                "  WHERE EqmtCpntInfo.PkTypeID='";

        stringBuffer.append(sql);
        stringBuffer.append(uuid.toString().toUpperCase());
        stringBuffer.append("' AND EqmtCpntInfo.IsMaster = '" +String.valueOf(isMaster));
        stringBuffer.append("' \n" );

        GenericRawResults<String []> v = cpntDao.queryRaw(stringBuffer.toString(), DBUtil.stringArrayMapper);
        Iterator<String []> iter = v.iterator();

        while(iter.hasNext()){
            String [] value = iter.next();
            CpntTypes ct = new CpntTypes();
            CpntDesc fd = new CpntDesc();
            ct.setCpntID(UUID.fromString(value[0]));
            ct.setCpntName(value[1]);
            ct.setCpntType(value[2]);
            ct.setCpntUnit(value[3]);
            ct.setCpntyear(Integer.parseInt(value[4]));
            ct.setCpntFunc(value[5]);
            ct.IsPublic(Boolean.parseBoolean(value[6]));
            ct.setCpntDesc(value[7]);
            fd.setCount(Integer.parseInt(value[8]));
            fd.setCpntTypes(ct);
            fd.setMaster(Boolean.parseBoolean(value[9]));
            view.lstEquipCpnts.add(fd);
        }
        return view;
    }


}
