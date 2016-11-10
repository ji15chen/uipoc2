package com.example.dbman.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.DeptHirarchyModel;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.db.model.EquipTypeCpntSetModel;
import com.example.dbman.db.model.EquipTypeFactorySetModel;
import com.example.dbman.db.model.EquipTypeParamSetModel;
import com.example.dbman.db.model.EquipHirarchyModel;
import com.example.dbman.db.model.StoreInfoModel;
import com.j256.ormlite.dao.CloseableIterator;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <textValue href="http://d.android.com/tools/testing">Testing documentation</textValue>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.jerry.skeldbui", appContext.getPackageName());
    }
    @Test
    public void testEquipType() throws Exception {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
        try {
            Context appContext = InstrumentationRegistry.getTargetContext();
            BaseDatabase.getInstance().init(appContext);
            //1.解析树形结构
            EquipHirarchyModel.getInstance();
            assertTrue(true);
            EquipTypeDaoImpl impl =(EquipTypeDaoImpl)BaseDatabase.getInstance().getDaoImpl("EquipType");
            //2.通过相似名称查找记录
            CloseableIterator<EquipType> iter = impl.findBySimilarTypeName("车");
            assertTrue(iter.hasNext());
            {
                int count =0;
                try {
                    while (iter.hasNext()) {
                        count++;
                        LogUtils.i(iter.nextThrow());
                    }
                }finally {
                    iter.closeQuietly();
                }
                assertTrue(count == 311);
            }
            //装备类型概要查询
            {
                CloseableIterator<EquipTypeBriefModel> iter1 = impl.lookupBriefEquipTypeInfo(impl.getLeafEquipByParentUUIDQuery(UUID.fromString("0FC40DF9-6534-44DA-9018-00EF24CA5E3F")));
                try {
                    while (iter1.hasNext()) {
                        EquipTypeBriefModel res = iter1.nextThrow();
                        LogUtils.e(res);
                    }
                }finally {
                    iter1.closeQuietly();
                }
                iter = impl.findBySimilarTypeName("越野车");
                iter1 = impl.lookupBriefEquipTypeInfo(impl.getLeafEquipByParentObjectsQuery(iter));
                try {
                    while (iter1.hasNext()) {
                        EquipTypeBriefModel res  = iter1.nextThrow();
                        LogUtils.e(res);
                    }
                }finally {
                    iter1.closeQuietly();
                }
            }
            //装备类型  属性明细
            {
                EquipTypeParamSetModel paramSetView = EquipTypeParamSetModel.loadEquipTypeParamSetView(UUID.fromString("5809D095-7F4D-4FAD-B3B9-762236EAC2EA"));
                assertTrue(paramSetView != null);
                assertTrue(paramSetView.getLstEquipExtParam().size() == 6);
                LogUtils.i(paramSetView.getLstEquipExtParam());
                assertTrue(paramSetView.getLstEquipTypeParam().size() == 5);
                LogUtils.i(paramSetView.getLstEquipTypeParam());
            }
            //装备类型，工厂信息
            {
                EquipTypeFactorySetModel factoryView = EquipTypeFactorySetModel.loadEquipFactorySet(UUID.fromString("5809D095-7F4D-4FAD-B3B9-762236EAC2EA"));
                assertTrue(factoryView != null);
                assertTrue(factoryView.getLstEquipFactories().size() == 7);
                LogUtils.i(factoryView.getLstEquipFactories());
            }
            //装备类型，组件信息
            {
                EquipTypeCpntSetModel cpntView = EquipTypeCpntSetModel.loadEquipCpntSet(UUID.fromString("5809D095-7F4D-4FAD-B3B9-762236EAC2EA"));
                assertTrue(cpntView != null);
                assertTrue(cpntView.getLstEquipCpnts().size() == 1);
                LogUtils.i(cpntView.getLstEquipCpnts());
            }
        }catch (Exception e){
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testStoreDetail() throws Exception{
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
        try {
            Context appContext = InstrumentationRegistry.getTargetContext();
            BaseDatabase.getInstance().init(appContext);

            //解析部门树形图
            DeptHirarchyModel.getInstance();
            assertTrue(true);

            //查询实力明细
            StoreInfoModel model = StoreInfoModel.loadEquipStoreDetail("");
            assertTrue(model != null);
            assertTrue(model.getLstEquipStoreInfo().size() == 37);
            LogUtils.i(model.getLstEquipStoreInfo());
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}
