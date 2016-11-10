package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.StoreDetailDao;
import com.example.dbman.db.genupdate.schema.StoreDetail;
public class StoreDetailDaoImpl extends BaseDaoImpl<StoreDetail,java.util.UUID> implements StoreDetailDao{
public StoreDetailDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, StoreDetail.class );
		}
}
