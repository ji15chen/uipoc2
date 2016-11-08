package com.example.dbman.core;

import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.RawRowMapper;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/6.
 */

public class DBUtil {
    private static final String emptyString ="";
    public static final RawRowMapper<String []> stringArrayMapper = new RawRowMapper<String[]>() {
        @Override
        public String[] mapRow(String[] column, String[] result) throws SQLException {
           int i;
            for (i=0; i < result.length;i++){
                if (result[i] == null){
                    result[i] = emptyString;
                }
            }
            return result;
        }
    };

    public static String translateString(String str){
        return (str == null)?emptyString:str;
    }
}
