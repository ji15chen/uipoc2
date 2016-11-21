package com.example.dbman.ui.PowerIndicator.bean;

import android.content.Context;

/**
 * Created by likai on 11/18/16.
 */

public class ActorBean {
    public String name;

    public String picName;

    public ActorBean(String name, String picName)
    {
        this.name = name;
        this.picName = picName;
    }

    public int getImageResourceId( Context context )
    {
        try
        {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
