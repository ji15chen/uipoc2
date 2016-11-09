package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.DepartmentDao;
import com.example.dbman.db.genupdate.schema.Department;
public class DepartmentDaoImpl extends BaseDaoImpl<Department,java.util.UUID> implements DepartmentDao{
public DepartmentDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Department.class );
		}
}
