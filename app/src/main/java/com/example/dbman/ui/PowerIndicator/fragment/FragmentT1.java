package com.example.dbman.ui.PowerIndicator.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dbman.core.Utils;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
import com.example.dbman.db.model.EquipTypeParamSetModel;
import com.example.dbman.ui.PowerIndicator.bean.T1EntryBean;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FragmentT1 extends FragmentBase {
    private String eqUUID ="";
    private RecyclerView entryRecyclerView;
    private T1Adapter t1Adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t1, container, false);
    }

    public void refreshData(String eqUUID){
        if (this.eqUUID.equals(eqUUID)) return;
        new LoadDataTask().execute(eqUUID);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        entryRecyclerView = (RecyclerView) view.findViewById(R.id.entryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        entryRecyclerView.setLayoutManager(linearLayoutManager);
        //entryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        entryRecyclerView.setHasFixedSize(false);
        t1Adapter = new T1Adapter(getActivity());
        entryRecyclerView.setAdapter(t1Adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    class T1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTitle;
        TextView mTextViewBody;

        T1ViewHolder(View v) {
            super(v);
            mTextViewTitle = (TextView) v.findViewById(R.id.detail_entry_title);
            mTextViewBody = (TextView) v.findViewById(R.id.detail_entry_body);
        }
    }

    class T1Adapter extends RecyclerView.Adapter<T1ViewHolder> {
        private List<T1EntryBean> detailEntryBeen = new ArrayList<>();

        private Context mContext;

        T1Adapter(Context context) {
            this.mContext = context;
        }
        T1Adapter(Context context, List<T1EntryBean> detailEntryBeen) {
            this.mContext = context;
            this.detailEntryBeen = detailEntryBeen;
        }
        public void refresh(List<T1EntryBean> detailEntryBeen){
            this.detailEntryBeen = detailEntryBeen;
            this.notifyDataSetChanged();
        }
        @Override
        public T1ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.power_indicator_detail_entry, viewGroup, false);
            return new T1ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(T1ViewHolder viewHolder, int i) {
            T1EntryBean p = detailEntryBeen.get(i);
            viewHolder.mTextViewTitle.setText(p.getTitle());
            viewHolder.mTextViewBody.setMovementMethod(ScrollingMovementMethod.getInstance());
            Spanned htmlAsSpanned = Utils.fromHtml(p.getBody());
            viewHolder.mTextViewBody.setText(htmlAsSpanned);
        }

        @Override
        public int getItemCount() {
            // 返回数据总数
            return detailEntryBeen == null ? 0 : detailEntryBeen.size();
        }
    }

    private  class LoadDataTask extends AsyncTask<String, Void, List<T1EntryBean>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected List<T1EntryBean> doInBackground(String... uuids) {
            String uuid = uuids[0];
            List<T1EntryBean> entryBeanList = new ArrayList<>();

            try {
                    //[1] 参数
                        StringBuffer basicParamHtml = new StringBuffer();
                        EquipTypeParamSetModel model = EquipTypeParamSetModel.loadEquipTypeParamSetView(UUID.fromString(uuid));
                        List<EquipTypeParamSetModel.EquipTypeParam> equipTypeParams = model.getLstEquipTypeParam();
                        for (EquipTypeParamSetModel.EquipTypeParam equipTypeParam:equipTypeParams){
                            basicParamHtml.append("<li>");
                            basicParamHtml.append("<b>");
                            basicParamHtml.append(equipTypeParam.getParaName());
                            basicParamHtml.append(": ");
                            basicParamHtml.append("</b>");
                            basicParamHtml.append(equipTypeParam.getParaValue());
                            basicParamHtml.append(" ");
                            basicParamHtml.append(equipTypeParam.getParaUnit());
                            basicParamHtml.append("<br>");
                            basicParamHtml.append("</li>");
                            basicParamHtml.append("\n");
                        }
                        if (basicParamHtml.length() > 0) {
                            T1EntryBean basicParamBean = new T1EntryBean("特征参数", basicParamHtml.toString());
                            entryBeanList.add(basicParamBean);
                        }
                        //[2] 其他参数
                        List<EquipTypeDetail> equipTypeDetails = model.getLstEquipExtParam();
                        for (EquipTypeDetail etd:equipTypeDetails){
                            T1EntryBean basicParamBean = new T1EntryBean(etd.getItemName(), etd.getItemBody());
                            entryBeanList.add(basicParamBean);
                        }
            }catch (Exception e){
                entryBeanList.clear();
                e.printStackTrace();
                throw  new RuntimeException("unable to get data from "+ uuid);
            }
            return entryBeanList;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(List<T1EntryBean> result) {
            if (result != null) {
                FragmentT1.this.t1Adapter.refresh(result);
            }
        }
    }
}
