package com.example.dbman.ui.ScanStoreDetail.beans;

/**
 * Created by jerry on 2016/11/30.
 */

public class ScanStoreDetailStatEntry {
    private String uuid;
    private String name;
    private int count;

    public ScanStoreDetailStatEntry(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString(){
        return getName()+"("+getCount()+")";
    }
}
