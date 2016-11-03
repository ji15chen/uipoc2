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
import com.example.dbman.db.genupdate.daoimpl.CheckMntcInfoDaoImpl;
@DatabaseTable(tableName = "CheckMntcInfo", daoClass =CheckMntcInfoDaoImpl.class)
	public class CheckMntcInfo{
@DatabaseField( columnName ="MtID",id=true ,canBeNull = false )
	private UUID mtid;
@DatabaseField( columnName ="DetpID")
	private UUID detpid;
@DatabaseField( columnName ="RoomID")
	private UUID roomid;
@DatabaseField( columnName ="Topic")
	private String topic;
@DatabaseField( columnName ="MtTime")
	private Date mttime;
@DatabaseField( columnName ="MtLong")
	private BigDecimal mtlong;
@DatabaseField( columnName ="MtResult")
	private String mtresult;
@DatabaseField( columnName ="CheckType")
	private UUID checktype;
@DatabaseField( columnName ="CheckResult")
	private String checkresult;
@DatabaseField( columnName ="WeatherID")
	private UUID weatherid;
@DatabaseField( columnName ="WokCount")
	private int wokcount;

	public UUID getMtID(){
		return this.mtid;
	}
	public void setMtID(UUID MtID){
		this.mtid=MtID;
	}
	public UUID getDetpID(){
		return this.detpid;
	}
	public void setDetpID(UUID DetpID){
		this.detpid=DetpID;
	}
	public UUID getRoomID(){
		return this.roomid;
	}
	public void setRoomID(UUID RoomID){
		this.roomid=RoomID;
	}
	public String getTopic(){
		return this.topic;
	}
	public void setTopic(String Topic){
		this.topic=Topic;
	}
	public Date getMtTime(){
		return this.mttime;
	}
	public void setMtTime(Date MtTime){
		this.mttime=MtTime;
	}
	public BigDecimal getMtLong(){
		return this.mtlong;
	}
	public void setMtLong(BigDecimal MtLong){
		this.mtlong=MtLong;
	}
	public String getMtResult(){
		return this.mtresult;
	}
	public void setMtResult(String MtResult){
		this.mtresult=MtResult;
	}
	public UUID getCheckType(){
		return this.checktype;
	}
	public void setCheckType(UUID CheckType){
		this.checktype=CheckType;
	}
	public String getCheckResult(){
		return this.checkresult;
	}
	public void setCheckResult(String CheckResult){
		this.checkresult=CheckResult;
	}
	public UUID getWeatherID(){
		return this.weatherid;
	}
	public void setWeatherID(UUID WeatherID){
		this.weatherid=WeatherID;
	}
	public int getWokCount(){
		return this.wokcount;
	}
	public void setWokCount(int WokCount){
		this.wokcount=WokCount;
	}

}