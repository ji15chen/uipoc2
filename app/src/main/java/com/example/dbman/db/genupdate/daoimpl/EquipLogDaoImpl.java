package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EquipLogDao;
import com.example.dbman.db.genupdate.schema.EquipLog;
public class EquipLogDaoImpl extends BaseDaoImpl<EquipLog,java.util.UUID>{
public EquipLogDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EquipLog.class );
		}
}
