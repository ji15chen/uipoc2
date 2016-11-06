package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
public class EquipTypeDaoImpl extends BaseDaoImpl<EquipType,java.util.UUID> implements EquipTypeDao{
	private  QueryBuilder<EquipType,java.util.UUID>  queryBuilder = null ;

	public EquipTypeDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EquipType.class );
		queryBuilder = this.queryBuilder();
	}

	@Override
	public List<EquipType> findByParentUUID(UUID parentUUID) throws SQLException {
		return queryForEq("SupPkTypeID", parentUUID);
	}

	@Override
	public List<EquipType> findBySimilarTypeName(String typeName) throws SQLException{
		String arg;
		if (typeName.length()> 0){
		 	arg = "%"+typeName+"%";
		}else{
			arg = "%";
		}
		return queryBuilder.where().like("TypeName", arg).query();
	}
}
