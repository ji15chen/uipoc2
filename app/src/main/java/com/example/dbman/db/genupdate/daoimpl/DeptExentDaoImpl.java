package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.DeptExentDao;
import com.example.dbman.db.genupdate.schema.DeptExent;
public class DeptExentDaoImpl extends BaseDaoImpl<DeptExent,java.util.UUID>{
public DeptExentDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, DeptExent.class );
		}
}
