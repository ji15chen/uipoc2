package com.example.dbman.db.genupdate.daoimpl;
import com.example.dbman.core.Constants;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
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

	/*
	* @show 通过父UUID 查找所有子对象
	* */
	@Override
	public CloseableIterator<EquipType> findByParentUUID(UUID parentUUID) throws SQLException {
		return queryBuilder.where().eq("SupPkTypeID", parentUUID).iterator();
	}

	/*
	* @show 通过通过名称模糊查找所有对象
	* */
	@Override
	public CloseableIterator<EquipType> findBySimilarTypeName(String typeName) throws SQLException{
		return findBySimilarTypeNameQuery(typeName).iterator();
	}

	/*
	* @show 查找某个结点下属所有叶子节点
	* */
	@Override
	public Where<EquipType,UUID> getLeafEquipByParentUUIDQuery(UUID ... parentUUIDs) throws SQLException {
		final Where<EquipType,UUID> nullWhere = queryBuilder.where().in("PkTypeID", Constants.NULL_UUID);
		HashMap<UUID,UUID> uuids = new HashMap<UUID,UUID>();
		for (UUID parentUUID:parentUUIDs) {
			tryAdd(uuids, parentUUID);
		}

		if (uuids.size() <= 0){
			return nullWhere;
		}else{
			List<UUID> lst = new ArrayList<UUID>();
			for (UUID uuid:uuids.keySet()){
				lst.add(uuid);
			}
			return queryBuilder.where().in("PkTypeID", lst);
		}
	}
	/*
* @show 查找某个结点下属所有叶子节点
* */
	@Override
	public Where<EquipType,UUID> getLeafEquipByParentObjectsQuery(Iterator<EquipType> iterator) throws SQLException{
		final Where<EquipType,UUID> nullWhere = queryBuilder.where().in("PkTypeID", Constants.NULL_UUID);
		HashMap<UUID,UUID> uuids = new HashMap<UUID,UUID>();
		while (iterator.hasNext()){
			tryAdd(uuids, iterator.next().getPkTypeID());
		}

		if (uuids.size() <= 0){
			return nullWhere;
		}else{
			List<UUID> lst = new ArrayList<UUID>();
			for (UUID uuid:uuids.keySet()){
				lst.add(uuid);
			}
			return queryBuilder.where().in("PkTypeID", lst);
		}
	}

	private  void tryAdd(HashMap<UUID,UUID>uuids, final UUID pId) throws SQLException{
		CloseableIterator<EquipType> iter = findByParentUUID(pId);
		if ( (iter == null) || (iter.hasNext() == false) ){
			uuids.put(pId,pId);
		}else{
			try {
				while (iter.hasNext()) {
					tryAdd(uuids, iter.next().getPkTypeID());
				}
			}finally {
				iter.closeQuietly();
			}
		}
	}

	@Override
	public Where<EquipType,UUID> findBySimilarTypeNameQuery(String typeName) throws SQLException {
		String arg;
		if (typeName.length()> 0){
			arg = "%"+typeName+"%";
		}else{
			arg = "%";
		}
		return  queryBuilder.where().like("TypeName", arg);
	}

	/*
	* @show 查找某个装备概要信息
	* */
	@Override
	public CloseableIterator<EquipTypeBriefModel> lookupBriefEquipTypeInfo(Where<EquipType,UUID> query)  throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();
        final String sql = "SELECT EquipType.PkTypeID,EquipType.TypeName, SysParameter.ParaName,EquipType.LimitedYear,EquipType.Scale,EquipType.WarrantyPeriod FROM EquipType LEFT OUTER  JOIN SysParameter ON EquipType.Unit= SysParameter.ParaID ";
        stringBuffer.append(sql);
        stringBuffer.append(" WHERE ");
        stringBuffer.append(query.getStatement());
        GenericRawResults<EquipTypeBriefModel> view = queryRaw(stringBuffer.toString(), new RawRowMapper<EquipTypeBriefModel>() {
            @Override
            public EquipTypeBriefModel mapRow(String[] col, String[] result) throws SQLException {
                EquipTypeBriefModel view = new EquipTypeBriefModel();
                view.setId(UUID.fromString(result[0]));
                view.setTypeName(DBUtil.translateString(result[1]));
                view.setUnit(DBUtil.translateString(result[2]));
                view.setLimitedyear(DBUtil.translateString(result[3]));
                view.setScale(DBUtil.translateString(result[4]));
                view.setCategoryid("单品");
                view.setWarrantyperiod(DBUtil.translateString(result[5]));
                return view;
            }
        });
        return view.closeableIterator();
    }



}
