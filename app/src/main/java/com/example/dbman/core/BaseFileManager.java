package com.example.dbman.core;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2016/11/13 0013.
 */

public class BaseFileManager {
    private static String FILE_DATA_NAME ="fileData";
    private static String FILE_CACHE_NAME ="fileCache";

    private  static String FILE_DATA_PATH ;
    private static String FILE_CACHE_PATH;

    public static void init(Context app){
        FILE_DATA_PATH = "/data/data/" + app.getPackageName()+"/"+FILE_DATA_NAME;
        FILE_CACHE_PATH = "/data/data/" +  app.getPackageName() +"/" + FILE_CACHE_NAME;
    }

    public static File getFilePath(String fileName, boolean isThumb){
        String rootPath = isThumb?FILE_CACHE_PATH:FILE_DATA_PATH;
        return new File(rootPath, fileName);
    }
}
