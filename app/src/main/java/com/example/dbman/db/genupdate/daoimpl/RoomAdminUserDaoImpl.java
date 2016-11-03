package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.RoomAdminUserDao;
import com.example.dbman.db.genupdate.schema.RoomAdminUser;
public class RoomAdminUserDaoImpl extends BaseDaoImpl<RoomAdminUser,java.util.UUID>{
public RoomAdminUserDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, RoomAdminUser.class );
		}
}
