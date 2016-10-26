package com.example.jerry.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jerry.ui.PowerIndicator.PowerIndicatorActivity;
import com.example.jerry.ui.core.AbstractBaseActivity;
import com.example.jerry.ui.core.ui_state.UIState;
import com.example.jerry.ui.core.ui_state.UIStateBrief;
import com.example.jerry.ui.core.ui_state.UIStateManager;
import com.example.jerry.ui.menu.CircleMenuLayout;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HomeActivity extends AbstractBaseActivity {

    private CircleMenuLayout mCircleMenuLayout;
    private static final int mItemTextResource [] ={
            R.string.title_home_1,
            R.string.title_home_2,
            R.string.title_home_3,
            R.string.title_home_4,
            R.string.title_home_5,
            R.string.title_home_6,
    };
    private static final Class<? extends AbstractBaseActivity> mItemClasses [] = new Class[]{
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class,
            PowerIndicatorActivity.class
    };

    private String[] mItemTexts = new String[mItemTextResource.length];

    private int[] mItemImgs = new int[] { R.mipmap.home_mbank_1_normal,
            R.mipmap.home_mbank_2_normal, R.mipmap.home_mbank_3_normal,
            R.mipmap.home_mbank_4_normal, R.mipmap.home_mbank_5_normal,
            R.mipmap.home_mbank_6_normal };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        for (int i=0;i<mItemTextResource.length;i++){
            mItemTexts[i] = getResources().getString(mItemTextResource[i]);
        }

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {

            @Override
            public void itemClick(View view, int pos)
            {
//                Toast.makeText(HomeActivity.this, mItemTexts[pos],
//                        Toast.LENGTH_SHORT).show();
                if ((pos < 0)|| (pos >= mItemClasses.length)) {
                    return;
                }
                switch (pos){
                    case 0:
                    {
                        Class<? extends AbstractBaseActivity> curActivityCls = mItemClasses[pos];
                        startActivity(curActivityCls);
                    }
                    break;
                    case 1:
                    {
                        List<UIState> uiStateList = UIStateManager.getInstance().getUIStates(null);
                        if ((uiStateList != null) && (uiStateList.size() > 0)){
                            UIStateBrief uiStateBrief = new UIStateBrief(uiStateList.get(0));
                            startSavedActivity(uiStateBrief);
                        }
                    }
                }

            }

            @Override
            public void itemCenterClick(View view)
            {
//                Toast.makeText(HomeActivity.this,
//                        "you can do something just like ccb  ",
//                        Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public String getShortName() {
        return "首页";
    }
}
