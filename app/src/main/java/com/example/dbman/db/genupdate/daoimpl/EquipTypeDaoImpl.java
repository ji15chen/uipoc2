package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
public class EquipTypeDaoImpl extends BaseDaoImpl<EquipType,java.util.UUID>{
public EquipTypeDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EquipType.class );
		}
}
