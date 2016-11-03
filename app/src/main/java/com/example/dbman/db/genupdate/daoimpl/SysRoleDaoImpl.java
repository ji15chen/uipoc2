package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysRoleDao;
import com.example.dbman.db.genupdate.schema.SysRole;
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole,java.util.UUID>{
public SysRoleDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysRole.class );
		}
}
