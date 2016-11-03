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
import com.example.dbman.db.genupdate.daoimpl.FaultInforDaoImpl;
@DatabaseTable(tableName = "FaultInfor", daoClass =FaultInforDaoImpl.class)
	public class FaultInfor{
@DatabaseField( columnName ="FaultID",id=true ,canBeNull = false )
	private UUID faultid;
@DatabaseField( columnName ="StoreID")
	private UUID storeid;
@DatabaseField( columnName ="FaultTitle")
	private String faulttitle;
@DatabaseField( columnName ="FaultType")
	private UUID faulttype;
@DatabaseField( columnName ="FaultKey")
	private String faultkey;
@DatabaseField( columnName ="FaultDesc")
	private String faultdesc;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="UserID")
	private UUID userid;
@DatabaseField( columnName ="CreateDate")
	private Date createdate;
@DatabaseField( columnName ="FaultState")
	private int faultstate;
@DatabaseField( columnName ="EquipCount")
	private BigDecimal equipcount;

	public UUID getFaultID(){
		return this.faultid;
	}
	public void setFaultID(UUID FaultID){
		this.faultid=FaultID;
	}
	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public String getFaultTitle(){
		return this.faulttitle;
	}
	public void setFaultTitle(String FaultTitle){
		this.faulttitle=FaultTitle;
	}
	public UUID getFaultType(){
		return this.faulttype;
	}
	public void setFaultType(UUID FaultType){
		this.faulttype=FaultType;
	}
	public String getFaultKey(){
		return this.faultkey;
	}
	public void setFaultKey(String FaultKey){
		this.faultkey=FaultKey;
	}
	public String getFaultDesc(){
		return this.faultdesc;
	}
	public void setFaultDesc(String FaultDesc){
		this.faultdesc=FaultDesc;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getUserID(){
		return this.userid;
	}
	public void setUserID(UUID UserID){
		this.userid=UserID;
	}
	public Date getCreateDate(){
		return this.createdate;
	}
	public void setCreateDate(Date CreateDate){
		this.createdate=CreateDate;
	}
	public int getFaultState(){
		return this.faultstate;
	}
	public void setFaultState(int FaultState){
		this.faultstate=FaultState;
	}
	public BigDecimal getEquipCount(){
		return this.equipcount;
	}
	public void setEquipCount(BigDecimal EquipCount){
		this.equipcount=EquipCount;
	}

}