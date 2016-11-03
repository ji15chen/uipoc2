package db.genupdate.schema;

	/**
	*	*@author jichen
	*/

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Date;
import db.genupdate.daoimpl.EquipCardDaoImpl;
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


}