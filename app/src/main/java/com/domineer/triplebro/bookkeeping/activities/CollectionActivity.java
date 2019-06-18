package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.adapters.CollectionAdapter;
import com.domineer.triplebro.bookkeeping.beans.CollectionInfo;
import com.domineer.triplebro.bookkeeping.managers.CollectionManager;

import java.util.List;

public class CollectionActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ImageView iv_close_collection;
    private CollectionManager collectionManager;
    private List<CollectionInfo> collectionInfoList;
    private ListView lv_collection;
    private CollectionAdapter collectionAdapter;

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
        collectionAdapter = new CollectionAdapter(this, collectionInfoList);
        lv_collection.setAdapter(collectionAdapter);
    }

    private void initData() {
        collectionManager = new CollectionManager(this);
        collectionInfoList = collectionManager.getCollectionInfoList();

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
