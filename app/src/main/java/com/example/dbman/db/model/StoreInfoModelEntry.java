package com.example.dbman.db.model;

import java.text.SimpleDateFormat;

/**
 * Created by jerry on 2016/11/10.
 */

public class StoreInfoModelEntry {
    private String time;
    private String epc;
    private String uuid;
    public String getEpc() {
        return epc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    private String currentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    public StoreInfoModelEntry(){
        time = currentTime();
    }
    private String columnValues[];

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(String[] columnValues) {
        this.columnValues = columnValues;
    }
}
