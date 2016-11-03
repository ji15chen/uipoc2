package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysLogDao;
import com.example.dbman.db.genupdate.schema.SysLog;
public class SysLogDaoImpl extends BaseDaoImpl<SysLog,java.util.UUID>{
public SysLogDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysLog.class );
		}
}
