package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysUserDao;
import com.example.dbman.db.genupdate.schema.SysUser;
public class SysUserDaoImpl extends BaseDaoImpl<SysUser,java.util.UUID>{
public SysUserDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysUser.class );
		}
}
