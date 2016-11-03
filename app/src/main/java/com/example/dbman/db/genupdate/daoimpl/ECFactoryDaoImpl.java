package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.ECFactoryDao;
import com.example.dbman.db.genupdate.schema.ECFactory;
public class ECFactoryDaoImpl extends BaseDaoImpl<ECFactory,java.util.UUID>{
public ECFactoryDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ECFactory.class );
		}
}
