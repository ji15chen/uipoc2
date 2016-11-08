package com.example.dbman.db.model;

import com.example.dbman.db.genupdate.schema.EquipType;
import com.j256.ormlite.stmt.Where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/8.
 */

public class EquipTypeExtendOptionsModel implements Serializable {
    List<EquipTypeExtendOption> optionList = new ArrayList<EquipTypeExtendOption>();

    public static EquipTypeExtendOptionsModel loadEquipTypeExtendOptionsModel(Where<EquipType,UUID> query){
        EquipTypeExtendOptionsModel model = new EquipTypeExtendOptionsModel();
        //TODO: implement
        return model;
    }
}
