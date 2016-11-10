package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EqmtInOutDao;
import com.example.dbman.db.genupdate.schema.EqmtInOut;
public class EqmtInOutDaoImpl extends BaseDaoImpl<EqmtInOut,java.util.UUID> implements EqmtInOutDao{
public EqmtInOutDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EqmtInOut.class );
		}
}
