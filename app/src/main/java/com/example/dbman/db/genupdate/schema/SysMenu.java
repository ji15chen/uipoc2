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
import com.example.dbman.db.genupdate.daoimpl.SysMenuDaoImpl;
@DatabaseTable(tableName = "SysMenu", daoClass =SysMenuDaoImpl.class)
	public class SysMenu{
@DatabaseField( columnName ="MenuID",id=true ,canBeNull = false )
	private UUID menuid;
@DatabaseField( columnName ="MenuName")
	private String menuname;
@DatabaseField( columnName ="ParentID")
	private UUID parentid;
@DatabaseField( columnName ="OrderNum")
	private int ordernum;
@DatabaseField( columnName ="Url")
	private String url;
@DatabaseField( columnName ="Remark")
	private String remark;
@DatabaseField( columnName ="IconName")
	private String iconname;

	public UUID getMenuID(){
		return this.menuid;
	}
	public void setMenuID(UUID MenuID){
		this.menuid=MenuID;
	}
	public String getMenuName(){
		return this.menuname;
	}
	public void setMenuName(String MenuName){
		this.menuname=MenuName;
	}
	public UUID getParentID(){
		return this.parentid;
	}
	public void setParentID(UUID ParentID){
		this.parentid=ParentID;
	}
	public int getOrderNum(){
		return this.ordernum;
	}
	public void setOrderNum(int OrderNum){
		this.ordernum=OrderNum;
	}
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String Url){
		this.url=Url;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String Remark){
		this.remark=Remark;
	}
	public String getIconName(){
		return this.iconname;
	}
	public void setIconName(String IconName){
		this.iconname=IconName;
	}

}