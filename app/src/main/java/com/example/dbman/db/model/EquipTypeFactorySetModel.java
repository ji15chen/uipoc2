package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.ECFactoryDao;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.dao.FactoryInfoDao;
import com.example.dbman.db.genupdate.schema.ECFactory;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/9.
 */

public class EquipTypeFactorySetModel {
    private static final FactoryInfoDao fiDao = (FactoryInfoDao)BaseDatabase.getInstance().getDaoImpl("FactoryInfo");
    private static final EquipTypeDao eqDao = (EquipTypeDao)BaseDatabase.getInstance().getDaoImpl("EquipType");
    private static final ECFactoryDao  ecfDao = (ECFactoryDao) BaseDatabase.getInstance().getDaoImpl("ECFactory");

    private List<FactoryDesc> lstEquipFactories = new ArrayList<FactoryDesc>();


    public List<FactoryDesc> getLstEquipFactories() {
        return lstEquipFactories;
    }

    public void setLstEquipFactories(List<FactoryDesc> lstEquipFactories) {
        this.lstEquipFactories = lstEquipFactories;
    }

    public static Where<EquipType,UUID> getEquipIdOfFactoryQuery(final UUID factoryId) throws SQLException{
        List<ECFactory> lstFactory = ecfDao.queryForEq("FactoryID", factoryId);
        List<UUID> uuids = new ArrayList<UUID>();
        if (lstFactory.size() > 0){
            for (ECFactory factory:lstFactory){
                uuids.add(factory.getPkTypeID());
            }
        }else{
            uuids.add(Constants.NULL_UUID);
        }
        Where<EquipType,UUID> where = eqDao.queryBuilder().where().in("PkTypeID", uuids);
        return  where;
    }

    public static EquipTypeFactorySetModel loadEquipFactorySet(final UUID uuid) throws SQLException{
        StringBuffer stringBuffer = new StringBuffer();
        EquipTypeFactorySetModel view = new EquipTypeFactorySetModel();
        //[1] 查询相关设备类型相关的工厂信息
        final String sql = "SELECT fi.FactoryID,fi.FactoryName,\n" +
                "                paraFactoryType.ParaName,\n" +
                "                paraProvince.ParaName,\n" +
                "                paraCity.ParaName,\n" +
                "                paraDistrict.ParaName,\n" +
                "                fi.Address,\n" +
                "                fi.PostCode,\n" +
                "                fi.FirstMan,\n" +
                "                fi.Remark,\n" +
                "                fi.Descn,\n" +
                "                fi.Telephone\n" +
                "        FROM FactoryInfo fi\n" +
                "        INNER JOIN SysParameter paraFactoryType ON  paraFactoryType.ParaID=fi.FactoryType\n" +
                "        INNER JOIN SysParameter paraProvince ON  \t\tparaProvince.ParaID=fi.Province\n" +
                "        INNER JOIN SysParameter paraCity ON  \t\t\t\tparaCity.ParaID=fi.City\n" +
                "        INNER JOIN SysParameter paraDistrict ON  \t\t\t\tparaDistrict.ParaID=fi.District\n" +
                "        WHERE fi.FactoryID IN (SELECT FactoryID FROM ECFactory WHERE PkTypeID = '";

        stringBuffer.append(sql);
        stringBuffer.append(uuid.toString().toUpperCase());
        stringBuffer.append("')\n" );

        GenericRawResults<String []> v = fiDao.queryRaw(stringBuffer.toString(), DBUtil.stringArrayMapper);
        Iterator<String []> iter = v.iterator();

        while(iter.hasNext()){
            String [] value = iter.next();
            FactoryDesc fd = new FactoryDesc();
            fd.setFactoryid(UUID.fromString(value[0]));
            fd.setFactoryname(value[1]);
            fd.setProvince(value[2]);
            fd.setCity(value[3]);
            fd.setDistrict(value[4]);
            fd.setAddress(value[5]);
            fd.setPostcode(value[6]);
            fd.setFirstman(value[7]);
            fd.setRemark(value[8]);
            fd.setDescn(value[9]);
            fd.setTelephone(value[10]);
            view.lstEquipFactories.add(fd);
        }
        return view;
    }
}
