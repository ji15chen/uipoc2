package com.example.dbman.ui.PowerIndicator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.example.dbman.ui.PowerIndicator.PowerIndicatorDetailActivity;


public class FragmentBase extends Fragment {
    private View mRoot;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRoot = view;
    }

    @SuppressWarnings("unchecked")
    protected <V extends View> V v(int id) {
        return (V) mRoot.findViewById(id);
    }


    protected PowerIndicatorDetailActivity activity() {
        return (PowerIndicatorDetailActivity) getActivity();
    }

    @SuppressWarnings("unchecked")
    protected <T extends FragmentBase> T fragment(int id) {
        return (T) getActivity().getFragmentManager().findFragmentById(id);
    }

    public void setFragmentShow(FragmentBase fragment) {
        fragment.getActivity().getFragmentManager().beginTransaction().show(fragment).commit();
    }

    public void setFragmentHide(FragmentBase fragment) {
        fragment.getActivity().getFragmentManager().beginTransaction().hide(fragment).commit();
    }
}
