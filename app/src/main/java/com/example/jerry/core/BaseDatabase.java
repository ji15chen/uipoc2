package com.example.jerry.core;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.apkfuns.logutils.LogUtils;
import com.example.jerry.db.schema.DaoMaster;
import com.example.jerry.db.schema.DaoSession;

/**
 * Created by ChenJi on 2016/10/26.
 */

public class BaseDatabase {
    private static final String DB_NAME="db";
    private static BaseDatabase baseDatabase = new BaseDatabase();

    public static BaseDatabase getInstance() {
        return baseDatabase;
    }



    private SQLiteDatabase db;
    private DaoMaster daoMaster = new DaoMaster(db);
    private DaoSession daoSession = daoMaster.newSession();

    public boolean open(Application app){
        try {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, DB_NAME, null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            return true;
        }catch (Exception e){
            LogUtils.e("ui database error",e);
            return false;
        }
    }

    public DaoSession getSession(){
        return daoSession;
    }

}
