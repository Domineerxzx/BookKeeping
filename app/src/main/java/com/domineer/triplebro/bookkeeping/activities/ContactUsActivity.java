package com.domineer.triplebro.bookkeeping.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.utils.PermissionUtil;
import com.domineer.triplebro.bookkeeping.views.TwoButtonDialog;

public class ContactUsActivity extends Activity implements View.OnClickListener {

    private TextView tv_people_telephone;
    private ImageView iv_close_contact_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_people_telephone = (TextView) findViewById(R.id.tv_people_telephone);
        iv_close_contact_us = (ImageView) findViewById(R.id.iv_close_contact_us);
    }

    private void initData() {

    }

    private void setOnClickListener() {
        tv_people_telephone.setOnClickListener(this);
        iv_close_contact_us.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_people_telephone:
                PermissionUtil.requestPower(this, this, Manifest.permission.CALL_PHONE);
                TwoButtonDialog contact_us = new TwoButtonDialog();
                String title = "联系我们";
                String message = "拨打电话：18840919546";
                final String telephone = "18840919546";
                contact_us.show(title, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ContextCompat.checkSelfPermission(ContactUsActivity.this,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(ContactUsActivity.this, "未授权拨打电话,请设置开启权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + telephone));
                            startActivity(intent);
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ContactUsActivity.this, "取消呼叫", Toast.LENGTH_SHORT).show();
                    }
                }, getFragmentManager());
                break;
            case R.id.iv_close_contact_us:
                finish();
                break;
        }
    }
}
