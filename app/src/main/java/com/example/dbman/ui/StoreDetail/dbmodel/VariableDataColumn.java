package com.example.dbman.ui.StoreDetail.dbmodel;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.StoreDetailDao;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jerry on 2016/12/3.
 */

public class VariableDataColumn implements IDataColumn{

    private  HashMap<String,VariableDataColumnInfo> columnMap = new HashMap<>();
    private  String [] dataRefIDs = null;

    @Override
    public int size() {
        return columnMap.size();
    }

    @Override
    public String getDataRefID(int index) {
        if (dataRefIDs == null) {
            return null;
        }else{
            return dataRefIDs[index];
        }
    }

    @Override
    public String getColumnName(String dataRefID) {
        VariableDataColumnInfo variableDataColumnInfo = columnMap.get(dataRefID);
        if (variableDataColumnInfo == null) {
            return null;
        }
        return  variableDataColumnInfo.getName()+"("+variableDataColumnInfo.getUnit()+")";
    }
    public VariableDataColumnInfo getColumnInfo(String id){
        return columnMap.get(id);
    }

    public static class Builder{
        private static final StoreDetailDao sdDao = (StoreDetailDao) BaseDatabase.getInstance().getDaoImpl("StoreDetail");
        public static String valueIdFromUUID(String uuid){
            return "ATTR"+uuid.replace("-","").toUpperCase().concat("_VALUE");
        }

        public static VariableDataColumn sql(String condition){
            String sql = "SELECT * FROM ExtendAttrView ";
            if ( (condition != null) && (condition.length() > 0)){
                sql = sql +" WHERE" + condition;
            }
            GenericRawResults<String []> values = null;
            try {
                values = sdDao.queryRaw(sql, DBUtil.stringArrayMapper);
            }catch (Exception e){
                e.printStackTrace();
            }
            VariableDataColumn variableDataColumn = new VariableDataColumn();
            if (values != null){
                CloseableIterator<String []> iterator = values.closeableIterator();
                try {
                    while (iterator.hasNext()) {
                        String [] value = iterator.nextThrow();
                        String id = value[1];
                        VariableDataColumnInfo info = new VariableDataColumnInfo();
                        info.setUuid(value[1]);
                        info.setName(value[2]);
                        info.setUnit(value[3]);
                        List<String> lstSelectables = new ArrayList<>();
                        String [] selectables = value[4].split(Constants.DB_ARRAY_SPLITTER);
                        if (selectables.length > 0){
                            for (String elem:selectables) {
                                lstSelectables.add(elem);
                            }
                        }
                        info.setSelectValues(lstSelectables);
                        variableDataColumn.columnMap.put(valueIdFromUUID(info.getUuid()), info);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    iterator.closeQuietly();
                }
                variableDataColumn.dataRefIDs = variableDataColumn.columnMap.keySet().toArray(new String [0]);
            }
            return variableDataColumn;
        }
    }

}
