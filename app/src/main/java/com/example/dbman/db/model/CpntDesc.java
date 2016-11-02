package com.example.dbman.db.model;

import com.example.dbman.db.genupdate.schema.CpntTypes;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/11/9.
 */

public class CpntDesc implements Serializable {
    private CpntTypes cpntTypes;
    private int count;
    private boolean isMaster;

    public CpntTypes getCpntTypes() {
        return cpntTypes;
    }

    public void setCpntTypes(CpntTypes cpntTypes) {
        this.cpntTypes = cpntTypes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }
}
