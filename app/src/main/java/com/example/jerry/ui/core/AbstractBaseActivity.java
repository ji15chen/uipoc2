package com.example.jerry.ui.core;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.jerry.ui.PowerIndicator.PowerIndicatorActivity;
import com.example.jerry.ui.core.ui_state.UIStateBrief;
import com.example.jerry.ui.core.ui_state.UIStateManager;

import java.io.Serializable;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public abstract class AbstractBaseActivity extends Activity {
    public abstract  String getShortName();

    public void startSavedActivity(UIStateBrief uiStateBrief){
        Bundle bundle = UIStateBrief.createBundleFromStateBrief(uiStateBrief);
        Intent saveIntent = new Intent(this,uiStateBrief.getActivityClass());
        saveIntent.putExtra(UIStateManager.LAST_UI_STATE, bundle );
        startActivity(saveIntent);
    }

    public void startActivity(Class<? extends AbstractBaseActivity> activityClass){
        Intent saveIntent = new Intent(this, activityClass);
        startActivity(saveIntent);
    }
}
