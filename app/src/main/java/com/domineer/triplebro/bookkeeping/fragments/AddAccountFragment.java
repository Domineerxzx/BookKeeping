package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.adapters.AccountTypeAdapter;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.interfaces.OnItemClickListener;
import com.domineer.triplebro.bookkeeping.managers.AddAccountManager;

import java.util.List;

public class AddAccountFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    private View fragment_add_account;
    private EditText et_account_number;
    private RecyclerView rv_account_type;
    private TextView tv_submit;
    private AddAccountManager addAccountManager;
    private List<AccountTypeInfo> accountTypeInfoList;
    private AccountTypeAdapter accountTypeAdapter;
    private TextView tv_tip;
    private AccountTypeInfo accountTypeInfo;
    private SharedPreferences userInfo;
    private int user_id;
    private EditText et_ps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_add_account = View.inflate(getActivity(), R.layout.fragment_add_account, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_add_account;
    }

    private void initView() {
        et_account_number = (EditText) fragment_add_account.findViewById(R.id.et_account_number);
        rv_account_type = (RecyclerView) fragment_add_account.findViewById(R.id.rv_account_type);
        tv_submit = (TextView) fragment_add_account.findViewById(R.id.tv_submit);
        tv_tip = (TextView) fragment_add_account.findViewById(R.id.tv_tip);
        et_ps = (EditText) fragment_add_account.findViewById(R.id.et_ps);
        rv_account_type.setLayoutManager(new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void initData() {
        addAccountManager = new AddAccountManager(getActivity());
        accountTypeInfoList = addAccountManager.getAccountTypeInfoList();
        accountTypeAdapter = new AccountTypeAdapter(getActivity(), accountTypeInfoList);
        if (accountTypeInfoList.size() > 0) {
            tv_tip.setVisibility(View.GONE);
            rv_account_type.setVisibility(View.VISIBLE);
            rv_account_type.setAdapter(accountTypeAdapter);
        } else {
            tv_tip.setVisibility(View.VISIBLE);
            rv_account_type.setVisibility(View.GONE);
        }
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("_id", -1);
    }

    private void setOnClickListener() {
        tv_submit.setOnClickListener(this);
        accountTypeAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                String account_number = et_account_number.getText().toString().trim();
                String ps = et_ps.getText().toString().trim();
                if (user_id == -1) {
                    Toast.makeText(getActivity(), "尚未登录，请登录后再来添加账单信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(account_number)) {
                    Toast.makeText(getActivity(), "金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ps)) {
                    Toast.makeText(getActivity(), "备注不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (accountTypeInfo == null) {
                    Toast.makeText(getActivity(), "请选择一个账单类别", Toast.LENGTH_SHORT).show();
                    return;
                }
                addAccountManager.addAccount(user_id,ps,account_number,accountTypeInfo);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == -1) {
            accountTypeInfo = null;
        } else {
            accountTypeInfo = accountTypeInfoList.get(position);
        }
    }

    @Override
    public void onItemLongClick(View view) {

    }
}
