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
import com.example.dbman.db.genupdate.daoimpl.ScanStoreDaoImpl;
@DatabaseTable(tableName = "ScanStore", daoClass =ScanStoreDaoImpl.class)
	public class ScanStore{
@DatabaseField( columnName ="SCanID",id=true ,canBeNull = false )
	private UUID scanid;
@DatabaseField( columnName ="PSCanID")
	private UUID pscanid;
@DatabaseField( columnName ="Topic")
	private String topic;
@DatabaseField( columnName ="SCanTime")
	private Date scantime;
@DatabaseField( columnName ="ScanPerson")
	private UUID scanperson;
@DatabaseField( columnName ="StartTime")
	private Date starttime;
@DatabaseField( columnName ="EndTime")
	private Date endtime;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="State")
	private int state;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="IncDecType")
	private UUID incdectype;
@DatabaseField( columnName ="IncDecCount")
	private BigDecimal incdeccount;

	public UUID getSCanID(){
		return this.scanid;
	}
	public void setSCanID(UUID SCanID){
		this.scanid=SCanID;
	}
	public UUID getPSCanID(){
		return this.pscanid;
	}
	public void setPSCanID(UUID PSCanID){
		this.pscanid=PSCanID;
	}
	public String getTopic(){
		return this.topic;
	}
	public void setTopic(String Topic){
		this.topic=Topic;
	}
	public Date getSCanTime(){
		return this.scantime;
	}
	public void setSCanTime(Date SCanTime){
		this.scantime=SCanTime;
	}
	public UUID getScanPerson(){
		return this.scanperson;
	}
	public void setScanPerson(UUID ScanPerson){
		this.scanperson=ScanPerson;
	}
	public Date getStartTime(){
		return this.starttime;
	}
	public void setStartTime(Date StartTime){
		this.starttime=StartTime;
	}
	public Date getEndTime(){
		return this.endtime;
	}
	public void setEndTime(Date EndTime){
		this.endtime=EndTime;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public int getState(){
		return this.state;
	}
	public void setState(int State){
		this.state=State;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}
	public UUID getIncDecType(){
		return this.incdectype;
	}
	public void setIncDecType(UUID IncDecType){
		this.incdectype=IncDecType;
	}
	public BigDecimal getIncDecCount(){
		return this.incdeccount;
	}
	public void setIncDecCount(BigDecimal IncDecCount){
		this.incdeccount=IncDecCount;
	}

}