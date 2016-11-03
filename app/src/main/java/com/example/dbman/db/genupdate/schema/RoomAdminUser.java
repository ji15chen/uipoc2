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
import com.example.dbman.db.genupdate.daoimpl.RoomAdminUserDaoImpl;
@DatabaseTable(tableName = "RoomAdminUser", daoClass =RoomAdminUserDaoImpl.class)
	public class RoomAdminUser{
@DatabaseField( columnName ="RoomID",canBeNull = false )
	private UUID roomid;
@DatabaseField( columnName ="PersonID",canBeNull = false )
	private UUID personid;
@DatabaseField( columnName ="AdminID",id=true ,canBeNull = false )
	private UUID adminid;

	public UUID getRoomID(){
		return this.roomid;
	}
	public void setRoomID(UUID RoomID){
		this.roomid=RoomID;
	}
	public UUID getPersonID(){
		return this.personid;
	}
	public void setPersonID(UUID PersonID){
		this.personid=PersonID;
	}
	public UUID getAdminID(){
		return this.adminid;
	}
	public void setAdminID(UUID AdminID){
		this.adminid=AdminID;
	}

}