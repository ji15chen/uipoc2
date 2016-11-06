package com.example.dbman.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dbman.core.BaseApplication;
import com.example.dbman.core.BaseDatabase;
import com.example.dbman.db.core.Database;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.ui.PowerIndicator.EquipHirarchyModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import dalvik.annotation.TestTargetClass;

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
            EquipHirarchyModel.getInstance();
            assertTrue(true);
            EquipTypeDaoImpl impl =(EquipTypeDaoImpl)BaseDatabase.getInstance().getDaoImpl("EquipType");
            List<EquipType> lsts = impl.findBySimilarTypeName("车");
            assertTrue(lsts.size() == 311);
        }catch (Exception e){
            assertFalse(true);
        }
    }
}
