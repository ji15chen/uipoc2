package com.example.dbman.db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.EquipCardDao;
import com.example.dbman.db.genupdate.schema.EquipCard;
public class EquipCardDaoImpl extends BaseDaoImpl<EquipCard,java.util.UUID> implements EquipCardDao{
public EquipCardDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, EquipCard.class );
		}
}
