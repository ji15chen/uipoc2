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
import com.example.dbman.db.genupdate.daoimpl.EqmtInOutDaoImpl;
@DatabaseTable(tableName = "EqmtInOut", daoClass =EqmtInOutDaoImpl.class)
	public class EqmtInOut{
@DatabaseField( columnName ="EIOID",id=true ,canBeNull = false )
	private UUID eioid;
@DatabaseField( columnName ="PIOID")
	private UUID pioid;
@DatabaseField( columnName ="StoreID",canBeNull = false )
	private UUID storeid;
@DatabaseField( columnName ="Reason")
	private String reason;
@DatabaseField( columnName ="ScanTime")
	private Date scantime;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="TakeMan")
	private UUID takeman;

	public UUID getEIOID(){
		return this.eioid;
	}
	public void setEIOID(UUID EIOID){
		this.eioid=EIOID;
	}
	public UUID getPIOID(){
		return this.pioid;
	}
	public void setPIOID(UUID PIOID){
		this.pioid=PIOID;
	}
	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public String getReason(){
		return this.reason;
	}
	public void setReason(String Reason){
		this.reason=Reason;
	}
	public Date getScanTime(){
		return this.scantime;
	}
	public void setScanTime(Date ScanTime){
		this.scantime=ScanTime;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}
	public UUID getTakeMan(){
		return this.takeman;
	}
	public void setTakeMan(UUID TakeMan){
		this.takeman=TakeMan;
	}

}