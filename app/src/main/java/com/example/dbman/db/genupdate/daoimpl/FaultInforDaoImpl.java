package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.FaultInforDao;
import com.example.dbman.db.genupdate.schema.FaultInfor;
public class FaultInforDaoImpl extends BaseDaoImpl<FaultInfor,java.util.UUID>{
public FaultInforDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, FaultInfor.class );
		}
}
