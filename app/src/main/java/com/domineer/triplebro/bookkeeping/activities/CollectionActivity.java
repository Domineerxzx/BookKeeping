package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.adapters.AccountAdapter;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.managers.AccountManager;

import java.util.List;

public class CollectionActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ImageView iv_close_collection;
    private AccountManager accountManager;
    private List<AccountInfo> collectionInfoList;
    private ListView lv_collection;
    private AccountAdapter collectionAdapter;
    private SharedPreferences userInfo;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_collection = (ImageView) findViewById(R.id.iv_close_collection);
        lv_collection = (ListView) findViewById(R.id.lv_collection);
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("_id", -1);
        if(user_id == -1){
            Toast.makeText(this, "还没登录呢，不能查看统计信息", Toast.LENGTH_SHORT).show();
            return;
        }
        accountManager = new AccountManager(this);
        collectionInfoList = accountManager.getCollectionAccountList(user_id);
        collectionAdapter = new AccountAdapter(this, collectionInfoList,accountManager,2);
        lv_collection.setAdapter(collectionAdapter);

    }

    private void setOnClickListener() {
        iv_close_collection.setOnClickListener(this);
        lv_collection.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_collection:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
