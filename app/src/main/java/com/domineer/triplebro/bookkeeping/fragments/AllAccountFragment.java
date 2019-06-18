package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domineer.triplebro.bookkeeping.R;

/**
 * @author Domineer
 * @data 2019/6/15,13:02
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AllAccountFragment extends Fragment {

    private View fragment_account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_account = View.inflate(getActivity(), R.layout.fragment_account, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_account;
    }

    private void initView() {

    }

    private void initData() {

    }

    private void setOnClickListener() {

    }
}
