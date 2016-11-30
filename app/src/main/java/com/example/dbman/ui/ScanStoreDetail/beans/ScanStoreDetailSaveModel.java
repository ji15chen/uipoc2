package com.example.dbman.ui.ScanStoreDetail.beans;

import android.os.AsyncTask;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.db.genupdate.dao.EqmtInOutDao;
import com.example.dbman.db.genupdate.schema.EqmtInOut;
import com.example.dbman.db.genupdate.schema.PersonInfo;

import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/30.
 */

public class ScanStoreDetailSaveModel {
    private static final EqmtInOutDao eqmtInOutDao = (EqmtInOutDao)BaseDatabase.getInstance().getDaoImpl("EqmtInOut");
    private List<ScanStoreDetailStatEntry> scanStoreDetailStatEntryList;
    private boolean isIn=false;
    private String reason;
    private PersonInfo owner;

    public ScanStoreDetailSaveModel(List<ScanStoreDetailStatEntry> list){
        this.scanStoreDetailStatEntryList = list;
    }
    public List<ScanStoreDetailStatEntry> getScanStoreDetailStatEntryList() {
        return scanStoreDetailStatEntryList;
    }

    public void setScanStoreDetailStatEntryList(List<ScanStoreDetailStatEntry> scanStoreDetailStatEntryList) {
        this.scanStoreDetailStatEntryList = scanStoreDetailStatEntryList;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public boolean validate() {
        if (getOwner() == null){
           throw new RuntimeException("请选择人员");
        }

        if (getReason() == null){
            throw new RuntimeException("请选择出入库原因");
        }

        return  true;
    }

    public void save(){
        new LoadDataTask().execute(this);
    }

    private void _save(){

        if (scanStoreDetailStatEntryList == null) return;
        if (scanStoreDetailStatEntryList.size() <= 0) return;

        for(ScanStoreDetailStatEntry entry:scanStoreDetailStatEntryList){
            EqmtInOut inOut = new EqmtInOut();
            inOut.setEIOID(UUID.randomUUID());
            inOut.setPIOID(Constants.NULL_UUID);
            inOut.setReason(getReason());
            inOut.setStoreID(UUID.fromString(entry.getEntry().getUuid()));
            inOut.setScantime(entry.getEntry().getTime());
            try {
                eqmtInOutDao.create(inOut);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private  class LoadDataTask extends AsyncTask<ScanStoreDetailSaveModel, Void, Void> {
        @Override
        protected Void doInBackground(ScanStoreDetailSaveModel... params) {
            for (ScanStoreDetailSaveModel model:params){
                model.save();
            }
            return null;
        }
    }
}
