package com.example.dbman.core;

/**
 * Created by Administrator on 2016/11/13 0013.
 */

public class BaseFunctionDesc {
    private String functionName;
    private int functionIcon;
    private int functionBg;
    private Class functionActivityClass;

    public BaseFunctionDesc(String functionName, Class functionActivityClass, int functionIcon, int functionBg){
        this.functionName=functionName;
        this.functionActivityClass=functionActivityClass;
        this.functionIcon=functionIcon;
        this.functionBg=functionBg;
    }
}
