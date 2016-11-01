package com.example.dbman.ui.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dbman.ui.Home.HomeViewFragment;
import com.example.dbman.ui.core.ui_state.UIStateBrief;
import com.example.dbman.ui.core.ui_state.UIStateManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {
    public abstract  String getShortName();

    public void startSavedActivity(UIStateBrief uiStateBrief){
        Bundle bundle = UIStateBrief.createBundleFromStateBrief(uiStateBrief);
        Intent saveIntent = new Intent(this,uiStateBrief.getActivityClass());
        saveIntent.putExtra(UIStateManager.LAST_UI_STATE, bundle );
        startActivity(saveIntent);
    }

    public void startActivity(Class<? extends AbstractBaseActivity> activityClass) {
        Intent saveIntent = new Intent(this, activityClass);
        startActivity(saveIntent);
    }

    public void startActivityForResult(Class<? extends AbstractBaseActivity> activityClass, int requestCode) {
        Intent saveIntent = new Intent(this, activityClass);
        startActivityForResult(saveIntent, requestCode);
    }
}
