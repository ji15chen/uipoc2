package com.example.dbman.ui.core;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.example.dbman.core.BaseApplication;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.ui_state.UIStateBrief;
import com.example.dbman.ui.core.ui_state.UIStateManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public abstract class AbstractBaseUIActivity extends AbstractBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
}
