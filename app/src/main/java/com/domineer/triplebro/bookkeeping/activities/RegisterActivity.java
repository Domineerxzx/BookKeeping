package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.database.MyOpenHelper;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.managers.RegisterManager;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;
import com.domineer.triplebro.bookkeeping.utils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.bookkeeping.utils.RealPathFromUriUtils;

import java.io.File;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private ImageView iv_user_head;
    private EditText et_nickname;
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_agree;
    private Button bt_create;
    private Button bt_login;
    private RegisterManager registerManager;
    private String username;
    private String nickname;
    private String password;
    private SharedPreferences userInfo;
    private File userHeadFile;
    private ImageView iv_close_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_create = (ImageView) findViewById(R.id.iv_close_create);
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_agree = (CheckBox) findViewById(R.id.cb_agree);
        bt_create = (Button) findViewById(R.id.bt_create);
        bt_login = (Button) findViewById(R.id.bt_login);
    }

    private void initData() {
        registerManager = new RegisterManager(this);
    }

    private void setOnClickListener() {
        iv_user_head.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_create.setOnClickListener(this);
        iv_close_create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_user_head:
                username = et_username.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(this, "请先填写用户名", Toast.LENGTH_SHORT).show();
                }else{
                    long timeStamp = System.currentTimeMillis();
                    userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                    SharedPreferences.Editor edit = userInfo.edit();
                    edit.putLong("timeStamp", timeStamp);
                    edit.commit();
                    ChooseUserHeadDialogUtil.showDialog(this, username, timeStamp);
                }
                break;
            case R.id.bt_create:
                username = et_username.getText().toString().trim();
                nickname = et_nickname.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(nickname) || TextUtils.isEmpty(password)){
                    Toast.makeText(this, "信息填写不全", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.length() != 11){
                    Toast.makeText(this, "用户名不是手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!cb_agree.isChecked()){
                    Toast.makeText(this, "请阅读并同意遵守我们的条款与条件、隐私政策、以及个人敏感信息政策", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userHeadFile == null){
                    Toast.makeText(this, "用户头像还没设定呢", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    registerManager.register(username,password,nickname,userHeadFile);
                    finish();
                    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                } catch (LoginOrRegisterException e) {
                    Toast.makeText(this,"出错了,错误原因："+e.getErrorMassage(),Toast.LENGTH_SHORT).show();
                    Log.e("error","----------------注册出错，错误代码："+e.getErrorCode()+"-----------错误原因："+
                            e.getErrorMassage()+"-------------");
                }
                break;
            case R.id.bt_login:
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                finish();
                break;
            case R.id.iv_close_create:
                finish();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        SharedPreferences.Editor edit = userInfo.edit();
        ContentValues contentValues = new ContentValues();
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    userHeadFile = new File(RealPathFromUriUtils.getRealPathFromUri(this, data.getData()));
                    Glide.with(this).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    long timeStamp = userInfo.getLong("timeStamp", -1);
                    userHeadFile = new File(getFilesDir() + File.separator + "images" + File.separator + username + timeStamp + ".jpg");
                    Glide.with(this).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            edit.commit();
        } else {
            Toast.makeText(this, "取消修改", Toast.LENGTH_SHORT).show();
        }
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
