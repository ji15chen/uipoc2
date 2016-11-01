package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.dao.ExtendTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.genupdate.schema.ExtendType;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.Where;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/8.
 */

public class EquipTypeExtendOptionsModel implements Serializable {
    private static final EquipTypeDao eqDao = (EquipTypeDao) BaseDatabase.getInstance().getDaoImpl("EquipType");
    private static final ExtendTypeDao etDao = (ExtendTypeDao) BaseDatabase.getInstance().getDaoImpl("ExtendType");

    private boolean valid;
    Map<String, Map<EquipTypeExtendOption,EquipTypeExtendOption>> optionMap = new TreeMap<String, Map<EquipTypeExtendOption,EquipTypeExtendOption>>();


    public static EquipTypeExtendOptionsModel loadEquipTypeExtendOptionsModel(String subQuery) throws SQLException{

        EquipTypeExtendOptionsModel model = new EquipTypeExtendOptionsModel();
        String sql = "SELECT ExentData.ExtendID FROM ExentData \n" +
                "INNER JOIN EquipType ON ExentData.ObjectID=EquipType.PkTypeID \n" +
                " WHERE "+
                subQuery;
        GenericRawResults<UUID> uuids = eqDao.queryRaw(sql, new RawRowMapper<UUID>() {
            @Override
            public UUID mapRow(String[] col, String[] result) throws SQLException {
                return UUID.fromString(result[0]);
            }
        });

        List<ExtendType> extList = etDao.queryBuilder().where().in("ExtendID", uuids.getResults()).query();
        for (ExtendType et:extList){
            EquipTypeExtendOption option = new EquipTypeExtendOption();
            option.setExtendType(et);
            option.setName(et.getExtendName());
            option.setEnabled(false);
            option.setId(et.getExtendID());

            Map<EquipTypeExtendOption,EquipTypeExtendOption> subMap = model.optionMap.get(option.getName());
            if (subMap == null){
                subMap = new HashMap<EquipTypeExtendOption,EquipTypeExtendOption>();
                model.optionMap.put(option.getName(), subMap);
            }
            subMap.put(option,option);
        }
        return model;
    }



    public Map<String, Map<EquipTypeExtendOption, EquipTypeExtendOption>> getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(Map<String, Map<EquipTypeExtendOption, EquipTypeExtendOption>> optionMap) {
        this.optionMap = optionMap;
    }
}
