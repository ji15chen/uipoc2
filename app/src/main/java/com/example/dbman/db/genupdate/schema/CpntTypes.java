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
import com.example.dbman.db.genupdate.daoimpl.CpntTypesDaoImpl;
@DatabaseTable(tableName = "CpntTypes", daoClass =CpntTypesDaoImpl.class)
	public class CpntTypes implements Serializable{
@DatabaseField( columnName ="CpntID",id=true ,canBeNull = false )
	private UUID cpntid;
@DatabaseField( columnName ="CpntName")
	private String cpntname;
@DatabaseField( columnName ="CpntType")
	private String cpnttype;
@DatabaseField( columnName ="CpntUnit")
	private String cpntunit;
@DatabaseField( columnName ="CpntYear")
	private int cpntyear;
@DatabaseField( columnName ="CpntFunc")
	private String cpntfunc;
@DatabaseField( columnName ="IsPublic")
	private boolean ispublic;
@DatabaseField( columnName ="CpntDesc")
	private String cpntdesc;

	public UUID getCpntID(){
		return this.cpntid;
	}
	public void setCpntID(UUID CpntID){
		this.cpntid=CpntID;
	}
	public String getCpntName(){
		return this.cpntname;
	}
	public void setCpntName(String CpntName){
		this.cpntname=CpntName;
	}
	public String getCpntType(){
		return this.cpnttype;
	}
	public void setCpntType(String CpntType){
		this.cpnttype=CpntType;
	}
	public String getCpntUnit(){
		return this.cpntunit;
	}
	public void setCpntUnit(String CpntUnit){
		this.cpntunit=CpntUnit;
	}

	public int getCpntyear() {
		return cpntyear;
	}

	public void setCpntyear(int cpntyear) {
		this.cpntyear = cpntyear;
	}

	public String getCpntFunc(){
		return this.cpntfunc;
	}
	public void setCpntFunc(String CpntFunc){
		this.cpntfunc=CpntFunc;
	}
	public boolean IsPublic(){
		return this.ispublic;
	}
	public void IsPublic(boolean IsPublic){
		this.ispublic=IsPublic;
	}
	public String getCpntDesc(){
		return this.cpntdesc;
	}
	public void setCpntDesc(String CpntDesc){
		this.cpntdesc=CpntDesc;
	}

}