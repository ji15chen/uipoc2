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
import com.example.dbman.db.genupdate.daoimpl.TmpHmdDataDaoImpl;
@DatabaseTable(tableName = "TmpHmdData", daoClass =TmpHmdDataDaoImpl.class)
	public class TmpHmdData{
@DatabaseField( columnName ="HytherID",id=true ,canBeNull = false )
	private UUID hytherid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="RoomID")
	private UUID roomid;
@DatabaseField( columnName ="RecTime")
	private Date rectime;
@DatabaseField( columnName ="Temperature")
	private BigDecimal temperature;
@DatabaseField( columnName ="Humidity")
	private BigDecimal humidity;
@DatabaseField( columnName ="Remark")
	private String remark;

	public UUID getHytherID(){
		return this.hytherid;
	}
	public void setHytherID(UUID HytherID){
		this.hytherid=HytherID;
	}
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
	public Date getRecTime(){
		return this.rectime;
	}
	public void setRecTime(Date RecTime){
		this.rectime=RecTime;
	}
	public BigDecimal getTemperature(){
		return this.temperature;
	}
	public void setTemperature(BigDecimal Temperature){
		this.temperature=Temperature;
	}
	public BigDecimal getHumidity(){
		return this.humidity;
	}
	public void setHumidity(BigDecimal Humidity){
		this.humidity=Humidity;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String Remark){
		this.remark=Remark;
	}

}