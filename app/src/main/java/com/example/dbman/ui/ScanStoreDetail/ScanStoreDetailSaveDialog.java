package com.example.dbman.ui.ScanStoreDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.EqmtInOutDao;
import com.example.dbman.db.genupdate.dao.PersonInfoDao;
import com.example.dbman.db.genupdate.schema.PersonInfo;
import com.example.dbman.db.model.SysParamModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailSaveModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;
import com.example.dbman.ui.databinding.ScanStoreDetailSaveDialogBinding;

import java.util.HashMap;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailSaveDialog {
    private static final PersonInfoDao personInfoDao  = (PersonInfoDao) BaseDatabase.getInstance().getDaoImpl("PersonInfo");
    private static final EqmtInOutDao eqmtInOutDao = (EqmtInOutDao) BaseDatabase.getInstance().getDaoImpl("EqmtInOut");
    private static PersonHolder personHolder = new PersonHolder();

    private static AdapterView.OnItemSelectedListener InOut_OnItemSelectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ScanStoreDetailSaveDialogBinding binding = (ScanStoreDetailSaveDialogBinding) view.getTag();
            ScanStoreDetailSaveModel model = binding.getModelData();
            Spinner spinner = (Spinner) view;
            ArrayAdapter<String> arrayAdapter = (ArrayAdapter<String>) spinner.getAdapter();
            String value = arrayAdapter.getItem(position);
            model.setReason(value);
            model.setIn(spinner == binding.inReasonSelector);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private static AdapterView.OnItemSelectedListener Person_OnItemSelectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ScanStoreDetailSaveDialogBinding binding = (ScanStoreDetailSaveDialogBinding) view.getTag();
            ScanStoreDetailSaveModel model = binding.getModelData();
            Spinner spinner = (Spinner) view;
            ArrayAdapter<PersonInfo> arrayAdapter = (ArrayAdapter<PersonInfo>) spinner.getAdapter();
            PersonInfo value = arrayAdapter.getItem(position);
            model.setOwner(value);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    public static View createView(Context context, List<ScanStoreDetailStatEntry> stat){
        HashMap<String,ScanStoreDetailStatEntry> hashMap = new HashMap<>();
        ScanStoreDetailSaveModel model = new ScanStoreDetailSaveModel(stat);

        ScanStoreDetailSaveDialogBinding scanStoreDetailDialogBinding  = ScanStoreDetailSaveDialogBinding.inflate(LayoutInflater.from(context));
        scanStoreDetailDialogBinding.inReasonSelector.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        scanStoreDetailDialogBinding.outReasonSelector.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        scanStoreDetailDialogBinding.personSelector.setAdapter(new ArrayAdapter<PersonInfo>(context,android.R.layout.simple_spinner_item, personHolder.personInfoList));
        scanStoreDetailDialogBinding.summaryTable.setAdapter(new ArrayAdapter<ScanStoreDetailStatEntry>(context,android.R.layout.simple_list_item_1, stat));

        scanStoreDetailDialogBinding.getRoot().setTag(scanStoreDetailDialogBinding);
        scanStoreDetailDialogBinding.inReasonSelector.setTag(scanStoreDetailDialogBinding);
        scanStoreDetailDialogBinding.outReasonSelector.setTag(scanStoreDetailDialogBinding);
        scanStoreDetailDialogBinding.personSelector.setTag(scanStoreDetailDialogBinding);
        scanStoreDetailDialogBinding.summaryTable.setTag(scanStoreDetailDialogBinding);

        scanStoreDetailDialogBinding.inReasonSelector.setOnItemSelectedListener(InOut_OnItemSelectedListener);
        scanStoreDetailDialogBinding.inReasonSelector.setOnItemSelectedListener(InOut_OnItemSelectedListener);
        scanStoreDetailDialogBinding.personSelector.setOnItemSelectedListener(Person_OnItemSelectedListener);
        return scanStoreDetailDialogBinding.getRoot();
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
