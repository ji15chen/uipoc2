package com.example.dbman.ui.PowerIndicator;

import com.unnamed.b.atv.model.TreeNode;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class PowerIndicatorModel implements Serializable{
    public String textValue;
    public TreeNode treeNode;

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public PowerIndicatorModel(){

        textValue ="test";
    }
}
