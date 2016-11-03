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
import com.example.dbman.db.genupdate.daoimpl.WorkersDaoImpl;
@DatabaseTable(tableName = "Workers", daoClass =WorkersDaoImpl.class)
	public class Workers{
	@DatabaseField(generatedId=true ,canBeNull = false )
	private UUID id;
@DatabaseField( columnName ="PIOID",id=false ,canBeNull = false )
	private UUID pioid;
@DatabaseField( columnName ="PersonID",id=false ,canBeNull = false )
	private UUID personid;

	public UUID getPIOID(){
		return this.pioid;
	}
	public void setPIOID(UUID PIOID){
		this.pioid=PIOID;
	}
	public UUID getPersonID(){
		return this.personid;
	}
	public void setPersonID(UUID PersonID){
		this.personid=PersonID;
	}

}