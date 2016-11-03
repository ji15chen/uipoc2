package com.example.dbman.db.genupdate.schema;

	/**
	*	*@author jichen
	*/

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Date;
import com.example.dbman.db.genupdate.daoimpl.RoleMenuDaoImpl;
@DatabaseTable(tableName = "RoleMenu", daoClass =RoleMenuDaoImpl.class)
	public class RoleMenu{
@DatabaseField( columnName ="RoleID",id=false ,canBeNull = false )
	private UUID roleid;
@DatabaseField( columnName ="MenuID",id=false ,canBeNull = false )
	private UUID menuid;

	@DatabaseField(id=true, useGetSet=true)
	private UUID id;

	public UUID getId() {
		return new UUID(roleid.getMostSignificantBits()+menuid.getMostSignificantBits(), roleid.getLeastSignificantBits()+menuid.getLeastSignificantBits());
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRoleID(){
		return this.roleid;
	}
	public void setRoleID(UUID RoleID){
		this.roleid=RoleID;
	}
	public UUID getMenuID(){
		return this.menuid;
	}
	public void setMenuID(UUID MenuID){
		this.menuid=MenuID;
	}

}