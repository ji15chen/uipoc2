package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.dao.SysParameterDao;
import com.example.dbman.db.genupdate.schema.SysParameter;
import com.j256.ormlite.dao.CloseableIterator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by jerry on 2016/11/1.
 */

public class SysParameterModel {
    private static final SysParameterDao sysParameterDao = (SysParameterDao)BaseDatabase.getInstance().getDaoImpl("SysParameter");
    private static SysParameterModel model = null;

    private HashMap<UUID,String> paramMap;
    public static final SysParameterModel getModel(){
        if (model == null){
            model = new SysParameterModel();
        }
        return  model;
    }

    public SysParameterModel() {
        paramMap = new HashMap<UUID,String>();
        try {
            CloseableIterator<SysParameter> iter = sysParameterDao.iterator();
            SysParameter param;
            while (iter.hasNext()) {
                param = iter.nextThrow();
                paramMap.put(param.getId(), param.getParaName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<UUID, String> getParamMap() {
        return paramMap;
    }
}
