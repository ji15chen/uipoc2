package com.example.dbman.ui.PowerIndicator;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.genupdate.schema.ExentData;
import com.example.dbman.db.model.EquipHirarchyModelEntry;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.PowerIndicator.adapter.EquipTypeExtAttrAdapter;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractUIStateBindingActivity;
import com.example.dbman.ui.databinding.PowerIndicatorSearchActivityBinding;
import com.j256.ormlite.stmt.Where;
import com.unnamed.b.atv.model.TreeNode;

import java.util.List;
import java.util.UUID;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PowerIndicatorSearchActivity extends AbstractUIStateBindingActivity implements TreeNode.TreeNodeLongClickListener{
    private static final EquipTypeDao eqDao = (EquipTypeDao) BaseDatabase.getInstance().getDaoImpl("EquipType");

    private PowerIndicatorSearchActivityBinding binding ;
    private EquipTypeExtAttrAdapter extAttrAdapter;
    EquipHirarchyModelEntry selEntry = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isSupportSaveState() {
        return false;
    }

    @Override
    protected int getActivityId() {
        return R.layout.power_indicator_search_activity;
    }

    @Override
    protected Class getPresentationModelClass() {
        return PowerIndicatorModel.class;
    }

    @Override
    public boolean onLongClick(TreeNode node, Object value) {
        EquipHirarchyModelEntry item = (EquipHirarchyModelEntry) value;
        extAttrAdapter.attachSource(item.getId());
        selEntry = item;
        return true;
    }

    @Override
    protected void onFinishUIBinding(ViewDataBinding viewDataBinding) {
        binding = (PowerIndicatorSearchActivityBinding) viewDataBinding;
        extAttrAdapter = new EquipTypeExtAttrAdapter(this);
        binding.EquipTypeOptionList.setAdapter(extAttrAdapter);
        binding.buttonSearchApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selEntry == null) return;
                String advQuery = "";
                String query="";
                StringBuffer stringBuffer = new StringBuffer();

                advQuery = extAttrAdapter.getSqlExpr();
                //doesn't have advanced query
                try {
                        Where<EquipType, UUID> queryWhere = eqDao.getLeafEquipByParentUUIDQuery(selEntry.getId());
                        query = queryWhere.getStatement();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (advQuery.length() > 0) {
                        try {
                            List<UUID> uuids = EquipTypeBriefModel.lookupEquipTypeByExtAttributes(advQuery);
                            if (uuids.size() > 0) {
                                Where<EquipType, UUID> queryWhereAdv = eqDao.queryBuilder().where().in("PkTypeID", uuids);
                                query = "(" + query +" AND "+ queryWhereAdv.getStatement()+")";
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent=new Intent();
                    intent.putExtra("query", query);
                    setResult(RESULT_OK, intent);
                    finish();

            }
        });
    }

    @Override
    public String getShortName() {
        return "性能指标";
    }


    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
