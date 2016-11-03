package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.StoreExtendChangeDao;
import com.example.dbman.db.genupdate.schema.StoreExtendChange;
public class StoreExtendChangeDaoImpl extends BaseDaoImpl<StoreExtendChange,java.util.UUID>{
public StoreExtendChangeDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, StoreExtendChange.class );
		}
}
