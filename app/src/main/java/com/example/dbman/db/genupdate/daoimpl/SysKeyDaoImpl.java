package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysKeyDao;
import com.example.dbman.db.genupdate.schema.SysKey;
public class SysKeyDaoImpl extends BaseDaoImpl<SysKey,java.util.UUID>{
public SysKeyDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysKey.class );
		}
}
