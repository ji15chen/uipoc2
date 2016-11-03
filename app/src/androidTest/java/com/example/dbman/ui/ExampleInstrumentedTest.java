package com.example.dbman.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dbman.core.BaseApplication;
import com.example.dbman.db.core.Database;

import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void useApp() throws Exception {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
        BaseApplication application = new BaseApplication();
        Database.main(new String[]{"",""});
    }
}
