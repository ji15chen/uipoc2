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
import com.example.dbman.db.genupdate.daoimpl.PersonInOutDaoImpl;
@DatabaseTable(tableName = "PersonInOut", daoClass =PersonInOutDaoImpl.class)
	public class PersonInOut{
@DatabaseField( columnName ="PIOID",id=true ,canBeNull = false )
	private UUID pioid;
@DatabaseField( columnName ="RoomID")
	private UUID roomid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="PersonID")
	private UUID personid;
@DatabaseField( columnName ="InTime")
	private Date intime;
@DatabaseField( columnName ="OutTime")
	private Date outtime;
@DatabaseField( columnName ="ReasonID")
	private UUID reasonid;
@DatabaseField( columnName ="Approver")
	private UUID approver;
@DatabaseField( columnName ="EnterType1")
	private String entertype1;
@DatabaseField( columnName ="EnterType2")
	private String entertype2;
@DatabaseField( columnName ="Remark")
	private String remark;

	public UUID getPIOID(){
		return this.pioid;
	}
	public void setPIOID(UUID PIOID){
		this.pioid=PIOID;
	}
	public UUID getRoomID(){
		return this.roomid;
	}
	public void setRoomID(UUID RoomID){
		this.roomid=RoomID;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getPersonID(){
		return this.personid;
	}
	public void setPersonID(UUID PersonID){
		this.personid=PersonID;
	}
	public Date getInTime(){
		return this.intime;
	}
	public void setInTime(Date InTime){
		this.intime=InTime;
	}
	public Date getOutTime(){
		return this.outtime;
	}
	public void setOutTime(Date OutTime){
		this.outtime=OutTime;
	}
	public UUID getReasonID(){
		return this.reasonid;
	}
	public void setReasonID(UUID ReasonID){
		this.reasonid=ReasonID;
	}
	public UUID getApprover(){
		return this.approver;
	}
	public void setApprover(UUID Approver){
		this.approver=Approver;
	}
	public String getEnterType1(){
		return this.entertype1;
	}
	public void setEnterType1(String EnterType1){
		this.entertype1=EnterType1;
	}
	public String getEnterType2(){
		return this.entertype2;
	}
	public void setEnterType2(String EnterType2){
		this.entertype2=EnterType2;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String Remark){
		this.remark=Remark;
	}

}