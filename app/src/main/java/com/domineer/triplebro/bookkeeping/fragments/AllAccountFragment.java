package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.adapters.AccountAdapter;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.managers.AccountManager;

import java.util.List;

public class AllAccountFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_account;
    private ListView lv_all_account;
    private AccountManager accountManager;
    private List<AccountInfo> accountInfoList;
    private AccountAdapter accountAdapter;
    private SharedPreferences userInfo;
    private int user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_account = View.inflate(getActivity(), R.layout.fragment_account, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_account;
    }

    private void initView() {
        lv_all_account = (ListView) fragment_account.findViewById(R.id.lv_all_account);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("_id", -1);
        if(user_id == -1){
            Toast.makeText(getActivity(), "还没登录呢，不能查看账单信息", Toast.LENGTH_SHORT).show();
            return;
        }
        accountManager = new AccountManager(getActivity());
        accountInfoList = accountManager.getAllAccountInfoList(user_id);
        accountAdapter = new AccountAdapter(getActivity(), accountInfoList,accountManager,1);
        lv_all_account.setAdapter(accountAdapter);
    }

    private void setOnClickListener() {
        lv_all_account.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
