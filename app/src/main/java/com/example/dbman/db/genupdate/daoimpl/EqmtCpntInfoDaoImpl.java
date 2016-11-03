package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EqmtCpntInfoDao;
import com.example.dbman.db.genupdate.schema.EqmtCpntInfo;
public class EqmtCpntInfoDaoImpl extends BaseDaoImpl<EqmtCpntInfo,java.util.UUID>{
public EqmtCpntInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EqmtCpntInfo.class );
		}
}
