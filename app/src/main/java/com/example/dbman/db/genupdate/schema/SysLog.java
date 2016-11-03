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
import com.example.dbman.db.genupdate.daoimpl.SysLogDaoImpl;
@DatabaseTable(tableName = "SysLog", daoClass =SysLogDaoImpl.class)
	public class SysLog{
@DatabaseField( columnName ="LogID",id=true ,canBeNull = false )
	private UUID logid;
@DatabaseField( columnName ="UserID")
	private UUID userid;
@DatabaseField( columnName ="ModelID")
	private UUID modelid;
@DatabaseField( columnName ="OperateCont")
	private String operatecont;
@DatabaseField( columnName ="ImportClass")
	private UUID importclass;
@DatabaseField( columnName ="OperateDate")
	private Date operatedate;
@DatabaseField( columnName ="OperateType")
	private UUID operatetype;

	public UUID getLogID(){
		return this.logid;
	}
	public void setLogID(UUID LogID){
		this.logid=LogID;
	}
	public UUID getUserID(){
		return this.userid;
	}
	public void setUserID(UUID UserID){
		this.userid=UserID;
	}
	public UUID getModelID(){
		return this.modelid;
	}
	public void setModelID(UUID ModelID){
		this.modelid=ModelID;
	}
	public String getOperateCont(){
		return this.operatecont;
	}
	public void setOperateCont(String OperateCont){
		this.operatecont=OperateCont;
	}
	public UUID getImportClass(){
		return this.importclass;
	}
	public void setImportClass(UUID ImportClass){
		this.importclass=ImportClass;
	}
	public Date getOperateDate(){
		return this.operatedate;
	}
	public void setOperateDate(Date OperateDate){
		this.operatedate=OperateDate;
	}
	public UUID getOperateType(){
		return this.operatetype;
	}
	public void setOperateType(UUID OperateType){
		this.operatetype=OperateType;
	}

}