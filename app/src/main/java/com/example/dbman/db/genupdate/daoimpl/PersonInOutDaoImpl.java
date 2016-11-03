package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.PersonInOutDao;
import com.example.dbman.db.genupdate.schema.PersonInOut;
public class PersonInOutDaoImpl extends BaseDaoImpl<PersonInOut,java.util.UUID>{
public PersonInOutDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, PersonInOut.class );
		}
}
