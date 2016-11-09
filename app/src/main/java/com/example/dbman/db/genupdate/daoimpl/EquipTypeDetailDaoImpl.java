package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EquipTypeDetailDao;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
public class EquipTypeDetailDaoImpl extends BaseDaoImpl<EquipTypeDetail,java.util.UUID> implements  EquipTypeDetailDao{
public EquipTypeDetailDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EquipTypeDetail.class );
		}
}
