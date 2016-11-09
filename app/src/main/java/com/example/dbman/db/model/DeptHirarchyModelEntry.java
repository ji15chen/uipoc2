package com.example.dbman.db.model;

import com.example.dbman.db.genupdate.schema.Department;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.ui.R;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */

public class DeptHirarchyModelEntry implements Serializable {
    private String name;
    private UUID id;
    private UUID pid;
    private int icon;

    public DeptHirarchyModelEntry(Department department){
        this.name = department.getDeptName();
        this.id = department.getDeptID();
        this.pid = department.getSupDeptID();
        this.setIcon(R.string.ic_folder);
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

    public String toString(){
        return getName();
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
