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
import com.example.dbman.db.genupdate.daoimpl.UserDataPermissionDaoImpl;
@DatabaseTable(tableName = "UserDataPermission", daoClass =UserDataPermissionDaoImpl.class)
	public class UserDataPermission{
	@DatabaseField(generatedId=true ,canBeNull = false )
	private UUID id;
@DatabaseField( columnName ="RoleID",id=false ,canBeNull = false )
	private UUID roleid;
@DatabaseField( columnName ="PkTypeID",id=false ,canBeNull = false )
	private UUID pktypeid;

	public UUID getRoleID(){
		return this.roleid;
	}
	public void setRoleID(UUID RoleID){
		this.roleid=RoleID;
	}
	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}

}