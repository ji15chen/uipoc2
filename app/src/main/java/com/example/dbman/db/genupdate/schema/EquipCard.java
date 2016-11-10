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
import com.example.dbman.db.genupdate.daoimpl.EquipCardDaoImpl;
@DatabaseTable(tableName = "EquipCard", daoClass =EquipCardDaoImpl.class)
	public class EquipCard{
@DatabaseField( columnName ="CardID",id=true ,canBeNull = false )
	private UUID cardid;
@DatabaseField( columnName ="CardPID")
	private UUID cardpid;
@DatabaseField( columnName ="StoreID")
	private UUID storeid;
@DatabaseField( columnName ="RFID")
	private String rfid;

	public UUID getCardid() {
		return cardid;
	}

	public void setCardid(UUID cardid) {
		this.cardid = cardid;
	}

	public UUID getCardpid() {
		return cardpid;
	}

	public void setCardpid(UUID cardpid) {
		this.cardpid = cardpid;
	}

	public UUID getStoreid() {
		return storeid;
	}

	public void setStoreid(UUID storeid) {
		this.storeid = storeid;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
}