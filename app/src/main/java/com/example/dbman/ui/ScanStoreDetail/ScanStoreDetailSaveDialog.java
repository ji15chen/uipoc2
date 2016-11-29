package com.example.dbman.ui.ScanStoreDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.BaseFileManager;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.model.StoreInfoModelEntry;
import com.example.dbman.ui.databinding.ScanStoreDetailDialogBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScanStoreDetailSaveDialog extends AlertDialog implements View.OnClickListener{
    private static final SysFileInfoDao sysFileInfoDao  = (SysFileInfoDao) BaseDatabase.getInstance().getDaoImpl("SysFileInfo");
    private ScanStoreDetailDialogBinding binding;
    private StoreInfoModelEntry entry;
    public ScanStoreDetailSaveDialog(Context context, StoreInfoModelEntry entry) {
        super(context);
        this.entry = entry;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static View createView(Context context, StoreInfoModelEntry entry){
        ScanStoreDetailDialogBinding scanStoreDetailDialogBinding  = ScanStoreDetailDialogBinding.inflate(LayoutInflater.from(context));
        scanStoreDetailDialogBinding.setModelData(entry);
        String uuid = entry.getUuid();
        List<SysFileInfo> sfi = getImages(uuid);
        if (sfi.size() > 0) {
            scanStoreDetailDialogBinding.image.setImageURI(
                    Uri.fromFile(BaseFileManager.getFilePath(sfi.get(0),false))
            );
        }
        return scanStoreDetailDialogBinding.getRoot();
    }
    protected static List<SysFileInfo> getImages(String  uuid) {
        List<SysFileInfo> sysFileInfos = new ArrayList<>();
        List<SysFileInfo> fileList;
        try {
            fileList = sysFileInfoDao.findSysFile(UUID.fromString(uuid));
            for (SysFileInfo file:fileList){
                SysFileInfoDao.SysFileType type = sysFileInfoDao.getFileType(file);
                if (type == SysFileInfoDao.SysFileType.FILE_TYPE_IMAGE) {
                    sysFileInfos.add(file);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return sysFileInfos;
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
