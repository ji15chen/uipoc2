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
import com.example.dbman.db.genupdate.daoimpl.StoreExtendChangeDaoImpl;
@DatabaseTable(tableName = "StoreExtendChange", daoClass =StoreExtendChangeDaoImpl.class)
	public class StoreExtendChange{
@DatabaseField( columnName ="ChangeID",id=true ,canBeNull = false )
	private UUID changeid;
@DatabaseField( columnName ="StoreID")
	private UUID storeid;
@DatabaseField( columnName ="ExtendID")
	private UUID extendid;
@DatabaseField( columnName ="ChangeOld")
	private String changeold;
@DatabaseField( columnName ="ChangeNew")
	private String changenew;
@DatabaseField( columnName ="ChangeDate")
	private Date changedate;
@DatabaseField( columnName ="ChangeUserID")
	private UUID changeuserid;

	public UUID getChangeID(){
		return this.changeid;
	}
	public void setChangeID(UUID ChangeID){
		this.changeid=ChangeID;
	}
	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public UUID getExtendID(){
		return this.extendid;
	}
	public void setExtendID(UUID ExtendID){
		this.extendid=ExtendID;
	}
	public String getChangeOld(){
		return this.changeold;
	}
	public void setChangeOld(String ChangeOld){
		this.changeold=ChangeOld;
	}
	public String getChangeNew(){
		return this.changenew;
	}
	public void setChangeNew(String ChangeNew){
		this.changenew=ChangeNew;
	}
	public Date getChangeDate(){
		return this.changedate;
	}
	public void setChangeDate(Date ChangeDate){
		this.changedate=ChangeDate;
	}
	public UUID getChangeUserID(){
		return this.changeuserid;
	}
	public void setChangeUserID(UUID ChangeUserID){
		this.changeuserid=ChangeUserID;
	}

}