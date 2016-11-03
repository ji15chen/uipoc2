package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.ScanStoreDetailDao;
import com.example.dbman.db.genupdate.schema.ScanStoreDetail;
public class ScanStoreDetailDaoImpl extends BaseDaoImpl<ScanStoreDetail,java.util.UUID>{
public ScanStoreDetailDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ScanStoreDetail.class );
		}
}
