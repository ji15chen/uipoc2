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
import db.genupdate.daoimpl.M_PointDaoImpl;
@DatabaseTable(tableName = "M_Point", daoClass =M_PointDaoImpl.class)
	public class M_Point{
@DatabaseField( columnName ="PointID",id=true ,canBeNull = false )
	private UUID pointid;
@DatabaseField( columnName ="PointName")
	private String pointname;
@DatabaseField( columnName ="PointType")
	private int pointtype;
@DatabaseField( columnName ="PointImage")
	private Serializable pointimage;
@DatabaseField( columnName ="Longitude")
	private BigDecimal longitude;
@DatabaseField( columnName ="Latitude")
	private BigDecimal latitude;
@DatabaseField( columnName ="PointZoom")
	private int pointzoom;
@DatabaseField( columnName ="Descn")
	private String descn;


}