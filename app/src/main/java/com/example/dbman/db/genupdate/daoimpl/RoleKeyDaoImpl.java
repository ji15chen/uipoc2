package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.RoleKeyDao;
import com.example.dbman.db.genupdate.schema.RoleKey;
public class RoleKeyDaoImpl extends BaseDaoImpl<RoleKey,java.util.UUID>{
public RoleKeyDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, RoleKey.class );
		}
}
