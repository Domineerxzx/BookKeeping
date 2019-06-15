package com.domineer.triplebro.bookkeeping.managers;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.domineer.triplebro.bookkeeping.activities.RegisterActivity;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;

import java.io.File;

/**
 * @author Domineer
 * @data 2019/6/16,5:16
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class RegisterManager {

    private Context context;
    private DataBaseOP dataBaseOP;
    private int userId;
    private SharedPreferences userInfoSharedPreferences;
    private SharedPreferences.Editor edit;

    public RegisterManager(Context context) {
        this.context = context;
    }


    public void register(String username, String password, String nickname, File userHeadFile) throws LoginOrRegisterException {
        dataBaseOP = new DataBaseOP(context);
        userId = dataBaseOP.registerUser(username, nickname, password, userHeadFile);
        userInfoSharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        edit = userInfoSharedPreferences.edit();
        edit.putInt("_id", userId);
        edit.putString("username",username);
        edit.putString("password",password);
        edit.putString("nickname",nickname);
        edit.putString("userHead",userHeadFile.getAbsolutePath());
        edit.apply();
    }
}
