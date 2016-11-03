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
import com.example.dbman.db.genupdate.daoimpl.DepartmentDaoImpl;
@DatabaseTable(tableName = "Department", daoClass =DepartmentDaoImpl.class)
	public class Department{
@DatabaseField( columnName ="DeptID",id=true ,canBeNull = false )
	private UUID deptid;
@DatabaseField( columnName ="SupDeptID")
	private UUID supdeptid;
@DatabaseField( columnName ="DeptCode")
	private String deptcode;
@DatabaseField( columnName ="DeptName")
	private String deptname;
@DatabaseField( columnName ="ShortName")
	private String shortname;
@DatabaseField( columnName ="Designation")
	private String designation;
@DatabaseField( columnName ="Address")
	private String address;
@DatabaseField( columnName ="TypeCode")
	private UUID typecode;
@DatabaseField( columnName ="Dwye")
	private int dwye;
@DatabaseField( columnName ="DeptLevel")
	private UUID deptlevel;
@DatabaseField( columnName ="Dwzl")
	private UUID dwzl;
@DatabaseField( columnName ="FirstMgr")
	private UUID firstmgr;
@DatabaseField( columnName ="Descirbe")
	private String descirbe;
@DatabaseField( columnName ="IsEnable")
	private boolean isenable;
@DatabaseField( columnName ="CreateUserID")
	private UUID createuserid;
@DatabaseField( columnName ="ChangeDate")
	private Date changedate;

	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getSupDeptID(){
		return this.supdeptid;
	}
	public void setSupDeptID(UUID SupDeptID){
		this.supdeptid=SupDeptID;
	}
	public String getDeptCode(){
		return this.deptcode;
	}
	public void setDeptCode(String DeptCode){
		this.deptcode=DeptCode;
	}
	public String getDeptName(){
		return this.deptname;
	}
	public void setDeptName(String DeptName){
		this.deptname=DeptName;
	}
	public String getShortName(){
		return this.shortname;
	}
	public void setShortName(String ShortName){
		this.shortname=ShortName;
	}
	public String getDesignation(){
		return this.designation;
	}
	public void setDesignation(String Designation){
		this.designation=Designation;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String Address){
		this.address=Address;
	}
	public UUID getTypeCode(){
		return this.typecode;
	}
	public void setTypeCode(UUID TypeCode){
		this.typecode=TypeCode;
	}
	public int getDwye(){
		return this.dwye;
	}
	public void setDwye(int Dwye){
		this.dwye=Dwye;
	}
	public UUID getDeptLevel(){
		return this.deptlevel;
	}
	public void setDeptLevel(UUID DeptLevel){
		this.deptlevel=DeptLevel;
	}
	public UUID getDwzl(){
		return this.dwzl;
	}
	public void setDwzl(UUID Dwzl){
		this.dwzl=Dwzl;
	}
	public UUID getFirstMgr(){
		return this.firstmgr;
	}
	public void setFirstMgr(UUID FirstMgr){
		this.firstmgr=FirstMgr;
	}
	public String getDescirbe(){
		return this.descirbe;
	}
	public void setDescirbe(String Descirbe){
		this.descirbe=Descirbe;
	}
	public boolean IsEnable(){
		return this.isenable;
	}
	public void IsEnable(boolean IsEnable){
		this.isenable=IsEnable;
	}
	public UUID getCreateUserID(){
		return this.createuserid;
	}
	public void setCreateUserID(UUID CreateUserID){
		this.createuserid=CreateUserID;
	}
	public Date getChangeDate(){
		return this.changedate;
	}
	public void setChangeDate(Date ChangeDate){
		this.changedate=ChangeDate;
	}

}