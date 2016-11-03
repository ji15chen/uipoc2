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
import com.example.dbman.db.genupdate.daoimpl.EqmtCpntInfoDaoImpl;
@DatabaseTable(tableName = "EqmtCpntInfo", daoClass =EqmtCpntInfoDaoImpl.class)
	public class EqmtCpntInfo{
@DatabaseField( columnName ="PkTypeID",id=false ,canBeNull = false )
	private UUID pktypeid;
@DatabaseField( columnName ="CpntID",id=false ,canBeNull = false )
	private UUID cpntid;
@DatabaseField( columnName ="CpntCount")
	private int cpntcount;
@DatabaseField( columnName ="IsMaster")
	private boolean ismaster;
@DatabaseField( columnName ="Sorting")
	private int sorting;


	@DatabaseField(id=true, useGetSet=true)
	private UUID id;

	public UUID getId() {
		return new UUID(pktypeid.getMostSignificantBits()+cpntid.getMostSignificantBits(), pktypeid.getLeastSignificantBits()+cpntid.getLeastSignificantBits());
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}
	public UUID getCpntID(){
		return this.cpntid;
	}
	public void setCpntID(UUID CpntID){
		this.cpntid=CpntID;
	}
	public int getCpntCount(){
		return this.cpntcount;
	}
	public void setCpntCount(int CpntCount){
		this.cpntcount=CpntCount;
	}
	public boolean IsMaster(){
		return this.ismaster;
	}
	public void IsMaster(boolean IsMaster){
		this.ismaster=IsMaster;
	}
	public int getSorting(){
		return this.sorting;
	}
	public void setSorting(int Sorting){
		this.sorting=Sorting;
	}

}