package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysMenuDao;
import com.example.dbman.db.genupdate.schema.SysMenu;
public class SysMenuDaoImpl extends BaseDaoImpl<SysMenu,java.util.UUID>{
public SysMenuDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysMenu.class );
		}
}
