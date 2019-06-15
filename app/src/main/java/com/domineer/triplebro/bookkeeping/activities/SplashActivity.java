package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.managers.DataInitManager;

public class SplashActivity extends Activity implements View.OnClickListener {

    private DataInitManager dataInitManager;
    private TextView tv_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_skip = (TextView) findViewById(R.id.tv_skip);
    }

    private void initData() {
        dataInitManager = new DataInitManager(this);
    }

    private void setOnClickListener() {
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
                break;
        }
    }
}
