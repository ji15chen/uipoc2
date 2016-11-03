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
import com.example.dbman.db.genupdate.daoimpl.ScanStoreDetailDaoImpl;
@DatabaseTable(tableName = "ScanStoreDetail", daoClass =ScanStoreDetailDaoImpl.class)
	public class ScanStoreDetail{
@DatabaseField( columnName ="DetailID",id=true ,canBeNull = false )
	private UUID detailid;
@DatabaseField( columnName ="ScanID",canBeNull = false )
	private UUID scanid;
@DatabaseField( columnName ="StoreID",canBeNull = false )
	private UUID storeid;
@DatabaseField( columnName ="ScanType")
	private UUID scantype;
@DatabaseField( columnName ="Descn")
	private String descn;

	public UUID getDetailID(){
		return this.detailid;
	}
	public void setDetailID(UUID DetailID){
		this.detailid=DetailID;
	}
	public UUID getScanID(){
		return this.scanid;
	}
	public void setScanID(UUID ScanID){
		this.scanid=ScanID;
	}
	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public UUID getScanType(){
		return this.scantype;
	}
	public void setScanType(UUID ScanType){
		this.scantype=ScanType;
	}
	public String getDescn(){
		return this.descn;
	}
	public void setDescn(String Descn){
		this.descn=Descn;
	}

}