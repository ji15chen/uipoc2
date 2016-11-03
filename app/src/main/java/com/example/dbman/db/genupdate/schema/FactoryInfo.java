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
import com.example.dbman.db.genupdate.daoimpl.FactoryInfoDaoImpl;
@DatabaseTable(tableName = "FactoryInfo", daoClass =FactoryInfoDaoImpl.class)
	public class FactoryInfo{
@DatabaseField( columnName ="FactoryID",id=true ,canBeNull = false )
	private UUID factoryid;
@DatabaseField( columnName ="FactoryName")
	private String factoryname;
@DatabaseField( columnName ="FactoryType")
	private UUID factorytype;
@DatabaseField( columnName ="Province")
	private UUID province;
@DatabaseField( columnName ="City")
	private UUID city;
@DatabaseField( columnName ="District")
	private UUID district;
@DatabaseField( columnName ="Address")
	private String address;
@DatabaseField( columnName ="PostCode")
	private String postcode;
@DatabaseField( columnName ="FirstMan")
	private String firstman;
@DatabaseField( columnName ="Remark")
	private String remark;
@DatabaseField( columnName ="Descn")
	private String descn;
@DatabaseField( columnName ="Telephone")
	private String telephone;

	public UUID getFactoryID(){
		return this.factoryid;
	}
	public void setFactoryID(UUID FactoryID){
		this.factoryid=FactoryID;
	}
	public String getFactoryName(){
		return this.factoryname;
	}
	public void setFactoryName(String FactoryName){
		this.factoryname=FactoryName;
	}
	public UUID getFactoryType(){
		return this.factorytype;
	}
	public void setFactoryType(UUID FactoryType){
		this.factorytype=FactoryType;
	}
	public UUID getProvince(){
		return this.province;
	}
	public void setProvince(UUID Province){
		this.province=Province;
	}
	public UUID getCity(){
		return this.city;
	}
	public void setCity(UUID City){
		this.city=City;
	}
	public UUID getDistrict(){
		return this.district;
	}
	public void setDistrict(UUID District){
		this.district=District;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String Address){
		this.address=Address;
	}
	public String getPostCode(){
		return this.postcode;
	}
	public void setPostCode(String PostCode){
		this.postcode=PostCode;
	}
	public String getFirstMan(){
		return this.firstman;
	}
	public void setFirstMan(String FirstMan){
		this.firstman=FirstMan;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String Remark){
		this.remark=Remark;
	}
	public String getDescn(){
		return this.descn;
	}
	public void setDescn(String Descn){
		this.descn=Descn;
	}
	public String getTelephone(){
		return this.telephone;
	}
	public void setTelephone(String Telephone){
		this.telephone=Telephone;
	}

}