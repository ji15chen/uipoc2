package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.TmpHmdDataDao;
import com.example.dbman.db.genupdate.schema.TmpHmdData;
public class TmpHmdDataDaoImpl extends BaseDaoImpl<TmpHmdData,java.util.UUID>{
public TmpHmdDataDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, TmpHmdData.class );
		}
}
