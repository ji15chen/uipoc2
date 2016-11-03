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
import com.example.dbman.db.genupdate.daoimpl.ExtendTypeDaoImpl;
@DatabaseTable(tableName = "ExtendType", daoClass =ExtendTypeDaoImpl.class)
	public class ExtendType{
@DatabaseField( columnName ="ExtendID",id=true ,canBeNull = false )
	private UUID extendid;
@DatabaseField( columnName ="ExtendCode")
	private String extendcode;
@DatabaseField( columnName ="ExtendName")
	private String extendname;
@DatabaseField( columnName ="ExtendType")
	private UUID extendtype;
@DatabaseField( columnName ="ExtendUnit")
	private UUID extendunit;
@DatabaseField( columnName ="ExtendMin")
	private BigDecimal extendmin;
@DatabaseField( columnName ="ExtendMax")
	private BigDecimal extendmax;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="TypeCode")
	private UUID typecode;

	public UUID getExtendID(){
		return this.extendid;
	}
	public void setExtendID(UUID ExtendID){
		this.extendid=ExtendID;
	}
	public String getExtendCode(){
		return this.extendcode;
	}
	public void setExtendCode(String ExtendCode){
		this.extendcode=ExtendCode;
	}
	public String getExtendName(){
		return this.extendname;
	}
	public void setExtendName(String ExtendName){
		this.extendname=ExtendName;
	}
	public UUID getExtendType(){
		return this.extendtype;
	}
	public void setExtendType(UUID ExtendType){
		this.extendtype=ExtendType;
	}
	public UUID getExtendUnit(){
		return this.extendunit;
	}
	public void setExtendUnit(UUID ExtendUnit){
		this.extendunit=ExtendUnit;
	}
	public BigDecimal getExtendMin(){
		return this.extendmin;
	}
	public void setExtendMin(BigDecimal ExtendMin){
		this.extendmin=ExtendMin;
	}
	public BigDecimal getExtendMax(){
		return this.extendmax;
	}
	public void setExtendMax(BigDecimal ExtendMax){
		this.extendmax=ExtendMax;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}
	public UUID getTypeCode(){
		return this.typecode;
	}
	public void setTypeCode(UUID TypeCode){
		this.typecode=TypeCode;
	}

}