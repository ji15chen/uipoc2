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
import com.example.dbman.db.genupdate.daoimpl.SysKeyDaoImpl;
@DatabaseTable(tableName = "SysKey", daoClass =SysKeyDaoImpl.class)
	public class SysKey{
@DatabaseField( columnName ="KeyID",id=true ,canBeNull = false )
	private UUID keyid;
@DatabaseField( columnName ="MenuID")
	private UUID menuid;
@DatabaseField( columnName ="KeyName")
	private String keyname;
@DatabaseField( columnName ="KeyCode")
	private String keycode;
@DatabaseField( columnName ="KeySort")
	private int keysort;
@DatabaseField( columnName ="KeyImageUrl")
	private String keyimageurl;

	public UUID getKeyID(){
		return this.keyid;
	}
	public void setKeyID(UUID KeyID){
		this.keyid=KeyID;
	}
	public UUID getMenuID(){
		return this.menuid;
	}
	public void setMenuID(UUID MenuID){
		this.menuid=MenuID;
	}
	public String getKeyName(){
		return this.keyname;
	}
	public void setKeyName(String KeyName){
		this.keyname=KeyName;
	}
	public String getKeyCode(){
		return this.keycode;
	}
	public void setKeyCode(String KeyCode){
		this.keycode=KeyCode;
	}
	public int getKeySort(){
		return this.keysort;
	}
	public void setKeySort(int KeySort){
		this.keysort=KeySort;
	}
	public String getKeyImageUrl(){
		return this.keyimageurl;
	}
	public void setKeyImageUrl(String KeyImageUrl){
		this.keyimageurl=KeyImageUrl;
	}

}