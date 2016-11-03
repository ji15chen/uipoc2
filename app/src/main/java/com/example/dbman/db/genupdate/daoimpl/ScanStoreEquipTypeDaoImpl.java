package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.ScanStoreEquipTypeDao;
import com.example.dbman.db.genupdate.schema.ScanStoreEquipType;
public class ScanStoreEquipTypeDaoImpl extends BaseDaoImpl<ScanStoreEquipType,java.util.UUID>{
public ScanStoreEquipTypeDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ScanStoreEquipType.class );
		}
}
