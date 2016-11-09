查询某个设备类型的所有工厂信息

SELECT fi.FactoryID,fi.FactoryName,
			paraFactoryType.ParaName,
			paraProvince.ParaName,
			paraCity.ParaName,
			paraDistrict.ParaName,
			fi.Address,
			fi.PostCode,
			fi.FirstMan,
			fi.Remark,
			fi.Descn,
			fi.Telephone
FROM FactoryInfo fi
INNER JOIN SysParameter paraFactoryType ON  paraFactoryType.ParaID=fi.FactoryType
INNER JOIN SysParameter paraProvince ON  		paraProvince.ParaID=fi.Province
INNER JOIN SysParameter paraCity ON  				paraCity.ParaID=fi.City
INNER JOIN SysParameter paraDistrict ON  				paraDistrict.ParaID=fi.District
WHERE fi.FactoryID IN (SELECT FactoryID FROM ECFactory WHERE PkTypeID = '5809D095-7F4D-4FAD-B3B9-762236EAC2EA')


查询某设备类型的组件信息
        SELECT CpntTypes.*,EqmtCpntInfo.CpntCount FROM EqmtCpntInfo
        LEFT OUTER JOIN CpntTypes ON CpntTypes.CpntID=EqmtCpntInfo.CpntID
        WHERE EqmtCpntInfo.PkTypeID='5809D095-7F4D-4FAD-B3B9-762236EAC2EA'