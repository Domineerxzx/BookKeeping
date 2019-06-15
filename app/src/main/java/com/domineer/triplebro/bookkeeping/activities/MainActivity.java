package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.os.Bundle;

import com.domineer.triplebro.bookkeeping.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {

    }

    private void initData() {

    }

    private void setOnClickListener() {

    }
}
