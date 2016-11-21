package com.example.dbman.ui.StoreDetail;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.dbman.core.BaseDatabase;
import com.example.dbman.core.Constants;
import com.example.dbman.db.genupdate.dao.EquipTypeDao;
import com.example.dbman.db.genupdate.dao.StoreDetailDao;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.StoreDetail.adapter.StoreDetailTableAdapter;
import com.example.dbman.ui.core.AbstractBaseActivity;
import com.example.dbman.ui.databinding.PowerindicatorBriefViewFragmentBinding;
import com.example.dbman.ui.databinding.StoreDetailViewFragmentBinding;
import com.j256.ormlite.stmt.Where;

import java.util.List;
import java.util.UUID;

import static com.example.dbman.ui.PowerIndicator.PowerIndicatorActivity.REQUEST_QUERY;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreDetailViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreDetailViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreDetailViewFragment extends Fragment {
    private static StoreDetailDao equipTypeDao = (StoreDetailDao) BaseDatabase.getInstance().getDaoImpl("StoreDetail");
    private String lastQuery="";
    StoreDetailViewFragmentBinding binding = null;
    private StoreDetailTableAdapter adapter ;

    private OnFragmentInteractionListener mListener;

    public StoreDetailViewFragment() {

    }

    public static StoreDetailViewFragment newInstance() {
        StoreDetailViewFragment fragment = new StoreDetailViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new StoreDetailTableAdapter(getActivity());

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
        binding = StoreDetailViewFragmentBinding.inflate(LayoutInflater.from(getActivity()), container, false);
        adapter = new StoreDetailTableAdapter(getActivity());
        binding.table.setAdapter(adapter);
        {
            binding.StoreDetailViewSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AbstractBaseActivity)getActivity()).startActivityForResult(StoreDetailSearchActivity.class, REQUEST_QUERY);
                }
            });
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
