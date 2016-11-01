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
import com.example.dbman.ui.PowerIndicator.AutoAdjustLayoutManager;
import com.example.dbman.ui.PowerIndicator.SyLinearLayoutManager;
import com.example.dbman.ui.PowerIndicator.bean.T1EntryBean;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FragmentT1 extends FragmentBase {
    private String eqUUID ="";
    private RecyclerView entryRecyclerView;
    private T1Adapter t1Adapter;
//    private List<T1EntryBean> data = new ArrayList<>();
//    private final String htmlText = "<h1><mxgsa>昌河武直-10</mxgsa></h1>" +
//            "英文：<i><b>CAIC Z-10</b></i><b>，</b>中方代号：<b>霹雳火 <i>Fiery Thunderbolt</i><sup>[1]</sup><a class=\"sup-anchor\" name=\"ref_[1]_1029834\">&nbsp;</a>\n" +
//            "</b>，亦有直接称之为<b>直-10</b>的叫法）<br>是由<a target=\"_blank\" href=\"/view/669244.htm\">昌河飞机工业（集团）有限责任公司</a><a target=\"_blank\" href=\"/view/3603868.htm\">中国直升机设计研究所</a>（<i>China Helicopter Research and Development Institute CHRDI</i>，又称602所）进行研发，并由<a target=\"_blank\" href=\"/view/1613639.htm\">哈尔滨飞机公司</a>负责设计规划的中国人民解放军新一代专业<a target=\"_blank\" href=\"/subview/115674/5925788.htm\" data-lemmaid=\"10718\">武装直升机</a>。" +
//            "<b>武直-10是中国人民解放军第一种专业武装直升机和亚洲各国第一种自研专业武装直升机。</b>结束了<a target=\"_blank\" href=\"/view/11867790.htm\">中国人民解放军陆军航空兵</a>长期依赖法国<a target=\"_blank\" href=\"/view/1029207.htm\">海豚直升机</a>的改型兼当武装直升机的历史，大大提高了中国人民解放军<a target=\"_blank\" href=\"/view/60632.htm\">陆军航空兵</a>的航空突击与反装甲能力。" +
//            "<div class=\"para\" label-module=\"para\">武直-10配备一座旋转式机炮塔，机体两侧武器短翼可挂载反坦克导弹以及空空导弹，采用串列双座式设计，在设计上符合西方专业<a target=\"_blank\" href=\"/subview/115674/5925788.htm\" data-lemmaid=\"10718\">武装直升机</a>的主要特征。2012年11月12日，直-10在第九届<a target=\"_blank\" href=\"/view/595765.htm\">中国国际航空航天博览会</a>中首次正式曝光。2012年11月18日，中国中央电视台公布的军事新闻中，直-10已经正式加入现役，开始列装<a target=\"_blank\" href=\"/view/11867790.htm\">中国人民解放军陆军航空兵</a>部队。2016年8月6日，完成<a target=\"_blank\" href=\"/view/11867790.htm\">中国人民解放军陆军航空兵</a>全部列装。<sup>[2]</sup><a class=\"sup-anchor\" name=\"ref_[2]_1029834\">&nbsp;</a>\n</div>" +
//            "<div class=\"para\" label-module=\"para\">武直-10采用西方典型武装直升<div class=\"lemma-picture text-pic layout-right\" style=\"width:220px; float: right;\">\n" +
//            "<a class=\"image-link\" nslog-type=\"9317\" href=\"/pic/%E6%AD%A6%E7%9B%B4-10/5543885/0/3b292df5e0fe9925cbb2425a30a85edf8db17179?fr=lemma&amp;ct=single\" target=\"_blank\" title=\"武直-10线图\" style=\"width:220px;height:132px;\">\n" +
//            "<img class=\"\" src=\"http://h.hiphotos.baidu.com/baike/s%3D220/sign=bd037b0208d79123e4e093769d355917/3b292df5e0fe9925cbb2425a30a85edf8db17179.jpg\" alt=\"武直-10线图\" style=\"width:220px;height:132px;\">\n" +
//            "</a>\n" +
//            "<span class=\"description\">\n" +
//            "<h1><mxgsa>设计特点</mxgsa></h1>\n" +
//            "</span>\n" +
//            "</div>机构型，使用一具五叶片主旋翼，螺旋桨形式应为改良全铰接式，旋转方向是异于西方设计的顺时针式，尾旋翼则为类似于<a target=\"_blank\" href=\"/view/1340157.htm\">AH-64武装直升机</a>的四叶片式；武直-10的纵列式座舱设计与<a target=\"_blank\" href=\"/view/1067611.htm\">A129武装直升机</a>类似，前座为驾驶席，后座为炮手，两舱各自独立，使其遭敌火命中时，不容易同时波及两个座舱；据推测，武直-10的座舱视野与A-129相当。武直-10机体与两侧武器短翼之间以翼胴融合方式整合，可增加结构强度与升力。在2012年珠海航展中，中国军方公开宣称武直-10具有防护装甲，机体正面能承受12.7mm机枪子弹射击。<sup>[7]</sup><a class=\"sup-anchor\" name=\"ref_[7]_1029834\">&nbsp;</a>\n" +
//            "<div class=\"lemma-picture text-pic layout-center\" style=\"width:500px; float: none; display: block; margin: 0px auto; clear: both;\">\n" +
//            "<a class=\"image-link\" nslog-type=\"9317\" href=\"/pic/%E6%AD%A6%E7%9B%B4-10/5543885/0/0dd7912397dda1441877e9bdb5b7d0a20cf48647?fr=lemma&amp;ct=single\" target=\"_blank\" title=\"\" style=\"width:500px;height:167px;\">\n" +
//            "<img class=\"\" src=\"http://f.hiphotos.baidu.com/baike/s%3D500/sign=2511281d750e0cf3a4f74efb3a46f23d/0dd7912397dda1441877e9bdb5b7d0a20cf48647.jpg\" alt=\"\" style=\"width:500px;height:167px;\">\n" +
//            "</a>\n" +
//            "</div></div>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        data.add(new T1EntryBean("参数",htmlText));
//        data.add(new T1EntryBean("注意事项",htmlText));
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
