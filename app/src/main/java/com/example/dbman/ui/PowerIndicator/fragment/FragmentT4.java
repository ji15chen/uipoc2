package com.example.dbman.ui.PowerIndicator.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.db.model.CpntDesc;
import com.example.dbman.db.model.EquipTypeCpntSetModel;
import com.example.dbman.ui.PowerIndicator.adapter.PowerIndicatorDetailCpntAdapter;
import com.example.dbman.ui.R;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import java.util.List;
import java.util.UUID;


public class FragmentT4 extends FragmentBase {
    private String eqUUID="";
    PowerIndicatorDetailCpntAdapter cpntAdapter;
    TableFixHeaders tableFixHeaders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void refreshData(String eqUUID){
        if (this.eqUUID.equals(eqUUID)) return;
        new LoadDataTask().execute(eqUUID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t4, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.table);
        cpntAdapter = new PowerIndicatorDetailCpntAdapter(getActivity());
        tableFixHeaders.setAdapter(cpntAdapter);
    }

    private  class LoadDataTask extends AsyncTask<String, Void, List<CpntDesc>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected List<CpntDesc> doInBackground(String... uuids) {
            String uuid = uuids[0];
            List<CpntDesc> cpntInfos = null;

            try {
                EquipTypeCpntSetModel model = EquipTypeCpntSetModel.loadEquipCpntSet(UUID.fromString(uuid), false);
                cpntInfos = model.getLstEquipCpnts();
            }catch (Exception e) {
                cpntInfos= null;
            }

            return cpntInfos;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(List<CpntDesc> result) {
            if (result != null) {
                cpntAdapter.setData(result);
            }
        }
    }
}
