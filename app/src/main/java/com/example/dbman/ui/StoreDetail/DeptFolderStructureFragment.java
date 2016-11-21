package com.example.dbman.ui.StoreDetail;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dbman.db.model.EquipHirarchyModel;
import com.example.dbman.db.model.EquipHirarchyModelEntry;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.holder.IconTreeItemHolder;
import com.example.dbman.ui.core.holder.SelectableHeaderHolder;
import com.example.dbman.ui.core.holder.SelectableItemHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class DeptFolderStructureFragment extends Fragment {
    private AndroidTreeView tView;
    public TreeNode treeRootNode = EquipHirarchyModel.getInstance().getRootNode();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    public AndroidTreeView gettView() {
        return tView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.powerindicator_search_deptview, null, false);
        ViewGroup containerView = (ViewGroup) rootView.findViewById(R.id.container);
        setupNode(null, treeRootNode);
        tView = new AndroidTreeView(getActivity(), treeRootNode);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(IconTreeItemHolder.class);
        tView.setDefaultNodeClickListener(nodeClickListener);
        tView.setSelectionModeEnabled(true);
        if (getActivity() instanceof TreeNode.TreeNodeLongClickListener){
            tView.setDefaultNodeLongClickListener((TreeNode.TreeNodeLongClickListener)getActivity());
        }else {
            tView.setDefaultNodeLongClickListener(nodeLongClickListener);
        }
        //tView.setSelectionModeEnabled(true);
        containerView.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }

        return rootView;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.expandAll:
//                tView.expandAll();
//                break;
//
//            case R.id.collapseAll:
//                tView.collapseAll();
//                break;
//        }
//        return true;
//    }

    private int counter = 0;

    private void fillDownloadsFolder(TreeNode node) {
        TreeNode downloads = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Downloads" + (counter++)));
        node.addChild(downloads);
        if (counter < 5) {
            fillDownloadsFolder(downloads);
        }
    }

    private TreeNode.TreeNodeClickListener nodeClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {
            EquipHirarchyModelEntry item = (EquipHirarchyModelEntry) value;
            //statusBar.setText("Last clicked: " + item.text);
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            EquipHirarchyModelEntry item = (EquipHirarchyModelEntry) value;
            Toast.makeText(getActivity(), "Long click: " + item.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
