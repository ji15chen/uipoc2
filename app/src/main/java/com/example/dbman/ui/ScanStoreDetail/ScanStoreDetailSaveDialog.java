package com.example.dbman.ui.ScanStoreDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.BaseFileManager;
import com.example.dbman.db.genupdate.dao.EqmtInOutDao;
import com.example.dbman.db.genupdate.dao.PersonInfoDao;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.PersonInfo;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.db.model.SysParamModel;
import com.example.dbman.ui.ScanStoreDetail.beans.ScanStoreDetailStatEntry;
import com.example.dbman.ui.databinding.ScanStoreDetailDialogBinding;
import com.example.dbman.ui.databinding.ScanStoreDetailSaveDialogBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailSaveDialog {
    private static final PersonInfoDao personInfoDao  = (PersonInfoDao) BaseDatabase.getInstance().getDaoImpl("PersonInfo");
    private static final EqmtInOutDao eqmtInOutDao = (EqmtInOutDao) BaseDatabase.getInstance().getDaoImpl("EqmtInOut");
    private static PersonHolder personHolder = new PersonHolder();


    public static View createView(Context context, List<StoreInfoModelEntry> entries){
        HashMap<String,ScanStoreDetailStatEntry> hashMap = new HashMap<>();
        List<ScanStoreDetailStatEntry> stat = new ArrayList<>();

        ScanStoreDetailStatEntry statEntry;
        for (StoreInfoModelEntry entry:entries){
            statEntry = hashMap.get(entry.getTypeUUid());
            if (statEntry == null){
                statEntry = new ScanStoreDetailStatEntry(entry.getTypeUUid());
                statEntry.setName(entry.getColumnValues()[1]);
                hashMap.put(entry.getTypeUUid(), statEntry);
            }
            statEntry.setCount(statEntry.getCount() + 1);
        }

        stat.addAll(hashMap.values());
        ScanStoreDetailSaveDialogBinding scanStoreDetailDialogBinding  = ScanStoreDetailSaveDialogBinding.inflate(LayoutInflater.from(context));
        scanStoreDetailDialogBinding.inReasonSelector.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        scanStoreDetailDialogBinding.outReasonSelector.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, SysParamModel.getInstance().getInoutReason()));
        scanStoreDetailDialogBinding.personSelector.setAdapter(new ArrayAdapter<PersonInfo>(context,android.R.layout.simple_spinner_item, personHolder.personInfoList));
        scanStoreDetailDialogBinding.summaryTable.setAdapter(new ArrayAdapter<ScanStoreDetailStatEntry>(context,android.R.layout.simple_list_item_1, stat));
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
