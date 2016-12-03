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
    private String innerTableSql;


    public StoreDetailTableSchema(VariableDataColumn variableDataColumn){
        this.variableDataColumn = variableDataColumn;
        innerTableSql = buildInnerSql();
    }

    public String getInnerTableSql() {
        return innerTableSql;
    }

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

    private String extractID(final String id){
        String [] ids = id.split("_");
        return ids[0];
    }

    private String buildInnerSql(){
        String refId,refTableId;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT StoreDetailCommonDataView.*, ");
        for (int i=0;i<variableDataColumn.size();i++){
            refId = variableDataColumn.getDataRefID(i);
            refTableId = extractID(refId);
            stringBuffer.append(refTableId);
            stringBuffer.append(".ExtentValue, ");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        stringBuffer.append("FROM StoreDetailCommonDataView");
        VariableDataColumnInfo columnInfo;
        for (int i=0;i<variableDataColumn.size();i++){
            refId = variableDataColumn.getDataRefID(i);
            refTableId = extractID(refId);
            columnInfo = variableDataColumn.getColumnInfo(refId);
            stringBuffer.append(" LEFT JOIN (SELECT ObjectID,ExtentValue FROM ExentData WHERE ExtendID='");
            stringBuffer.append(columnInfo.getUuid());
            stringBuffer.append("') ");
            stringBuffer.append(refTableId);
            stringBuffer.append(" ON ");
            stringBuffer.append(refTableId);
            stringBuffer.append(".ObjectID=StoreDetailCommonDataView.StoreID\n");
        }
        return stringBuffer.toString();
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

        public static StoreDetailTableSchema build(Where<StoreDetail, UUID> where) throws SQLException{
             return sql(where.getStatement());
        }

        public static StoreDetailTableSchema build() throws SQLException{
            return sql(null);
        }

        private static StoreDetailTableSchema sql(final String sql){
            VariableDataColumn va;

            if ((sql != null) && (sql.length() > 0) ){
                String in = " (SELECT StoreID FROM StoreDetail WHERE "+sql+")";
                String INNER_SQL = " ObjectID IN "+ in;
                va = VariableDataColumn.Builder.sql(INNER_SQL);
            }else{
                va = VariableDataColumn.Builder.sql("");
            }
            StoreDetailTableSchema storeDetailTableSchema = new StoreDetailTableSchema(va);

            return storeDetailTableSchema;
        }
    }
}
