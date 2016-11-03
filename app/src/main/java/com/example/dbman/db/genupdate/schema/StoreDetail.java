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
import com.example.dbman.db.genupdate.daoimpl.StoreDetailDaoImpl;
@DatabaseTable(tableName = "StoreDetail", daoClass =StoreDetailDaoImpl.class)
	public class StoreDetail{
@DatabaseField( columnName ="StoreID",id=true ,canBeNull = false )
	private UUID storeid;
@DatabaseField( columnName ="StorePID")
	private UUID storepid;
@DatabaseField( columnName ="StoreCode")
	private String storecode;
@DatabaseField( columnName ="DeptID",canBeNull = false )
	private UUID deptid;
@DatabaseField( columnName ="RFID")
	private String rfid;
@DatabaseField( columnName ="RoomID")
	private UUID roomid;
@DatabaseField( columnName ="FactoryID",canBeNull = false )
	private UUID factoryid;
@DatabaseField( columnName ="PkTypeID",canBeNull = false )
	private UUID pktypeid;
@DatabaseField( columnName ="QuKeyID",canBeNull = false )
	private UUID qukeyid;
@DatabaseField( columnName ="Total")
	private BigDecimal total;
@DatabaseField( columnName ="Price")
	private BigDecimal price;
@DatabaseField( columnName ="UseState")
	private UUID usestate;
@DatabaseField( columnName ="PersonLiableID")
	private UUID personliableid;
@DatabaseField( columnName ="InDepot")
	private UUID indepot;
@DatabaseField( columnName ="ProduceDate",canBeNull = false )
	private Date producedate;
@DatabaseField( columnName ="PurchaseDate")
	private Date purchasedate;
@DatabaseField( columnName ="UseDate")
	private Date usedate;
@DatabaseField( columnName ="AddType")
	private String addtype;
@DatabaseField( columnName ="OtherDate")
	private Date otherdate;

	public UUID getStoreID(){
		return this.storeid;
	}
	public void setStoreID(UUID StoreID){
		this.storeid=StoreID;
	}
	public UUID getStorePID(){
		return this.storepid;
	}
	public void setStorePID(UUID StorePID){
		this.storepid=StorePID;
	}
	public String getStoreCode(){
		return this.storecode;
	}
	public void setStoreCode(String StoreCode){
		this.storecode=StoreCode;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public String getRFID(){
		return this.rfid;
	}
	public void setRFID(String RFID){
		this.rfid=RFID;
	}
	public UUID getRoomID(){
		return this.roomid;
	}
	public void setRoomID(UUID RoomID){
		this.roomid=RoomID;
	}
	public UUID getFactoryID(){
		return this.factoryid;
	}
	public void setFactoryID(UUID FactoryID){
		this.factoryid=FactoryID;
	}
	public UUID getPkTypeID(){
		return this.pktypeid;
	}
	public void setPkTypeID(UUID PkTypeID){
		this.pktypeid=PkTypeID;
	}
	public UUID getQuKeyID(){
		return this.qukeyid;
	}
	public void setQuKeyID(UUID QuKeyID){
		this.qukeyid=QuKeyID;
	}
	public BigDecimal getTotal(){
		return this.total;
	}
	public void setTotal(BigDecimal Total){
		this.total=Total;
	}
	public BigDecimal getPrice(){
		return this.price;
	}
	public void setPrice(BigDecimal Price){
		this.price=Price;
	}
	public UUID getUseState(){
		return this.usestate;
	}
	public void setUseState(UUID UseState){
		this.usestate=UseState;
	}
	public UUID getPersonLiableID(){
		return this.personliableid;
	}
	public void setPersonLiableID(UUID PersonLiableID){
		this.personliableid=PersonLiableID;
	}
	public UUID getInDepot(){
		return this.indepot;
	}
	public void setInDepot(UUID InDepot){
		this.indepot=InDepot;
	}
	public Date getProduceDate(){
		return this.producedate;
	}
	public void setProduceDate(Date ProduceDate){
		this.producedate=ProduceDate;
	}
	public Date getPurchaseDate(){
		return this.purchasedate;
	}
	public void setPurchaseDate(Date PurchaseDate){
		this.purchasedate=PurchaseDate;
	}
	public Date getUseDate(){
		return this.usedate;
	}
	public void setUseDate(Date UseDate){
		this.usedate=UseDate;
	}
	public String getAddType(){
		return this.addtype;
	}
	public void setAddType(String AddType){
		this.addtype=AddType;
	}
	public Date getOtherDate(){
		return this.otherdate;
	}
	public void setOtherDate(Date OtherDate){
		this.otherdate=OtherDate;
	}

}