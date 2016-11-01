package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.EquipTypeExtendOption;
import com.example.dbman.db.model.EquipTypeExtendOptionsModel;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.j256.ormlite.stmt.Where;
import com.meetme.android.horizontallistview.HorizontalListView;

/**
 * Created by jerry on 2016/11/19.
 */

public class EquipTypeExtAttrAdapter extends BaseAdapter implements ListAdapter {
    private static final EquipTypeDao eqDao = (EquipTypeDao) BaseDatabase.getInstance().getDaoImpl("EquipType");

    private final LayoutInflater inflater;
    private final Context context;

    private List<Pair<String, List<EquipTypeExtendOption>>> pairList = new ArrayList<Pair<String, List<EquipTypeExtendOption>>>();

    public EquipTypeExtAttrAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public synchronized void attachSource(UUID equipTypeUUID){
        try {
            pairList.clear();

            Where<EquipType, UUID> where = eqDao.getLeafEquipByParentUUIDQuery(equipTypeUUID);
            String equipTypeUUIDWhere = where.getStatement();
            EquipTypeExtendOptionsModel equipTypeExtendOptionsModel = EquipTypeExtendOptionsModel.loadEquipTypeExtendOptionsModel(equipTypeUUIDWhere);
            for (Map.Entry<String,Map<EquipTypeExtendOption,EquipTypeExtendOption>> entry: equipTypeExtendOptionsModel.getOptionMap().entrySet()){
                Map<EquipTypeExtendOption,EquipTypeExtendOption> map = entry.getValue();
                List<EquipTypeExtendOption> listOption = new ArrayList<>();
                for (EquipTypeExtendOption option: map.keySet()){
                    //skip invalid date range
                    if ( (option.getExtendType().getExtendMin().intValue() == 0)
                        &&
                        (option.getExtendType().getExtendMax().intValue() == 0)
                            ){
                        continue;
                    }

                    listOption.add(option);
                }
                if (listOption.size() > 0) {
                    pairList.add(new Pair(entry.getKey(), listOption));
                }
            }
            this.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return pairList.size();
    }

    @Override
    public Object getItem(int position) {
        return pairList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EquipTypeExtOptionViewHolder holder;
        Pair<String, List<EquipTypeExtendOption>> option = (Pair<String, List<EquipTypeExtendOption>>) getItem(position);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.powerindicator_search_adv_item_view, parent, false);
            holder = new EquipTypeExtOptionViewHolder();
            holder.OptionListName = (TextView) convertView.findViewById(R.id.OptionListName);
            holder.OptionListContents = (HorizontalListView) convertView.findViewById(R.id.OptionListContents);
            convertView.setTag(holder);
        }else{
            holder = (EquipTypeExtOptionViewHolder)convertView.getTag();
        }
        holder.OptionListName.setText(option.first);
        holder.OptionListContents.setAdapter(new EquipTypeExtOptionAdapter(context, option.second));

        return convertView;
    }

    static class EquipTypeExtOptionViewHolder
    {
        public TextView OptionListName;
        public HorizontalListView OptionListContents;
        public EquipTypeExtOptionAdapter optionAdapter;
    }

    private final String getSqlExpr(final EquipTypeExtendOption option){
        StringBuffer sb = new StringBuffer();
        if (option.isEnabled()){
            sb.append("(");

            sb.append("(");
            sb.append("current_extend_value >= ");
            sb.append(option.getExtendType().getExtendMin().toString());
            sb.append(" AND ");
            sb.append("current_extend_value <= ");
            sb.append(option.getExtendType().getExtendMax().toString());
            sb.append(")");
            sb.append(" AND ExentData.ExtendID = '");
            sb.append(option.getId().toString());
            sb.append("'");
            sb.append(")");
        }
        return sb.toString();
    }

    private final String getSqlExpr(List<EquipTypeExtendOption> optionList){
        int count = 0;
        StringBuffer sb = new StringBuffer();

        sb.append("(\n");
        for (EquipTypeExtendOption option:optionList){
            if (!option.isEnabled()) continue;
            sb.append(getSqlExpr(option));
            if (count > 0){
                sb.append(" OR ");
            }
            count++;
        }
        sb.append(")\n");
        if (count > 0) {
            return sb.toString();
        }else {
            return "";
        }
    }

    public final String getSqlExpr(){
        int count = 0;
        StringBuffer sb = new StringBuffer();
        String cond;
        sb.append("(\n");
        for (Pair<String, List<EquipTypeExtendOption>> pair :pairList){
            cond = getSqlExpr(pair.second);
            if (cond.length() <= 0) continue;

            sb.append(cond);
            if (count > 0){
                sb.append(" AND ");
            }
            count++;
        }
        sb.append(")\n");
        if (count > 0) {
            return sb.toString();
        }else {
            return  "";
        }
    }
}
