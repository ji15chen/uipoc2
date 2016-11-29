package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.SysParameterDao;
import com.example.dbman.db.genupdate.schema.SysParameter;
import com.j256.ormlite.dao.GenericRawResults;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jerry on 2016/11/3.
 */

public class SysParamModel {
    private List<String> inoutReason = new ArrayList<>();
    private static final SysParameterDao sysParameterDao = (SysParameterDao)BaseDatabase.getInstance().getDaoImpl("SysParameter");
    private static SysParamModel inst = null;
    public static SysParamModel getInstance(){
        if (inst == null){
            inst = new SysParamModel();
        }
        return inst;
    }

    public SysParamModel(){
        getInOutReason();
    }

    private void getInOutReason(){
        try {
            inoutReason.clear();
            GenericRawResults<String []> rawResults =sysParameterDao.queryRaw("select ParaName from SysParameter WHERE ParentID='00000000-0000-0000-0000-000000000013'", DBUtil.stringArrayMapper);
            Iterator<String []> iter = rawResults.iterator();
            while(iter.hasNext()){
                String [] arr = iter.next();
                inoutReason.add(arr[0]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getInoutReason() {
        return inoutReason;
    }
}
