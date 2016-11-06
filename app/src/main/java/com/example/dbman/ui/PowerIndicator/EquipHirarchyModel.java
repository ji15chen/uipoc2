package com.example.dbman.ui.PowerIndicator;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.core.Utils;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.unnamed.b.atv.model.TreeNode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */

public class EquipHirarchyModel implements Serializable {
    private static final String THIS_TABLE="EquipType";
    private TreeNode rootNode = TreeNode.root();
    private static int count = 0;
    private static EquipHirarchyModel equipHirarchyModel = null;

    public static EquipHirarchyModel getInstance(){
        if (equipHirarchyModel == null){
            equipHirarchyModel = new EquipHirarchyModel();
        }
        return equipHirarchyModel;
    }

    public  EquipHirarchyModel(){
        HashMap<UUID, TreeNode> treeNodeHashMap = new HashMap<UUID,TreeNode>();

        EquipTypeDaoImpl equipTypeDao = (EquipTypeDaoImpl)BaseDatabase.getInstance().getDaoImpl(THIS_TABLE);
        tryAdd(equipTypeDao, rootNode,Constants.NULL_UUID);
        LogUtils.i("adding "+ count +"nodes");
    }

    private void tryAdd(EquipTypeDaoImpl equipTypeDao,  TreeNode parentNode, UUID parentUUID){

        List<EquipType>  equipTypes;

        try {
            equipTypes = equipTypeDao.queryForEq("SupPkTypeID", parentUUID);
            if ( (equipTypes == null) || (equipTypes.size() <= 0)){
                LogUtils.w("child of "+ parentUUID +"is empty");
            }
            for (EquipType equipType: equipTypes){
                TreeNode childNode = new TreeNode(new EquipHirarchyModelEntry(equipType));
                parentNode.addChildren(childNode);
                count++;
                tryAdd(equipTypeDao, childNode, equipType.getPkTypeID());
            }
        }catch (Exception e){
            LogUtils.e("error query "+ THIS_TABLE  + e);
            e.printStackTrace();
        }
    }

    public TreeNode getRootNode(){
        return rootNode;
    }
}
