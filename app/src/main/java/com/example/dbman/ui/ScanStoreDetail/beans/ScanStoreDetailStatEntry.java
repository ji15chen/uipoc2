package com.example.dbman.ui.ScanStoreDetail.beans;

import com.example.dbman.db.model.StoreInfoModelEntry;

/**
 * Created by jerry on 2016/11/30.
 */

public class ScanStoreDetailStatEntry {
    private String uuid;
    private String name;
    private int count;
    private int total;
    private int cur_total;
    private StoreInfoModelEntry entry;

    private static int getTotal(String total){
        int nTotal = 0;
        try{
            nTotal = Integer.parseInt(total);
        }catch (Exception e){
            nTotal = 0;
        }
        return nTotal;
    }

    public void add(int n){
        count +=n;
        cur_total+= n*getTotal();
    }

    public int getCur_total() {
        return cur_total;
    }

    public void setCur_total(int cur_total) {
        this.cur_total = cur_total;
    }

    public ScanStoreDetailStatEntry(StoreInfoModelEntry entry) {
        this.entry = entry;
        setUuid(entry.getUuid());
        setName(entry.getColumnValues()[2]);
        setCount(1);
        setTotal(getTotal(entry.getColumnValues()[4]));
        setCur_total(getTotal());
    }

    public StoreInfoModelEntry getEntry() {
        return entry;
    }

    public void setEntry(StoreInfoModelEntry entry) {
        this.entry = entry;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        return getName()+"("+getCur_total()+")";
    }
}
