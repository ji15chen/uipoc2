package com.example.dbman.ui.PowerIndicator;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.db.model.EquipHirarchyModel;
import com.example.dbman.db.model.EquipHirarchyModelEntry;
import com.example.dbman.ui.core.holder.SelectableHeaderHolder;
import com.example.dbman.ui.core.holder.SelectableItemHolder;
import com.example.dbman.ui.R;
import com.unnamed.b.atv.model.TreeNode;

import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class SelectableTreeFragment extends Fragment {
    private AndroidTreeView tView;
    public TreeNode treeRootNode = EquipHirarchyModel.getInstance().getRootNode();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_selectable_nodes, container, true);
        ViewGroup containerView = (ViewGroup) rootView.findViewById(R.id.container);

        setupNode(null,treeRootNode);
        tView = new AndroidTreeView(getActivity(), treeRootNode);
        tView.setDefaultAnimation(false);
        containerView.addView(tView.getView());
        tView.setSelectionModeEnabled(true);
        //tView.expandAll();

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
        return rootView;
    }

    private void setupNode(TreeNode parent, TreeNode treeNode){
        List<TreeNode> childs = treeNode.getChildren();
        for (TreeNode childNode: childs){
            setupNode(treeNode, childNode);
        }

        EquipHirarchyModelEntry entry = (EquipHirarchyModelEntry) treeNode.getValue();
        if (treeNode != treeRootNode){
            if (treeNode.getChildren().size()>0){
                entry.setIcon(R.string.ic_folder);
                treeNode.setViewHolder(new SelectableHeaderHolder(getActivity()));
            }else{
                entry.setIcon(R.string.ic_flag);
                treeNode.setViewHolder(new SelectableItemHolder(getActivity()));
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
