package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.CheckMntcInfoDao;
import com.example.dbman.db.genupdate.schema.CheckMntcInfo;
public class CheckMntcInfoDaoImpl extends BaseDaoImpl<CheckMntcInfo,java.util.UUID>{
public CheckMntcInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, CheckMntcInfo.class );
		}
}
