package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
public class SysFileInfoDaoImpl extends BaseDaoImpl<SysFileInfo,java.util.UUID>{
public SysFileInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysFileInfo.class );
		}
}
