package com.example.dbman.db.genupdate.schema;

	/**
	*	*@author jichen
	*/

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Date;
import com.example.dbman.db.genupdate.daoimpl.PersonInfoDaoImpl;
@DatabaseTable(tableName = "PersonInfo", daoClass =PersonInfoDaoImpl.class)
	public class PersonInfo{
@DatabaseField( columnName ="PersonID",id=true ,canBeNull = false )
	private UUID personid;
@DatabaseField( columnName ="PersonCode")
	private String personcode;
@DatabaseField( columnName ="PersonName")
	private String personname;
@DatabaseField( columnName ="Sex")
	private UUID sex;
@DatabaseField( columnName ="PersonType")
	private UUID persontype;
@DatabaseField( columnName ="JopID")
	private UUID jopid;
@DatabaseField( columnName ="Status")
	private UUID status;
@DatabaseField( columnName ="MobilPhone")
	private String mobilphone;
@DatabaseField( columnName ="Phone")
	private String phone;
@DatabaseField( columnName ="EMail")
	private String email;
@DatabaseField( columnName ="QQ")
	private String qq;
@DatabaseField( columnName ="Birthday")
	private Date birthday;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="IsDelete")
	private boolean isdelete;
@DatabaseField( columnName ="EnlistTime")
	private Date enlisttime;
@DatabaseField( columnName ="Fax")
	private String fax;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="Work")
	private String work;
@DatabaseField( columnName ="HeadImage", dataType = DataType.SERIALIZABLE)
	private Serializable headimage;

	public UUID getPersonID(){
		return this.personid;
	}
	public void setPersonID(UUID PersonID){
		this.personid=PersonID;
	}
	public String getPersonCode(){
		return this.personcode;
	}
	public void setPersonCode(String PersonCode){
		this.personcode=PersonCode;
	}
	public String getPersonName(){
		return this.personname;
	}
	public void setPersonName(String PersonName){
		this.personname=PersonName;
	}
	public UUID getSex(){
		return this.sex;
	}
	public void setSex(UUID Sex){
		this.sex=Sex;
	}
	public UUID getPersonType(){
		return this.persontype;
	}
	public void setPersonType(UUID PersonType){
		this.persontype=PersonType;
	}
	public UUID getJopID(){
		return this.jopid;
	}
	public void setJopID(UUID JopID){
		this.jopid=JopID;
	}
	public UUID getStatus(){
		return this.status;
	}
	public void setStatus(UUID Status){
		this.status=Status;
	}
	public String getMobilPhone(){
		return this.mobilphone;
	}
	public void setMobilPhone(String MobilPhone){
		this.mobilphone=MobilPhone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPhone(String Phone){
		this.phone=Phone;
	}
	public String getEMail(){
		return this.email;
	}
	public void setEMail(String EMail){
		this.email=EMail;
	}
	public String getQQ(){
		return this.qq;
	}
	public void setQQ(String QQ){
		this.qq=QQ;
	}
	public Date getBirthday(){
		return this.birthday;
	}
	public void setBirthday(Date Birthday){
		this.birthday=Birthday;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}
	public boolean IsDelete(){
		return this.isdelete;
	}
	public void IsDelete(boolean IsDelete){
		this.isdelete=IsDelete;
	}
	public Date getEnlistTime(){
		return this.enlisttime;
	}
	public void setEnlistTime(Date EnlistTime){
		this.enlisttime=EnlistTime;
	}
	public String getFax(){
		return this.fax;
	}
	public void setFax(String Fax){
		this.fax=Fax;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public String getWork(){
		return this.work;
	}
	public void setWork(String Work){
		this.work=Work;
	}
	public Serializable getHeadImage(){
		return this.headimage;
	}
	public void setHeadImage(Serializable HeadImage){
		this.headimage=HeadImage;
	}

}