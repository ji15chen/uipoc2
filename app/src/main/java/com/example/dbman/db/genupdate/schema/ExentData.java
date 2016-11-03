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
import com.example.dbman.db.genupdate.daoimpl.ExentDataDaoImpl;
@DatabaseTable(tableName = "ExentData", daoClass =ExentDataDaoImpl.class)
	public class ExentData{
@DatabaseField( columnName ="ObjectID")
	private UUID objectid;
@DatabaseField( columnName ="ExtendID")
	private UUID extendid;
@DatabaseField( columnName ="ExtentValue")
	private String extentvalue;
@DatabaseField( columnName ="ExtentSort")
	private int extentsort;

	public UUID getObjectID(){
		return this.objectid;
	}
	public void setObjectID(UUID ObjectID){
		this.objectid=ObjectID;
	}
	public UUID getExtendID(){
		return this.extendid;
	}
	public void setExtendID(UUID ExtendID){
		this.extendid=ExtendID;
	}
	public String getExtentValue(){
		return this.extentvalue;
	}
	public void setExtentValue(String ExtentValue){
		this.extentvalue=ExtentValue;
	}
	public int getExtentSort(){
		return this.extentsort;
	}
	public void setExtentSort(int ExtentSort){
		this.extentsort=ExtentSort;
	}

}