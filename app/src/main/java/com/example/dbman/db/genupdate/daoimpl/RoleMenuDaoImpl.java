package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.RoleMenuDao;
import com.example.dbman.db.genupdate.schema.RoleMenu;
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu,java.util.UUID>{
public RoleMenuDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, RoleMenu.class );
		}
}
