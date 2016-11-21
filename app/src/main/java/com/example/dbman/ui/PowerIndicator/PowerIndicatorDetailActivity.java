package com.example.dbman.ui.PowerIndicator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dbman.ui.PowerIndicator.fragment.FragmentT1;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT2;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT3;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentT4;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PowerIndicatorDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Button> btnList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.power_indicator_detail_activity);

        Button buttonT1 = (Button)this.findViewById(R.id.btn_t1);
        Button buttonT2 = (Button)this.findViewById(R.id.btn_t2);
        Button buttonT3 = (Button)this.findViewById(R.id.btn_t3);
        Button buttonT4 = (Button)this.findViewById(R.id.btn_t4);
        buttonT1.setOnClickListener(this);
        buttonT2.setOnClickListener(this);
        buttonT3.setOnClickListener(this);
        buttonT4.setOnClickListener(this);

        btnList.add(buttonT1);
        btnList.add(buttonT2);
        btnList.add(buttonT3);
        btnList.add(buttonT4);

        setBackgroundColorById(R.id.btn_t1);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.btn_t1:
                setBackgroundColorById(R.id.btn_t1);
                ft.replace(R.id.fragment_content, new FragmentT1());
                break;
            case R.id.btn_t2:
                setBackgroundColorById(R.id.btn_t2);
                ft.replace(R.id.fragment_content, new FragmentT2());
                break;
            case R.id.btn_t3:
                setBackgroundColorById(R.id.btn_t3);
                ft.replace(R.id.fragment_content, new FragmentT3());
                break;
            case R.id.btn_t4:
                setBackgroundColorById(R.id.btn_t4);
                ft.replace(R.id.fragment_content, new FragmentT4());
                break;
        }
        ft.commit();
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

//    @Override
//    public String getShortName() {
//        return "性能指标";
//    }
}
