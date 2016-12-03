package com.example.dbman.ui.StoreDetail.dbmodel;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.StoreDetailDao;
import com.example.dbman.db.genupdate.schema.StoreDetail;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/12/3.
 */

public class StoreDetailTableSchema implements IDataColumn{
    private FixedDataColumn fixedDataColumn = new FixedDataColumn();
    private VariableDataColumn variableDataColumn = new VariableDataColumn();

    @Override
    public int size() {
        return fixedDataColumn.size() + variableDataColumn.size();
    }

    @Override
    public String getDataRefID(int index) {
        if (index < fixedDataColumn.size()){
            return fixedDataColumn.getDataRefID(index);
        }else{
            return variableDataColumn.getDataRefID(index - fixedDataColumn.size());
        }
    }

    @Override
    public String getColumnName(String dataRefID) {
        String name = fixedDataColumn.getColumnName(dataRefID);
        if (name != null){
            return name;
        }

        return variableDataColumn.getColumnName(dataRefID);
    }

    public static class Builder{
        private static final StoreDetailDao sdDao = (StoreDetailDao) BaseDatabase.getInstance().getDaoImpl("StoreDetail");

        public static Where<StoreDetail, UUID> filterDept(List<UUID> deptIDs) throws SQLException{
            Where<StoreDetail,UUID> where = null;
            where = sdDao.queryBuilder().where().in("DeptID", deptIDs);
            return where;
        }
        public static Where<StoreDetail, UUID> filterEquip(List<UUID> equipIDs) throws SQLException{
            Where<StoreDetail,UUID> where = null;
            where = sdDao.queryBuilder().where().in("PKTypeID", equipIDs);
            return where;
        }
        public static Where<StoreDetail, UUID> filterEquipDept(List<UUID> deptIDs, List<UUID> equipIDs) throws SQLException{
            Where<StoreDetail,UUID> where = null;
            where = sdDao.queryBuilder().where().and(filterEquip(equipIDs),filterDept(deptIDs));
            return where;
        }

        public static StoreDetailTableSchema build(Where<StoreDetail, UUID> where){
            //TODO:implement here
        }
    }
}
