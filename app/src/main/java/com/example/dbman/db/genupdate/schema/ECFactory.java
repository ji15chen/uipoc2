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
import com.example.dbman.db.genupdate.daoimpl.ECFactoryDaoImpl;
@DatabaseTable(tableName = "ECFactory", daoClass =ECFactoryDaoImpl.class)
	public class ECFactory{
@DatabaseField( columnName ="RecID",id=false ,canBeNull = false )
	private UUID recid;
@DatabaseField( columnName ="FactoryID",id=false ,canBeNull = false )
	private UUID factoryid;
@DatabaseField( columnName ="PkTypeID")
	private UUID pktypeid;
@DatabaseField( columnName ="Sorting")
	private int sorting;
@DatabaseField( columnName ="Describe")
	private String describe;

	@DatabaseField(id=true, useGetSet=true)
	private UUID id;

	public UUID getId() {
		return new UUID(recid.getMostSignificantBits()+factoryid.getMostSignificantBits(), recid.getLeastSignificantBits()+factoryid.getLeastSignificantBits());
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRecID(){
		return this.recid;
	}
	public void setRecID(UUID RecID){
		this.recid=RecID;
	}
	public UUID getFactoryID(){
		return this.factoryid;
	}
	public void setFactoryID(UUID FactoryID){
		this.factoryid=FactoryID;
	}
	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}
	public int getSorting(){
		return this.sorting;
	}
	public void setSorting(int Sorting){
		this.sorting=Sorting;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}

}