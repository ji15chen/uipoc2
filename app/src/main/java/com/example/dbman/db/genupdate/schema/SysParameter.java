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
import com.example.dbman.db.genupdate.daoimpl.SysParameterDaoImpl;
@DatabaseTable(tableName = "SysParameter", daoClass =SysParameterDaoImpl.class)
	public class SysParameter{
@DatabaseField( columnName ="ParaID",id=true ,canBeNull = false )
	private UUID paraid;
@DatabaseField( columnName ="ParentID")
	private UUID parentid;
@DatabaseField( columnName ="ParaName")
	private String paraname;
@DatabaseField( columnName ="ParaSort")
	private int parasort;

	public UUID getParaID(){
		return this.paraid;
	}
	public void setParaID(UUID ParaID){
		this.paraid=ParaID;
	}
	public UUID getParentID(){
		return this.parentid;
	}
	public void setParentID(UUID ParentID){
		this.parentid=ParentID;
	}
	public String getParaName(){
		return this.paraname;
	}
	public void setParaName(String ParaName){
		this.paraname=ParaName;
	}
	public int getParaSort(){
		return this.parasort;
	}
	public void setParaSort(int ParaSort){
		this.parasort=ParaSort;
	}

}