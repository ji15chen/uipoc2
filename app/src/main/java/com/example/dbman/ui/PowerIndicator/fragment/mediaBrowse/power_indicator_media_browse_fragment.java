package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbman.ui.PowerIndicator.PowerIndicatorDetailActivity;
import com.example.dbman.ui.PowerIndicator.bean.ActorBean;
import com.example.dbman.ui.PowerIndicator.fragment.FragmentBase;
import com.example.dbman.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Use the {@link power_indicator_media_browse_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class power_indicator_media_browse_fragment extends FragmentBase {
    private static final String ARG_PARAM1="param1";

    private String mUUID;
    private RecyclerView mPhotoList;
    private MyAdapter myAdapter;
    private List<ActorBean> actors = new ArrayList<>();
    private String[] names = { "朱茵", "张柏芝", "张敏", "如花" };
    private String[] pics = { "test_001", "test_002", "test_003", "test_004"};

    public power_indicator_media_browse_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param uuid Parameter 1.
     * @return A new instance of fragment power_indicator_media_browse_fragment.
     */
    public static power_indicator_media_browse_fragment newInstance(String uuid) {
        power_indicator_media_browse_fragment fragment = new power_indicator_media_browse_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, uuid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUUID = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_power_indicator_media_browse_fragment, container, false);
        mPhotoList = (RecyclerView) root.findViewById(R.id.photoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPhotoList.setLayoutManager(linearLayoutManager);
        mPhotoList.setItemAnimator(new DefaultItemAnimator());
        mPhotoList.setHasFixedSize(true);
        myAdapter = new MyAdapter(getActivity(), actors);
        mPhotoList.setAdapter(myAdapter);


        final int count = names.length;
        for (int i = 0; i < count; i++) {
            String name = names[i];
            String pic = pics[i];

            actors.add(new ActorBean(name, pic));
            mPhotoList.scrollToPosition(myAdapter.getItemCount() - 1);
            myAdapter.notifyDataSetChanged();

        }
        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
