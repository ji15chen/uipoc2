package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.FactoryInfoDao;
import com.example.dbman.db.genupdate.schema.FactoryInfo;
public class FactoryInfoDaoImpl extends BaseDaoImpl<FactoryInfo,java.util.UUID>{
public FactoryInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, FactoryInfo.class );
		}
}
