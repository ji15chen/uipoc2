package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.WorkersDao;
import com.example.dbman.db.genupdate.schema.Workers;
public class WorkersDaoImpl extends BaseDaoImpl<Workers,java.util.UUID>{
public WorkersDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Workers.class );
		}
}
