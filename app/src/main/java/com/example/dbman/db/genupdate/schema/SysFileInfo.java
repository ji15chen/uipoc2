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
import com.example.dbman.db.genupdate.daoimpl.SysFileInfoDaoImpl;
@DatabaseTable(tableName = "SysFileInfo", daoClass =SysFileInfoDaoImpl.class)
	public class SysFileInfo{
@DatabaseField( columnName ="FileID",id=true ,canBeNull = false )
	private UUID fileid;
@DatabaseField( columnName ="ObjectID")
	private UUID objectid;
@DatabaseField( columnName ="DeptID")
	private UUID deptid;
@DatabaseField( columnName ="FileName")
	private String filename;
@DatabaseField( columnName ="Title")
	private String title;
@DatabaseField( columnName ="Extension")
	private String extension;
@DatabaseField( columnName ="FileSize")
	private String filesize;
@DatabaseField( columnName ="FileData", dataType = DataType.SERIALIZABLE)
	private Serializable filedata;
@DatabaseField( columnName ="CategoryID")
	private int categoryid;
@DatabaseField( columnName ="Descn")
	private String descn;
@DatabaseField( columnName ="CreateDate")
	private Date createdate;
@DatabaseField( columnName ="UserID")
	private UUID userid;


	public UUID getFileID(){
		return this.fileid;
	}
	public void setFileID(UUID FileID){
		this.fileid=FileID;
	}
	public UUID getObjectID(){
		return this.objectid;
	}
	public void setObjectID(UUID ObjectID){
		this.objectid=ObjectID;
	}
	public UUID getDeptID(){
		return this.deptid;
	}
	public void setDeptID(UUID DeptID){
		this.deptid=DeptID;
	}
	public String getFileName(){
		return this.filename;
	}
	public void setFileName(String FileName){
		this.filename=FileName;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String Title){
		this.title=Title;
	}
	public String getExtension(){
		return this.extension;
	}
	public void setExtension(String Extension){
		this.extension=Extension;
	}
	public String getFileSize(){
		return this.filesize;
	}
	public void setFileSize(String FileSize){
		this.filesize=FileSize;
	}
	public Serializable getFileData(){
		return this.filedata;
	}
	public void setFileData(Serializable FileData){
		this.filedata=FileData;
	}
	public int getCategoryID(){
		return this.categoryid;
	}
	public void setCategoryID(int CategoryID){
		this.categoryid=CategoryID;
	}
	public String getDescn(){
		return this.descn;
	}
	public void setDescn(String Descn){
		this.descn=Descn;
	}
	public Date getCreateDate(){
		return this.createdate;
	}
	public void setCreateDate(Date CreateDate){
		this.createdate=CreateDate;
	}
	public UUID getUserID(){
		return this.userid;
	}
	public void setUserID(UUID UserID){
		this.userid=UserID;
	}

}