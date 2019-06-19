package com.domineer.triplebro.bookkeeping.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.domineer.triplebro.bookkeeping.activities.LoginActivity;
import com.domineer.triplebro.bookkeeping.beans.UserInfo;
import com.domineer.triplebro.bookkeeping.database.MyOpenHelper;
import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

public class LoginManager {

    private Context context;
    private ISource dataBaseOP;
    private SharedPreferences userInfoSharedPreferences;
    private SharedPreferences.Editor edit;

    public LoginManager(Context context) {
        this.context = context;
    }

    public void login(String username, String password) throws LoginOrRegisterException {

        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        UserInfo userInfo = dataBaseOP.checkUser(username,password);
        userInfoSharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        edit = userInfoSharedPreferences.edit();
        edit.putInt("_id",userInfo.get_id());
        edit.putString("username",userInfo.getTelephone());
        edit.putString("password",userInfo.getPassword());
        edit.putString("nickname",userInfo.getNickname());
        edit.putString("userHead",userInfo.getUserHead());
        edit.apply();
    }
}
