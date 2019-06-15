package com.domineer.triplebro.bookkeeping.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.managers.LoginManager;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private Button bt_create;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_nickname);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_create = (Button) findViewById(R.id.bt_create);
    }

    private void initData() {
        loginManager = new LoginManager(this);
    }

    private void setOnClickListener() {
        bt_login.setOnClickListener(this);
        bt_create.setOnClickListener(this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(this, "用户名或密码不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    loginManager.login(username, password);
                    finish();
                    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                } catch (LoginOrRegisterException e) {
                    Toast.makeText(LoginActivity.this,"出错了,错误原因："+e.getErrorMassage(),Toast.LENGTH_SHORT).show();
                    Log.e("error","----------------登录出错，错误代码："+e.getErrorCode()+"-----------错误原因："+
                            e.getErrorMassage()+"-------------");
                }
                break;
            case R.id.bt_create:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                finish();
                break;
        }
    }
}
