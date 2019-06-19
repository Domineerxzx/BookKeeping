package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.adapters.AccountTypeAdapter;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.interfaces.OnItemClickListener;
import com.domineer.triplebro.bookkeeping.managers.AddAccountManager;

import java.io.Serializable;
import java.util.List;

public class AccountActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private EditText et_account_number;
    private EditText et_ps;
    private RecyclerView rv_account_type;
    private TextView tv_change;
    private TextView tv_delete;
    private AccountInfo accountInfo;
    private AddAccountManager addAccountManager;
    private List<AccountTypeInfo> accountTypeInfoList;
    private AccountTypeAdapter accountTypeAdapter;
    private TextView tv_tip;
    private SharedPreferences userInfo;
    private int user_id;
    private AccountTypeInfo accountTypeInfo;
    private ImageView iv_close_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        tv_change.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        accountTypeAdapter.setOnItemClickListener(this);
        iv_close_account.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        accountInfo = (AccountInfo)intent.getSerializableExtra("accountInfo");
        et_account_number.setText(accountInfo.getAccountMoney());
        et_ps.setText(accountInfo.getAccountContent());
        addAccountManager = new AddAccountManager(this);
        accountTypeInfoList = addAccountManager.getAccountTypeInfoList();
        accountTypeAdapter = new AccountTypeAdapter(this, accountTypeInfoList,accountInfo.getAccountTypeId());
        if (accountTypeInfoList.size() > 0) {
            tv_tip.setVisibility(View.GONE);
            rv_account_type.setVisibility(View.VISIBLE);
            rv_account_type.setAdapter(accountTypeAdapter);
        } else {
            tv_tip.setVisibility(View.VISIBLE);
            rv_account_type.setVisibility(View.GONE);
        }
        userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("_id", -1);
    }

    private void initView() {
        et_account_number = (EditText) findViewById(R.id.et_account_number);
        et_ps = (EditText) findViewById(R.id.et_ps);
        rv_account_type = (RecyclerView) findViewById(R.id.rv_account_type);
        tv_change = (TextView) findViewById(R.id.tv_change);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        rv_account_type.setLayoutManager(new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        iv_close_account = (ImageView) findViewById(R.id.iv_close_account);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_change:
                String account_number = et_account_number.getText().toString().trim();
                String ps = et_ps.getText().toString().trim();
                accountInfo.setAccountMoney(account_number);
                if(accountTypeInfo != null){
                    accountInfo.setAccountTypeId(accountTypeInfo.get_id());
                    accountInfo.setAccountName(accountTypeInfo.getAccountTypeName());
                }
                accountInfo.setAccountContent(ps);
                addAccountManager.changeAccountInfo(accountInfo);
                break;
            case R.id.tv_delete:
                addAccountManager.deleteAccountInfo(accountInfo.get_id());
                break;
            case R.id.iv_close_account:
                finish();
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
