package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.DepotInfoDao;
import com.example.dbman.db.genupdate.schema.DepotInfo;
public class DepotInfoDaoImpl extends BaseDaoImpl<DepotInfo,java.util.UUID>{
public DepotInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, DepotInfo.class );
		}
}
