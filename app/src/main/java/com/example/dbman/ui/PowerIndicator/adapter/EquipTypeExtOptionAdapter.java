package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.EquipTypeExtendOption;
import com.example.dbman.db.model.EquipTypeExtendOptionsModel;
import com.example.dbman.db.model.SysParameterModel;
import com.example.dbman.ui.R;
import com.j256.ormlite.stmt.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/19.
 */

public class EquipTypeExtOptionAdapter extends BaseAdapter implements ListAdapter,CompoundButton.OnCheckedChangeListener {
    private final LayoutInflater inflater;
    private final Context context;

    List<EquipTypeExtendOption> optionList = new ArrayList<>();

    public EquipTypeExtOptionAdapter(Context context, List<EquipTypeExtendOption> optionList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.optionList=optionList;
    }

    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Object getItem(int position) {
        return optionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EquipTypeExtOptionViewHolder holder = null;
        EquipTypeExtendOption option = (EquipTypeExtendOption) getItem(position);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.powerindicator_search_adv_item_option_view, parent, false);
            holder = new EquipTypeExtOptionViewHolder();
            holder.optionCheck = (CheckBox) convertView.findViewById(R.id.OptionCheck);
            holder.optionCheck.setOnCheckedChangeListener(this);
            holder.optionCheck.setTag(holder);
            convertView.setTag(holder);
        }else{
            holder = (EquipTypeExtOptionViewHolder)convertView.getTag();
        }
        {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(option.getExtendType().getExtendMin().toString());
            stringBuffer.append("-");
            stringBuffer.append(option.getExtendType().getExtendMax().toString());
            stringBuffer.append(getVarUnit(option));
            holder.optionCheck.setText(stringBuffer.toString());
        }
        holder.position = position;
        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkBox = (CheckBox) buttonView;
        EquipTypeExtOptionViewHolder holder = (EquipTypeExtOptionViewHolder) checkBox.getTag();
        int position = holder.position;
        EquipTypeExtendOption option = (EquipTypeExtendOption)getItem(position);
        if (option == null) return;
        option.setEnabled(isChecked);
    }

    private String getVarUnit(EquipTypeExtendOption option){
        if (SysParameterModel.getModel().getParamMap().containsKey(option.getExtendType().getExtendUnit())){
            return SysParameterModel.getModel().getParamMap().get(option.getExtendType().getExtendUnit());
        }else {
            LogUtils.w("Doesn't have unit id:"+ option.getExtendType().getExtendUnit().toString());
            return "";
        }
    }
    static class EquipTypeExtOptionViewHolder
    {
        public CheckBox optionCheck;
        public int position;
    }


}
