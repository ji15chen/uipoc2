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
import com.example.dbman.db.genupdate.daoimpl.SysUserDaoImpl;
@DatabaseTable(tableName = "SysUser", daoClass =SysUserDaoImpl.class)
	public class SysUser{
@DatabaseField( columnName ="UserID",id=true ,canBeNull = false )
	private UUID userid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="RoleID")
	private UUID roleid;
@DatabaseField( columnName ="UserName")
	private String username;
@DatabaseField( columnName ="LoginID")
	private String loginid;
@DatabaseField( columnName ="Password")
	private String password;
@DatabaseField( columnName ="UseState")
	private boolean usestate;
@DatabaseField( columnName ="ChangeUserID")
	private UUID changeuserid;
@DatabaseField( columnName ="ChangeDate")
	private Date changedate;

	public UUID getUserID(){
		return this.userid;
	}
	public void setUserID(UUID UserID){
		this.userid=UserID;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getRoleID(){
		return this.roleid;
	}
	public void setRoleID(UUID RoleID){
		this.roleid=RoleID;
	}
	public String getUserName(){
		return this.username;
	}
	public void setUserName(String UserName){
		this.username=UserName;
	}
	public String getLoginID(){
		return this.loginid;
	}
	public void setLoginID(String LoginID){
		this.loginid=LoginID;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String Password){
		this.password=Password;
	}
	public boolean UseState(){
		return this.usestate;
	}
	public void UseState(boolean UseState){
		this.usestate=UseState;
	}
	public UUID getChangeUserID(){
		return this.changeuserid;
	}
	public void setChangeUserID(UUID ChangeUserID){
		this.changeuserid=ChangeUserID;
	}
	public Date getChangeDate(){
		return this.changedate;
	}
	public void setChangeDate(Date ChangeDate){
		this.changedate=ChangeDate;
	}

}