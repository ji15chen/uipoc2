package com.example.dbman.db.model;

import com.example.dbman.db.genupdate.schema.ExtendType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ChenJi on 2016/11/8.
 */

public class EquipTypeExtendOption implements Serializable {
    private ExtendType extendType;
    private boolean enabled=false;
    private String name;

    public ExtendType getExtendType() {
        return extendType;
    }

    public void setExtendType(ExtendType extendType) {
        this.extendType = extendType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
