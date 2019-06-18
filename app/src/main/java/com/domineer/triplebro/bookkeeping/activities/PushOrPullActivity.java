package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;

public class PushOrPullActivity extends Activity implements View.OnClickListener {

    private TextView tv_yun_version_number;
    private TextView tv_local_version_number;
    private TextView tv_push;
    private TextView tv_pull;
    private ImageView iv_close_push_or_pull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_or_pull);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_yun_version_number = (TextView) findViewById(R.id.tv_yun_version_number);
        tv_local_version_number = (TextView) findViewById(R.id.tv_local_version_number);
        tv_push = (TextView) findViewById(R.id.tv_push);
        tv_pull = (TextView) findViewById(R.id.tv_pull);
        iv_close_push_or_pull = (ImageView) findViewById(R.id.iv_close_push_or_pull);
    }

    private void initData() {

    }

    private void setOnClickListener() {
        tv_push.setOnClickListener(this);
        tv_pull.setOnClickListener(this);
        iv_close_push_or_pull.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_push:
                break;
            case R.id.tv_pull:
                break;
            case R.id.iv_close_push_or_pull:
                finish();
                break;
        }
    }
}
