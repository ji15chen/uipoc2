package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysParameterDao;
import com.example.dbman.db.genupdate.schema.SysParameter;
public class SysParameterDaoImpl extends BaseDaoImpl<SysParameter,java.util.UUID> implements  SysParameterDao{
public SysParameterDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysParameter.class );
		}
}
