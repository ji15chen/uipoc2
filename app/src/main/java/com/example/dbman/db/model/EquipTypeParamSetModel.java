package com.example.dbman.db.model;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.DBUtil;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.dao.EquipTypeDetailDao;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
import com.j256.ormlite.dao.GenericRawResults;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenJi on 2016/11/9.
 */

public class EquipTypeParamSetModel {
    private static final EquipTypeDao eqDao = (EquipTypeDao)BaseDatabase.getInstance().getDaoImpl("EquipType");
    private static final EquipTypeDetailDao edDao = (EquipTypeDetailDao)BaseDatabase.getInstance().getDaoImpl("EquipTypeDetail");

    private List<EquipTypeParam> lstEquipTypeParam = new ArrayList<EquipTypeParam>();
    private List<EquipTypeDetail> lstEquipExtParam = new ArrayList<EquipTypeDetail>();

    public static class EquipTypeParam implements Serializable {
        private String paraName;
        private String paraValue;
        private String paraUnit;
        public String getParaName() {
            return paraName;
        }

        public void setParaName(String paraName) {
            this.paraName = paraName;
        }

        public String getParaValue() {
            return paraValue;
        }

        public void setParaValue(String paraValue) {
            this.paraValue = paraValue;
        }

        public String getParaUnit() {
            return paraUnit;
        }

        public void setParaUnit(String paraUnit) {
            this.paraUnit = paraUnit;
        }
    }

    public static EquipTypeParamSetModel loadEquipTypeParamSetView(final UUID uuid) throws SQLException{
        StringBuffer stringBuffer = new StringBuffer();
        //[1] 查询相关设备参数
        final String sql = "SELECT ExentData.ObjectID,ExtendType.ExtendName,ExentData.ExtentValue,SysParameter.ParaName FROM ExentData LEFT JOIN ExtendType on ExentData.ExtendID = ExtendType.ExtendID LEFT JOIN SysParameter ON SysParameter.ParaID=ExentData.ObjectID WHERE  ExentData.ObjectID = '";
        stringBuffer.append(sql);
        stringBuffer.append(uuid.toString().toUpperCase());
        stringBuffer.append("'");

        EquipTypeParamSetModel view = new EquipTypeParamSetModel();
        GenericRawResults<String []> v = eqDao.queryRaw(stringBuffer.toString(), DBUtil.stringArrayMapper);
        Iterator<String []> iter = v.iterator();

        while(iter.hasNext()){
            String [] value = iter.next();
            EquipTypeParam param = new EquipTypeParam();
            param.setParaName(value[1]);
            param.setParaValue(value[2]);
            param.setParaUnit(value[3]);
            view.lstEquipTypeParam.add(param);
        }
        //[2] 查询相关扩展属性
        view.lstEquipExtParam = edDao.queryForEq("PkTypeID", uuid);
        return view;
    }


    public List<EquipTypeParam> getLstEquipTypeParam() {
        return lstEquipTypeParam;
    }

    public void setLstEquipTypeParam(List<EquipTypeParam> lstEquipTypeParam) {
        this.lstEquipTypeParam = lstEquipTypeParam;
    }

    public List<EquipTypeDetail> getLstEquipExtParam() {
        return lstEquipExtParam;
    }

    public void setLstEquipExtParam(List<EquipTypeDetail> lstEquipExtParam) {
        this.lstEquipExtParam = lstEquipExtParam;
    }
}
