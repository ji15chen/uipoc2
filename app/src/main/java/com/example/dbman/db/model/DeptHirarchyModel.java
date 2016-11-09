package com.example.dbman.db.model;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.db.genupdate.dao.DepartmentDao;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.example.dbman.db.genupdate.schema.Department;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.unnamed.b.atv.model.TreeNode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */
public class DeptHirarchyModel implements Serializable {
    private static final String THIS_TABLE="Department";
    private static final DepartmentDao deptDao = (DepartmentDao) BaseDatabase.getInstance().getDaoImpl(THIS_TABLE);

    private TreeNode rootNode = TreeNode.root();
    private static int count = 0;
    private static DeptHirarchyModel equipHirarchyModel = null;

    public static DeptHirarchyModel getInstance(){
        if (equipHirarchyModel == null){
            equipHirarchyModel = new DeptHirarchyModel();
        }
        return equipHirarchyModel;
    }

    public DeptHirarchyModel(){
        tryAdd(deptDao, rootNode,Constants.NULL_UUID);
        LogUtils.i("adding "+ count +"nodes");
    }

    private void tryAdd(DepartmentDao deptDao, TreeNode parentNode, UUID parentUUID){
        List<Department>  equipTypes;

        try {
            equipTypes = deptDao.queryForEq("SupDeptID", parentUUID);
            if ( (equipTypes == null) || (equipTypes.size() <= 0)){
                LogUtils.w("child of "+ parentUUID +"is empty");
            }
            for (Department dept: equipTypes) {
                TreeNode childNode = new TreeNode(new DeptHirarchyModelEntry(dept));
                parentNode.addChildren(childNode);
                count++;
                tryAdd(deptDao, childNode, dept.getDeptID());
            }
        }catch (Exception e) {
            LogUtils.e("error query "+ THIS_TABLE  + e);
            e.printStackTrace();
        }
    }

    public TreeNode getRootNode(){
        return rootNode;
    }
}
