package com.example.dbman.db.gen;

	/**
	*	*@author jichen
	*/

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

@DatabaseTable(tableName = "EquipType")
	public class EquipType {
@DatabaseField( columnName ="PkTypeID",id = true ,canBeNull = false )
	private String pktypeid;
@DatabaseField( columnName ="SupPkTypeID")
	private String suppktypeid;
//@DatabaseField( columnName ="DeptID"/*//TODO: properly handle foreign key*/,foreign = true ,columnName ="Department_DeptID")
//	private String deptid;
//@DatabaseField( columnName ="UserID"/*//TODO: properly handle foreign key*/,foreign = true ,columnName ="SysUser_UserID")
//	private String userid;
@DatabaseField( columnName ="AddDate")
	private Date adddate;
@DatabaseField( columnName ="TypeName")
	private String typename;
@DatabaseField( columnName ="TypeCode")
	private String typecode;
@DatabaseField( columnName ="Unit")
	private String unit;
@DatabaseField( columnName ="Scale")
	private BigDecimal scale;
@DatabaseField( columnName ="IsEnable")
	private int isenable;
@DatabaseField( columnName ="Describe")
	private String describe;
@DatabaseField( columnName ="CategoryID")
	private String categoryid;
@DatabaseField( columnName ="WarrantyPeriod")
	private int warrantyperiod;
@DatabaseField( columnName ="Life")
	private int life;
@DatabaseField( columnName ="Count")
	private BigDecimal count;

}