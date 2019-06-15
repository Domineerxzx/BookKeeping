package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.utils.FileUtils;
import com.domineer.triplebro.bookkeeping.views.TwoButtonDialog;

import java.io.File;

public class SettingActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_setting;
    private TextView tv_clean_cache;
    private TextView tv_cancellation;
    private SharedPreferences userInfo;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        setOnClickListener();
    }

    private void initView() {
        iv_close_setting = (ImageView) findViewById(R.id.iv_close_setting);
        tv_clean_cache = (TextView) findViewById(R.id.tv_clean_cache);
        tv_cancellation = (TextView) findViewById(R.id.tv_cancellation);
    }

    private void setOnClickListener() {
        iv_close_setting.setOnClickListener(this);
        tv_clean_cache.setOnClickListener(this);
        tv_cancellation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_setting:
                finish();
                break;
            case R.id.tv_clean_cache:
                TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
                twoButtonDialog.show("清理缓存", "是否要清除软件缓存？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FileUtils.deleteFile(getCacheDir());
                        Toast.makeText(SettingActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                },getFragmentManager());
                break;
            case R.id.tv_cancellation:
                userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                edit = userInfo.edit();
                edit.clear();
                edit.apply();
                finish();
                break;
        }
    }
}
