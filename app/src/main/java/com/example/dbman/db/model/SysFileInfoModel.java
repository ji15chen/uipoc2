package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;

/**
 * Created by Administrator on 2016/11/13 0013.
 */

public class SysFileInfoModel {
    SysFileInfoDao sysFileInfoDao = (SysFileInfoDao)BaseDatabase.getInstance().getDaoImpl("SysFileInfo");

}
