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
import com.example.dbman.db.genupdate.daoimpl.DeptExentDaoImpl;
@DatabaseTable(tableName = "DeptExent", daoClass =DeptExentDaoImpl.class)
	public class DeptExent{
@DatabaseField( columnName ="ExentID",id=true ,canBeNull = false )
	private UUID exentid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="ExentName")
	private String exentname;
@DatabaseField( columnName ="ExentValue")
	private String exentvalue;
@DatabaseField( columnName ="ExentSort")
	private int exentsort;

	public UUID getExentID(){
		return this.exentid;
	}
	public void setExentID(UUID ExentID){
		this.exentid=ExentID;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public String getExentName(){
		return this.exentname;
	}
	public void setExentName(String ExentName){
		this.exentname=ExentName;
	}
	public String getExentValue(){
		return this.exentvalue;
	}
	public void setExentValue(String ExentValue){
		this.exentvalue=ExentValue;
	}
	public int getExentSort(){
		return this.exentsort;
	}
	public void setExentSort(int ExentSort){
		this.exentsort=ExentSort;
	}

}