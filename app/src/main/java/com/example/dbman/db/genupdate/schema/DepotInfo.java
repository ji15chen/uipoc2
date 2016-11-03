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
import com.example.dbman.db.genupdate.daoimpl.DepotInfoDaoImpl;
@DatabaseTable(tableName = "DepotInfo", daoClass =DepotInfoDaoImpl.class)
	public class DepotInfo{
@DatabaseField( columnName ="DeptID",canBeNull = false )
	private UUID deptid;
@DatabaseField( columnName ="RoomID",id=true ,canBeNull = false )
	private UUID roomid;
@DatabaseField( columnName ="RoomName")
	private String roomname;
@DatabaseField( columnName ="RoomType")
	private UUID roomtype;
@DatabaseField( columnName ="DepotAdd")
	private String depotadd;
@DatabaseField( columnName ="DepotArear")
	private BigDecimal depotarear;
@DatabaseField( columnName ="Lever")
	private UUID lever;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="AirconCount")
	private int airconcount;
@DatabaseField( columnName ="DehCount")
	private int dehcount;

	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public UUID getRoomID(){
		return this.roomid;
	}
	public void setRoomID(UUID RoomID){
		this.roomid=RoomID;
	}
	public String getRoomName(){
		return this.roomname;
	}
	public void setRoomName(String RoomName){
		this.roomname=RoomName;
	}
	public UUID getRoomType(){
		return this.roomtype;
	}
	public void setRoomType(UUID RoomType){
		this.roomtype=RoomType;
	}
	public String getDepotAdd(){
		return this.depotadd;
	}
	public void setDepotAdd(String DepotAdd){
		this.depotadd=DepotAdd;
	}
	public BigDecimal getDepotArear(){
		return this.depotarear;
	}
	public void setDepotArear(BigDecimal DepotArear){
		this.depotarear=DepotArear;
	}
	public UUID getLever(){
		return this.lever;
	}
	public void setLever(UUID Lever){
		this.lever=Lever;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String Describe){
		this.describe=Describe;
	}
	public int getAirconCount(){
		return this.airconcount;
	}
	public void setAirconCount(int AirconCount){
		this.airconcount=AirconCount;
	}
	public int getDehCount(){
		return this.dehcount;
	}
	public void setDehCount(int DehCount){
		this.dehcount=DehCount;
	}

}