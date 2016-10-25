package com.example.jerry.core;

import android.app.Application;
import android.content.Context;

/**
 * Created by jerry on 2016/10/26.
 */

public class BaseApplication extends Application {
    private static BaseApplication app = null;

    public static synchronized BaseApplication getApp(){
        return app;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
    }
}
