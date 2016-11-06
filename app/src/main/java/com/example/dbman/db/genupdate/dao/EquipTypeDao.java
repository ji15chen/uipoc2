package com.example.dbman.db.genupdate.dao;

import com.j256.ormlite.dao.Dao;
import com.example.dbman.db.genupdate.schema.EquipType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface EquipTypeDao extends Dao<EquipType,java.util.UUID>{
    List<EquipType> findByParentUUID(UUID parentUUID) throws SQLException;
    List<EquipType> findBySimilarTypeName(final String typeName) throws SQLException;


}
