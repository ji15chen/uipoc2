package com.example.dbman.ui.ScanStoreDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.EqmtInOutDao;
import com.example.dbman.db.genupdate.dao.PersonInfoDao;
import com.example.dbman.db.genupdate.schema.PersonInfo;
import com.example.dbman.db.model.SysParamModel;
import com.example.dbman.ui.R;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailSaveModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;
import com.example.dbman.ui.databinding.ScanStoreDetailSaveDialogBinding;

import java.util.HashMap;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailSaveDialog extends AlertDialog {
    private static final PersonInfoDao personInfoDao  = (PersonInfoDao) BaseDatabase.getInstance().getDaoImpl("PersonInfo");
    private static final EqmtInOutDao eqmtInOutDao = (EqmtInOutDao) BaseDatabase.getInstance().getDaoImpl("EqmtInOut");
    private static PersonHolder personHolder = new PersonHolder();

    private Context mContext;
    private ScanStoreDetailSaveModel model ;
    private ScanStoreDetailSaveDialogBinding binding;
    private  AdapterView.OnItemSelectedListener InOut_OnItemSelectedListener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;
            ArrayAdapter<String> arrayAdapter = (ArrayAdapter<String>) spinner.getAdapter();
            String value = arrayAdapter.getItem(position);
            model.setReason(value);
            model.setIn(spinner == binding.inReasonSelector);
            spinner.invalidate();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener Person_OnItemSelectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;
            ArrayAdapter<PersonInfo> arrayAdapter = (ArrayAdapter<PersonInfo>) spinner.getAdapter();
            PersonInfo value = arrayAdapter.getItem(position);
            model.setOwner(value);
            spinner.invalidate();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    public ScanStoreDetailSaveDialog(Context context,List<ScanStoreDetailStatEntry> stat) {
        super(context);
        mContext=context;
        model = new ScanStoreDetailSaveModel(stat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ScanStoreDetailSaveDialogBinding.inflate(LayoutInflater.from(mContext));
        setContentView(binding.getRoot());

        binding.setModelData(model);
        binding.inReasonSelector.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        binding.outReasonSelector.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        binding.personSelector.setAdapter(new ArrayAdapter<PersonInfo>(mContext,android.R.layout.simple_spinner_item, personHolder.personInfoList));
        binding.summaryTable.setAdapter(new ArrayAdapter<ScanStoreDetailStatEntry>(mContext,android.R.layout.simple_list_item_1, model.getScanStoreDetailStatEntryList()));

        binding.getRoot().setTag(binding);
        binding.inReasonSelector.setTag(binding);
        binding.outReasonSelector.setTag(binding);
        binding.personSelector.setTag(binding);
        binding.summaryTable.setTag(binding);

        binding.inReasonSelector.setOnItemSelectedListener(InOut_OnItemSelectedListener);
        binding.inReasonSelector.setOnItemSelectedListener(InOut_OnItemSelectedListener);
        binding.personSelector.setOnItemSelectedListener(Person_OnItemSelectedListener);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        try {
                            model.validate();
                            model.save();
                            dismiss();
                            Toast.makeText(mContext,"装备动态保存成功",Toast.LENGTH_SHORT).show();

                        }
                        catch (RuntimeException re) {
                            Toast.makeText(mContext, re.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(mContext,"装备动态保存失败",Toast.LENGTH_SHORT).show();
                        }
                    }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private static class PersonHolder{
        private List<PersonInfo> personInfoList;

        public List<PersonInfo> getPersonInfoList() {
            return personInfoList;
        }

        public PersonHolder(){
            try {
                personInfoList = personInfoDao.queryForAll();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
