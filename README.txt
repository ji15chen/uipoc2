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


查询实力明细主查询
SELECT
Department.DeptName, EquipType.Alias,EquipType.TypeName,
StoreDetail.StoreCode,StoreDetail.Total,paramUnit.ParaName,StoreDetail.Price,
paramStatus.ParaName, paramQuality.ParaName,
StoreDetail.ProduceDate,StoreDetail.PurchaseDate,StoreDetail.UseDate,
paramAddType.ParaName,StoreDetail.OtherDate,PersonInfo.PersonName,
DepotInfo.RoomName,FactoryInfo.FactoryName
FROM StoreDetail
LEFT JOIN Department ON Department.DeptID=StoreDetail.DeptID
LEFT JOIN EquipType  ON EquipType.PkTypeID=StoreDetail.PkTypeID
LEFT JOIN FactoryInfo ON  FactoryInfo.FactoryID=StoreDetail.FactoryID
LEFT JOIN DepotInfo  ON  DepotInfo.RoomID=StoreDetail.InDepot
LEFT JOIN PersonInfo  ON  PersonInfo.PersonID=StoreDetail.PersonLiableID
LEFT JOIN SysParameter paramUnit ON paramUnit.ParaID=EquipType.PkTypeID
LEFT JOIN SysParameter paramStatus ON paramStatus.ParaID=StoreDetail.UseState
LEFT JOIN SysParameter paramQuality ON paramQuality.ParaID=StoreDetail.QuKeyID
LEFT JOIN SysParameter paramAddType ON paramAddType.ParaID=StoreDetail.AddType

列2,列3,装备,和型号的区别,分别来自哪个字段?


扫描盘库中.
进行装备动态的更新是否仅针对  装备出入库表进行记录的插入操作?能否详细阐述操作流程?
