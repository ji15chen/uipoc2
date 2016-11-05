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
        for (EquipType equipType:equipTypeDao){
            EquipHirarchyModelEntry entry = new EquipHirarchyModelEntry(equipType);
            TreeNode curNode = new TreeNode(entry);
            tryAdd(equipTypeDao, treeNodeHashMap,curNode);
        }
    }

    private boolean tryAdd(final EquipTypeDaoImpl equipTypeDao,HashMap<UUID, TreeNode> treeNodeHashMap, TreeNode curNode){
        EquipHirarchyModelEntry entry = (EquipHirarchyModelEntry)curNode.getValue();
        LogUtils.i(entry);
        if (Utils.isNullUUID(entry.getId())) {
            return true;
        }

        if (treeNodeHashMap.containsKey(curNode.getId())){
            return true;
        }

        if (Utils.isNullUUID(entry.getPid())){
            rootNode.addChild(curNode);
            treeNodeHashMap.put(entry.getId(), curNode);
            return true;
        }

        UUID pid = entry.getPid();
        TreeNode pNode = treeNodeHashMap.get(pid);

        if (pNode != null){
            pNode.addChild(curNode);
            treeNodeHashMap.put(entry.getId(), curNode);
            return true;
        }

        EquipType equipType = null;
        try {
                equipType =equipTypeDao.queryForId(pid);
        } catch (Exception e){
                LogUtils.e("error query "+ THIS_TABLE   +"id = ", entry.getPid(),e);
                equipType = null;
        }
        if (equipType == null){
            LogUtils.e("Can't find parent Object for ", entry);
             return false;
        }

        pNode = new TreeNode(new EquipHirarchyModelEntry(equipType));
        if (tryAdd(equipTypeDao, treeNodeHashMap, pNode)){
            pNode.addChild(curNode);
            treeNodeHashMap.put(entry.getId(), curNode);
            return true;
        }
        return false;
    }

    public TreeNode getRootNode(){
        return rootNode;
    }
}
