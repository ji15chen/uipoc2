package com.example.dbman.ui.PowerIndicator.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.dbman.db.model.CpntDesc;
import com.example.dbman.db.model.EquipTypeCpntSetModel;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FragmentT2 extends FragmentBase {
    private String eqUUID ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView mListView = (ListView) view.findViewById(R.id.listView1);
        mListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, getData()));

    }

    public void refreshData(String eqUUID){
        if (this.eqUUID.equals(eqUUID)) return;
        new LoadDataTask().execute(eqUUID);
    }
    private List<String> getData(){

        List<String> data = new ArrayList<>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }


    private  class LoadDataTask extends AsyncTask<String, Void, List<CpntDesc>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected List<CpntDesc> doInBackground(String... uuids) {
            String uuid = uuids[0];
            List<CpntDesc> cpntInfos = null;

            //TODO:implement here

            return cpntInfos;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(List<CpntDesc> result) {
            if (result != null) {
                //cpntAdapter.setData(result);
            }
        }
    }
}
