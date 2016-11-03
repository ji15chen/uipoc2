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
import com.example.dbman.db.genupdate.daoimpl.CheckMntcDetailDaoImpl;
@DatabaseTable(tableName = "CheckMntcDetail", daoClass =CheckMntcDetailDaoImpl.class)
	public class CheckMntcDetail{

@DatabaseField( columnName ="MtID",id=false ,canBeNull = false )
	private UUID mtid;
@DatabaseField( columnName ="StoreID",id=false ,canBeNull = false )
	private UUID storeid;
@DatabaseField( columnName ="MtLong")
	private BigDecimal mtlong;
@DatabaseField( columnName ="MtResult")
	private String mtresult;
@DatabaseField( columnName ="CheckResult")
	private String checkresult;

	@DatabaseField(id=true, useGetSet=true)
	private UUID id;

	public UUID getId() {
		return new UUID(mtid.getMostSignificantBits()+storeid.getMostSignificantBits(), mtid.getLeastSignificantBits()+storeid.getLeastSignificantBits());
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getMtid() {
		return mtid;
	}
	public void setMtID(UUID MtID){
		this.mtid=MtID;
	}
	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public BigDecimal getMtLong(){
		return this.mtlong;
	}
	public void setMtLong(BigDecimal MtLong){
		this.mtlong=MtLong;
	}
	public String getMtResult(){
		return this.mtresult;
	}
	public void setMtResult(String MtResult){
		this.mtresult=MtResult;
	}
	public String getCheckResult(){
		return this.checkresult;
	}
	public void setCheckResult(String CheckResult){
		this.checkresult=CheckResult;
	}

}