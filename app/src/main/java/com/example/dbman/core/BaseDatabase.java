package com.example.dbman.core;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.db.core.Database;
import com.example.dbman.ui.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by ChenJi on 2016/10/26.
 */

public class BaseDatabase {
    private static final String DB_NAME="dbman.db";
    private static BaseDatabase baseDatabase = new BaseDatabase();
    private static String DATABASE_PATH;
    private static String DATABASE_URL;
    private static String DATABASE_DIR;

    public static BaseDatabase getInstance() {
        return baseDatabase;
    }

    private SQLiteDatabase db;

    private void createDBIfNotExist(){
             File dir = new File(DATABASE_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }//如果该目录不存在，创建该目录

            File filepath = new File(DATABASE_PATH);
            if (!filepath.exists()) {//如果文件不存在
                try {
                    InputStream inputStream = BaseApplication.getApp().getResources().openRawResource(R.raw.dbman);//将raw中的test.db放入输入流中
                    FileOutputStream fileOutputStream = new FileOutputStream(DATABASE_PATH);//将新的文件放入输出流中
                    byte[] buff = new byte[8192];
                    int len = 0;
                    while ((len = inputStream.read(buff)) > 0) {
                        fileOutputStream.write(buff, 0, len);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                } catch (Exception e) {
                    Log.i("info","无法复制");
                    e.printStackTrace();
                }
            }//写入文件结束
            Log.i("filepath"," "+filepath);
            SQLiteDatabase database = SQLiteDatabase.openDatabase(filepath.getPath(),null,SQLiteDatabase.OPEN_READWRITE);//利用openDatabase方法打开数据库。
            return database;
        }
    }

    public boolean init(Application app){
        try {
            DATABASE_DIR = "/data/data/" + app.getPackageName();
            DATABASE_PATH = DATABASE_DIR + "/" + DB_NAME;
            DATABASE_URL = "jdbc:sqlite:/"+ DATABASE_PATH;
            try {
                DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
                Database.main(new String[]{"", ""});
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            LogUtils.e("ui database error",e);
            return false;
        }
    }



}
