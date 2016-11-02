package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.EquipCardDao;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.dao.StoreDetailDao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.Where;
import com.example.dbman.db.genupdate.schema.EquipCard;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


/**
 * Created by jerry on 2016/11/10.
 */

public class StoreInfoModel {
    private static final StoreDetailDao sdDao = (StoreDetailDao) BaseDatabase.getInstance().getDaoImpl("StoreDetail");
    private static final EquipTypeDao eqDao = (EquipTypeDao)BaseDatabase.getInstance().getDaoImpl("EquipType");
    private static final EquipCardDao ecDao =  (EquipCardDao)BaseDatabase.getInstance().getDaoImpl("EquipCard");

    private List<StoreInfoModelEntry> lstEquipStoreInfo = new ArrayList<StoreInfoModelEntry>();

    public List<StoreInfoModelEntry> getLstEquipStoreInfo() {
        return lstEquipStoreInfo;
    }

    public static StoreInfoModel loadEquipStoreDetail(final String query) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();
        StoreInfoModel view = new StoreInfoModel();

        //[1] 查询相关设备类型相关的工厂信息
        final String sql = "SELECT\n" +
                "Department.DeptName, pET.TypeName,EquipType.TypeName,\n" +
                "StoreDetail.StoreCode,StoreDetail.Total,paramUnit.ParaName,StoreDetail.Price,\n" +
                "paramStatus.ParaName, paramQuality.ParaName,\n" +
                "StoreDetail.ProduceDate,StoreDetail.PurchaseDate,StoreDetail.UseDate,\n" +
                "paramAddType.ParaName,StoreDetail.OtherDate,PersonInfo.PersonName,\n" +
                "DepotInfo.RoomName,FactoryInfo.FactoryName,StoreDetail.StoreID\n" +
                "FROM StoreDetail\n" +
                "LEFT JOIN Department ON Department.DeptID=StoreDetail.DeptID\n" +
                "LEFT JOIN EquipType  ON EquipType.PkTypeID=StoreDetail.PkTypeID\n" +
                "LEFT JOIN FactoryInfo ON  FactoryInfo.FactoryID=StoreDetail.FactoryID\n" +
                "LEFT JOIN DepotInfo  ON  DepotInfo.RoomID=StoreDetail.InDepot\n" +
                "LEFT JOIN PersonInfo  ON  PersonInfo.PersonID=StoreDetail.PersonLiableID\n" +
                "LEFT JOIN EquipType pET ON pET.PkTypeID=EquipType.SupPkTypeID\n" +
                "LEFT JOIN SysParameter paramUnit ON paramUnit.ParaID=EquipType.PkTypeID\n" +
                "LEFT JOIN SysParameter paramStatus ON paramStatus.ParaID=StoreDetail.UseState\n" +
                "LEFT JOIN SysParameter paramQuality ON paramQuality.ParaID=StoreDetail.QuKeyID\n" +
                "LEFT JOIN SysParameter paramAddType ON paramAddType.ParaID=StoreDetail.AddType ";

        stringBuffer.append(sql);
        stringBuffer.append(query);
        stringBuffer.append(" \n" );

        GenericRawResults<String []> v = sdDao.queryRaw(stringBuffer.toString(), DBUtil.stringArrayMapper);
        Iterator<String []> iter = v.iterator();

        while(iter.hasNext()){
            StoreInfoModelEntry entry = new StoreInfoModelEntry();
            entry.setColumnValues(iter.next());
            {
                String [] arr = entry.getColumnValues();
                entry.setUuid(arr[arr.length - 1]);
            }
            view.lstEquipStoreInfo.add(entry);
        }
        return view;
    }

    public static final String buildEquipCardQuery(String cardId) throws SQLException{
        List<EquipCard> lst = ecDao.queryForEq("RFID", cardId);
        if (lst.size()>0){
            return "WHERE StoreDetail.StoreID = '"+ lst.get(0).getStoreid()+"'";
        }else{
            return "";
        }
    }
}
