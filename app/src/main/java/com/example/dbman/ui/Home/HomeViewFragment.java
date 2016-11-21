package com.example.dbman.ui.Home;

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
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.model.EquipTypeBriefModel;
import com.example.dbman.ui.PowerIndicator.PowerIndicatorSearchActivity;
import com.example.dbman.ui.PowerIndicator.adapter.PowerIndicatorBriefTableAdapter;
import com.example.dbman.ui.R;
import com.example.dbman.ui.core.AbstractBaseActivity;
import com.example.dbman.ui.core.AbstractUIStateBindingActivityWithNavMenu;
import com.example.dbman.ui.databinding.HomeFragmentBinding;
import com.example.dbman.ui.databinding.PowerindicatorBriefViewFragmentBinding;
import com.j256.ormlite.stmt.Where;

import java.util.List;
import java.util.UUID;

import static com.example.dbman.ui.PowerIndicator.PowerIndicatorActivity.REQUEST_QUERY;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeViewFragment extends Fragment implements View.OnClickListener{
    HomeFragmentBinding binding = null;

//    private OnFragmentInteractionListener mListener;

    public HomeViewFragment() {

    }

    public static HomeViewFragment newInstance() {
        HomeViewFragment fragment = new HomeViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(LayoutInflater.from(getActivity()), container, false);
        binding.homeFun1.setOnClickListener(this);
        binding.homeFun2.setOnClickListener(this);
        binding.homeFun3.setOnClickListener(this);
        binding.homeFun4.setOnClickListener(this);
        binding.homeFun5.setOnClickListener(this);
        binding.homeFun6.setOnClickListener(this);
        return binding.getRoot();
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void onClick(View v) {
        AbstractBaseActivity act = (AbstractBaseActivity)getActivity();
        int id = 0;
        switch (v.getId())
        {
            case R.id.home_fun_1:
                id =1;
                break;
            case R.id.home_fun_2:
                id = 2;
                break;
            case R.id.home_fun_3:
                id = 3;
                break;
            case R.id.home_fun_4:
                id = 4;
                break;
            case R.id.home_fun_5:
                id = 5;
                break;
            case R.id.home_fun_6:
                id = 6;
                break;
            default:
                return;
        }
        act.startActivity(AbstractUIStateBindingActivityWithNavMenu.mItemClasses[id]);
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
