package com.example.dbman.ui.PowerIndicator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbman.ui.PowerIndicator.fragment.FragmentT1;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT2;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT3;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT4;
import com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse.power_indicator_media_browse_fragment;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;

public class PowerIndicatorDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Button> btnList = new ArrayList<>();
    private power_indicator_media_browse_fragment media_browse_fragment;
    private String eqUUID = "5809D095-7F4D-4FAD-B3B9-762236EAC2EA";
    private TextView titleView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.power_indicator_detail_activity);
        Button btnHide = (Button)this.findViewById(R.id.power_indicator_detail_showhide);
        Button buttonT1 = (Button)this.findViewById(R.id.btn_t1);
        Button buttonT2 = (Button)this.findViewById(R.id.btn_t2);
        Button buttonT3 = (Button)this.findViewById(R.id.btn_t3);
        Button buttonT4 = (Button)this.findViewById(R.id.btn_t4);
        buttonT1.setOnClickListener(this);
        buttonT2.setOnClickListener(this);
        buttonT3.setOnClickListener(this);
        buttonT4.setOnClickListener(this);

        media_browse_fragment = (power_indicator_media_browse_fragment)getFragmentManager().findFragmentById(R.id.power_indicator_detail_media_fragment);
        titleView = (TextView) findViewById(R.id.textView1);

        btnList.add(buttonT1);
        btnList.add(buttonT2);
        btnList.add(buttonT3);
        btnList.add(buttonT4);

        selectResButton(R.id.btn_t1);

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (media_browse_fragment.isHidden()){
                    media_browse_fragment.setFragmentShow(media_browse_fragment);
                }else{
                    media_browse_fragment.setFragmentHide(media_browse_fragment);
                }
            }
        });

        {
            String uuid = getIntent().getStringExtra("id");
            if (uuid != null) {
                eqUUID = uuid;
            }
        }
        {
            String name = getIntent().getStringExtra("name");
            if (name != null){
                titleView.setText(name);
            }
        }

        media_browse_fragment.refreshData(eqUUID);
    }

    private void selectResButton(int id){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (id) {
            case R.id.btn_t1:
                setBackgroundColorById(R.id.btn_t1);
                fragment = new FragmentT1();
                ft.replace(R.id.fragment_content, fragment);
                break;
            case R.id.btn_t2:
                setBackgroundColorById(R.id.btn_t2);
                fragment = new FragmentT2();
                ft.replace(R.id.fragment_content, fragment);
                break;
            case R.id.btn_t3:
                setBackgroundColorById(R.id.btn_t3);
                fragment = new FragmentT3();
                ft.replace(R.id.fragment_content, fragment);
                break;
            case R.id.btn_t4:
                setBackgroundColorById(R.id.btn_t4);
                fragment = new FragmentT4();
                ft.replace(R.id.fragment_content, fragment);
                break;
        }
        ft.commit();

        {
            Fragment pageFragment = fragment;
            //pageFragment = getFragmentManager().findFragmentById(R.id.fragment_content);
            if (pageFragment instanceof FragmentT1){
                ((FragmentT1) pageFragment).refreshData(eqUUID);
            }
            if (pageFragment instanceof FragmentT2){
                ((FragmentT2) pageFragment).refreshData(eqUUID);
            }
            if (pageFragment instanceof FragmentT3) {
                ((FragmentT3) pageFragment).refreshData(eqUUID);
            }
            if (pageFragment instanceof FragmentT4) {
                ((FragmentT4) pageFragment).refreshData(eqUUID);
            }
        }
    }

    @Override
    public void onClick(View v) {
        selectResButton(v.getId());
    }

    private void setBackgroundColorById(int btnId) {
        for (Button btn : btnList) {
            if (btn.getId() == btnId){
                //btn.setBackgroundColor(Color.GREEN);
                btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }else {
                //btn.setBackgroundColor(Color.BLUE);
                btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }



}
