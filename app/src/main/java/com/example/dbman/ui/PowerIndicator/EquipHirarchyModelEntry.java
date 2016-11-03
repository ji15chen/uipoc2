package com.example.dbman.ui.PowerIndicator;

import com.example.dbman.db.genupdate.schema.EquipType;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */

public class EquipHirarchyModelEntry implements Serializable {
    private String name;
    private UUID id;
    private UUID pid;

    public EquipHirarchyModelEntry(EquipType equipType){
        this.name = equipType.getTypeName();
        this.id = equipType.getPkTypeID();
        this.pid = equipType.getSupPkTypeID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPid() {
        return pid;
    }

    public void setPid(UUID pid) {
        this.pid = pid;
    }
}
