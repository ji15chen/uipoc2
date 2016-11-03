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
import com.example.dbman.db.genupdate.daoimpl.EquipLogDaoImpl;
@DatabaseTable(tableName = "EquipLog", daoClass =EquipLogDaoImpl.class)
	public class EquipLog{
@DatabaseField( columnName ="LogID")
	private UUID logid;
@DatabaseField( columnName ="SupLogID")
	private UUID suplogid;
@DatabaseField( columnName ="EquipID")
	private UUID equipid;
@DatabaseField( columnName ="PkTypeID")
	private UUID pktypeid;
@DatabaseField( columnName ="Total")
	private int total;
@DatabaseField( columnName ="QuKeyID")
	private UUID qukeyid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="OperationType")
	private UUID operationtype;
@DatabaseField( columnName ="OperationDate")
	private Date operationdate;

	public UUID getLogID(){
		return this.logid;
	}
	public void setLogID(UUID LogID){
		this.logid=LogID;
	}
	public UUID getSupLogID(){
		return this.suplogid;
	}
	public void setSupLogID(UUID SupLogID){
		this.suplogid=SupLogID;
	}
	public UUID getEquipID(){
		return this.equipid;
	}
	public void setEquipID(UUID EquipID){
		this.equipid=EquipID;
	}
	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}
	public int getTotal(){
		return this.total;
	}
	public void setTotal(int Total){
		this.total=Total;
	}
	public UUID getQuKeyID(){
		return this.qukeyid;
	}
	public void setQuKeyID(UUID QuKeyID){
		this.qukeyid=QuKeyID;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getOperationType(){
		return this.operationtype;
	}
	public void setOperationType(UUID OperationType){
		this.operationtype=OperationType;
	}
	public Date getOperationDate(){
		return this.operationdate;
	}
	public void setOperationDate(Date OperationDate){
		this.operationdate=OperationDate;
	}

}