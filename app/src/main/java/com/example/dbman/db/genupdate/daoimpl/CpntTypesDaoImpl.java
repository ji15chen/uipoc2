package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.CpntTypesDao;
import com.example.dbman.db.genupdate.schema.CpntTypes;
public class CpntTypesDaoImpl extends BaseDaoImpl<CpntTypes,java.util.UUID>{
public CpntTypesDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, CpntTypes.class );
		}
}
