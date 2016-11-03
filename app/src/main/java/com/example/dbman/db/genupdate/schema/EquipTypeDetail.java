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
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDetailDaoImpl;
@DatabaseTable(tableName = "EquipTypeDetail", daoClass =EquipTypeDetailDaoImpl.class)
	public class EquipTypeDetail{
@DatabaseField( columnName ="ItemID",id=true ,canBeNull = false )
	private UUID itemid;
@DatabaseField( columnName ="PkTypeID")
	private UUID pktypeid;
@DatabaseField( columnName ="ItemName")
	private String itemname;
@DatabaseField( columnName ="ItemBody")
	private String itembody;
@DatabaseField( columnName ="ItemSort")
	private int itemsort;

	public UUID getItemID(){
		return this.itemid;
	}
	public void setItemID(UUID ItemID){
		this.itemid=ItemID;
	}
	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}
	public String getItemName(){
		return this.itemname;
	}
	public void setItemName(String ItemName){
		this.itemname=ItemName;
	}
	public String getItemBody(){
		return this.itembody;
	}
	public void setItemBody(String ItemBody){
		this.itembody=ItemBody;
	}
	public int getItemSort(){
		return this.itemsort;
	}
	public void setItemSort(int ItemSort){
		this.itemsort=ItemSort;
	}

}