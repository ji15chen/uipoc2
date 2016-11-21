package com.example.dbman.ui.PowerIndicator;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.PowerIndicator.adapter.PowerIndicatorBriefTableAdapter;
import com.example.dbman.ui.core.AbstractBaseActivity;
import com.example.dbman.ui.databinding.PowerindicatorBriefViewFragmentBinding;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.Where;
import info.hoang8f.widget.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.example.dbman.ui.PowerIndicator.PowerIndicatorActivity.REQUEST_QUERY;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PowerIndicatorBriefViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PowerIndicatorBriefViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PowerIndicatorBriefViewFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener{
    private static EquipTypeDao equipTypeDao = (EquipTypeDao) BaseDatabase.getInstance().getDaoImpl("EquipType");
    private String lastQuery="";
    PowerindicatorBriefViewFragmentBinding binding = null;
    private PowerIndicatorBriefTableAdapter adapter ;

    private OnFragmentInteractionListener mListener;

    public PowerIndicatorBriefViewFragment() {

    }

    private boolean querySimpleName(final String name){
        Where<EquipType,UUID> query =null;
        if ((name == null)|| (name.length() <= 0)) return false;

        try{
            query = equipTypeDao.findBySimilarTypeNameQuery(name);
            if (query == null){
                return false;
            }
            List<EquipTypeBriefModel> model = EquipTypeBriefModel.lookupBriefEquipTypeInfo(query);
            adapter.setData(model);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  true;
    }

    // 当点击搜索按钮时触发该方法
    @Override
    public boolean onQueryTextSubmit(String name) {
        return querySimpleName(name);
    }

    // 当搜索内容改变时触发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
            if (!TextUtils.isEmpty(newText)){
                //binding.PowerIndicatorBriefSearchViewList.setFilterText(newText);
            }else{
                //binding.PowerIndicatorBriefSearchViewList.clearTextFilter();
            }
            return false;
    }


    public static PowerIndicatorBriefViewFragment newInstance() {
        PowerIndicatorBriefViewFragment fragment = new PowerIndicatorBriefViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void updateQuery(final String query){
        try {
            List<EquipTypeBriefModel> model = EquipTypeBriefModel.lookupBriefEquipTypeInfo(query);
            adapter.setData(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PowerindicatorBriefViewFragmentBinding.inflate(LayoutInflater.from(getActivity()), container, false);
        adapter = new PowerIndicatorBriefTableAdapter(getActivity(),this);
        binding.table.setAdapter(adapter);
        {
            binding.PowerIndicatorBriefSimlSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    querySimpleName(binding.PowerIndicatorBriefSearchView.getQuery().toString());
                }
            });
        }
        {
            binding.PowerIndicatorBriefAdvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AbstractBaseActivity)getActivity()).startActivityForResult(PowerIndicatorSearchActivity.class, REQUEST_QUERY);
                }
            });
        }
        binding.PowerIndicatorBriefSearchView.setOnQueryTextListener(this);
        try{
            Where<EquipType,UUID> query = equipTypeDao.getLeafEquipByParentUUIDQuery(Constants.NULL_UUID);
            updateQuery(query.getStatement());
        }catch (Exception e){
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        EquipTypeBriefModel model = (EquipTypeBriefModel) v.getTag();
        Intent intent = new Intent(getActivity(), PowerIndicatorDetailActivity.class);
        intent.putExtra("id", model.getId().toString());
        getActivity().startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
