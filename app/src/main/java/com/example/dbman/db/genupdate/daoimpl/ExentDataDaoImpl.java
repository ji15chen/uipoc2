package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.ExentDataDao;
import com.example.dbman.db.genupdate.schema.ExentData;
public class ExentDataDaoImpl extends BaseDaoImpl<ExentData,java.util.UUID> implements ExentDataDao{
public ExentDataDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ExentData.class );
		}
}
