package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.PersonInfoDao;
import com.example.dbman.db.genupdate.schema.PersonInfo;
public class PersonInfoDaoImpl extends BaseDaoImpl<PersonInfo,java.util.UUID> implements PersonInfoDao{
public PersonInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, PersonInfo.class );
		}
}
