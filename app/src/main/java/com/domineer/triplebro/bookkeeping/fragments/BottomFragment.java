package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;

/**
 * @author Domineer
 * @data 2019/6/15,13:02
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_add_account;
    private LinearLayout ll_account;
    private LinearLayout ll_statistics;
    private LinearLayout ll_myself;
    private TextView tv_add_account;
    private TextView tv_account;
    private TextView tv_statistics;
    private TextView tv_myself;
    private Button bt_add_account;
    private Button bt_account;
    private Button bt_statistics;
    private Button bt_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = View.inflate(getActivity(), R.layout.fragment_bottom, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_bottom;
    }

    private void initView() {
        ll_add_account = (LinearLayout) fragment_bottom.findViewById(R.id.ll_add_account);
        ll_account = (LinearLayout) fragment_bottom.findViewById(R.id.ll_account);
        ll_statistics = (LinearLayout) fragment_bottom.findViewById(R.id.ll_statistics);
        ll_myself = (LinearLayout) fragment_bottom.findViewById(R.id.ll_myself);
        tv_add_account = (TextView) fragment_bottom.findViewById(R.id.tv_add_account);
        tv_account = (TextView) fragment_bottom.findViewById(R.id.tv_account);
        tv_statistics = (TextView) fragment_bottom.findViewById(R.id.tv_statistics);
        tv_myself = (TextView) fragment_bottom.findViewById(R.id.tv_myself);
        bt_add_account = (Button) fragment_bottom.findViewById(R.id.bt_add_account);
        bt_account = (Button) fragment_bottom.findViewById(R.id.bt_account);
        bt_statistics = (Button) fragment_bottom.findViewById(R.id.bt_statistics);
        bt_myself = (Button) fragment_bottom.findViewById(R.id.bt_myself);
    }

    private void initData() {
        fragmentManager = getActivity().getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void setOnClickListener() {
        ll_add_account.setOnClickListener(this);
        ll_account.setOnClickListener(this);
        ll_statistics.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        tv_add_account.setOnClickListener(this);
        tv_account.setOnClickListener(this);
        tv_statistics.setOnClickListener(this);
        tv_myself.setOnClickListener(this);
        bt_add_account.setOnClickListener(this);
        bt_account.setOnClickListener(this);
        bt_statistics.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_add_account;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_add_account;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_account:
            case R.id.bt_add_account:
            case R.id.tv_add_account:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new AddAccountFragment());
                fragmentTransaction.commit();
                refresh(lastFunctionButton, bt_add_account);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_add_account.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_add_account;
                break;
            case R.id.ll_account:
            case R.id.bt_account:
            case R.id.tv_account:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new AllAccountFragment());
                fragmentTransaction.commit();
                refresh(lastFunctionButton, bt_account);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_account.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_account;
                break;
            case R.id.ll_statistics:
            case R.id.bt_statistics:
            case R.id.tv_statistics:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new StatisticsFragment());
                fragmentTransaction.commit();
                refresh(lastFunctionButton, bt_statistics);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_statistics.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_statistics;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new MyselfFragment());
                fragmentTransaction.commit();
                refresh(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void refresh(Button lastFunctionButton, Button onClickButton) {
        if (lastFunctionButton == onClickButton) {
            return;
        }
        switch (lastFunctionButton.getId()) {
            case R.id.bt_add_account:
                lastFunctionButton.setBackgroundResource(R.mipmap.add_account_unclick);
                break;
            case R.id.bt_account:
                lastFunctionButton.setBackgroundResource(R.mipmap.account_unclick);
                break;
            case R.id.bt_statistics:
                lastFunctionButton.setBackgroundResource(R.mipmap.statistics_unclick);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.myself_unclick);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_add_account:
                onClickButton.setBackgroundResource(R.mipmap.add_account);
                break;
            case R.id.bt_account:
                onClickButton.setBackgroundResource(R.mipmap.account);
                break;
            case R.id.bt_statistics:
                onClickButton.setBackgroundResource(R.mipmap.statistics);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.myself);
                break;
        }
        this.lastFunctionButton = onClickButton;
    }
}
