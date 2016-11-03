package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysdiagramsDao;
import com.example.dbman.db.genupdate.schema.Sysdiagrams;
public class SysdiagramsDaoImpl extends BaseDaoImpl<Sysdiagrams,java.util.UUID>{
public SysdiagramsDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Sysdiagrams.class );
		}
}
