package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.UserDataPermissionDao;
import com.example.dbman.db.genupdate.schema.UserDataPermission;
public class UserDataPermissionDaoImpl extends BaseDaoImpl<UserDataPermission,java.util.UUID>{
public UserDataPermissionDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, UserDataPermission.class );
		}
}
