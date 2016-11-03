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
import com.example.dbman.db.genupdate.daoimpl.SysdiagramsDaoImpl;
@DatabaseTable(tableName = "sysdiagrams", daoClass =SysdiagramsDaoImpl.class)
	public class Sysdiagrams{
@DatabaseField( columnName ="name",canBeNull = false )
	private String name;
@DatabaseField( columnName ="principal_id",canBeNull = false )
	private int principal_id;
@DatabaseField( columnName ="diagram_id",id = true ,canBeNull = false )
	private int diagram_id;
@DatabaseField( columnName ="version")
	private int version;
@DatabaseField( columnName ="definition", dataType = DataType.BYTE_ARRAY)
	private byte[] definition;

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getPrincipal_id(){
		return this.principal_id;
	}
	public void setPrincipal_id(int principal_id){
		this.principal_id=principal_id;
	}
	public int getDiagram_id(){
		return this.diagram_id;
	}
	public void setDiagram_id(int diagram_id){
		this.diagram_id=diagram_id;
	}
	public int getVersion(){
		return this.version;
	}
	public void setVersion(int version){
		this.version=version;
	}
	public byte[] getDefinition(){
		return this.definition;
	}
	public void setDefinition(byte[] definition){
		this.definition=definition;
	}

}