package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.ExtendTypeDao;
import com.example.dbman.db.genupdate.schema.ExtendType;
public class ExtendTypeDaoImpl extends BaseDaoImpl<ExtendType,java.util.UUID>{
public ExtendTypeDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ExtendType.class );
		}
}
