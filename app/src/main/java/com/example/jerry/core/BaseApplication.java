package com.example.jerry.core;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.apkfuns.logutils.LogUtils;
import com.example.jerry.db.schema.DaoMaster;
import com.example.jerry.db.schema.DaoSession;
import com.snappydb.DB;
import com.snappydb.DBFactory;

import static com.example.jerry.ui.core.ui_state.UIStateManager.UI_STATE_DB;

/**
 * Created by jerry on 2016/10/26.
 */

public class BaseApplication extends Application {
    private static BaseApplication app = null;

    private DB uiStateDB;// database for ui state



    public static synchronized BaseApplication getApp(){
        return app;
    }

    public DB getUiStateDB() {
        return uiStateDB;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
       // TypefaceProvider.registerDefaultIconSets();
        LogUtils.i("init ui database");
        try {
            uiStateDB = DBFactory.open(getApplicationContext(), UI_STATE_DB);
        }catch (Exception e){
            LogUtils.e("ui database error",e);
        }

        BaseDatabase.getInstance().open(this);

        app = this;
    }
}
