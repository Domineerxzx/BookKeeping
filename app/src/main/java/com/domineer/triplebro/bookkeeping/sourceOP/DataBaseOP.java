package com.domineer.triplebro.bookkeeping.sourceOP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.beans.UserInfo;
import com.domineer.triplebro.bookkeeping.database.MyOpenHelper;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/6/16,4:08
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseOP {

    private Context context;
    private final MyOpenHelper myOpenHelper;
    private final SQLiteDatabase db;

    public DataBaseOP(Context context) {
        this.context = context;
        myOpenHelper = new MyOpenHelper(context);
        db = myOpenHelper.getWritableDatabase();
    }

    public UserInfo checkUser(String username, String password) throws LoginOrRegisterException {

        Cursor userInfoCursor = db.query("userInfo", null, "telephone = ?", new String[]{username}, null, null, null);
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            if (userInfoCursor.getString(2).equals(password)) {
                userInfoCursor.moveToNext();
                UserInfo userInfo = new UserInfo();
                userInfo.set_id(userInfoCursor.getInt(0));
                userInfo.setTelephone(userInfoCursor.getString(1));
                userInfo.setPassword(userInfoCursor.getString(2));
                userInfo.setNickname(userInfoCursor.getString(3));
                userInfo.setUserHead(userInfoCursor.getString(4));
                return userInfo;
            } else {
                throw new LoginOrRegisterException(ProjectProperties.ERROR_CODE_PASSWORD_ERROR, "密码错误，请检查后进行登录！！！");
            }
        } else {
            throw new LoginOrRegisterException(ProjectProperties.ERROR_CODE_USER_NO_EXISTED, "该用户不存在，请注册后进行登录！！！");
        }
    }

    public int registerUser(String username, String nickname, String password, File userHeadFile)throws LoginOrRegisterException {
        ContentValues userInfo = new ContentValues();
        userInfo.put("telephone", username);
        userInfo.put("nickname", nickname);
        userInfo.put("password", password);
        userInfo.put("user_head", userHeadFile.getAbsolutePath());
        long userInfoInsert = db.insert("userInfo", null, userInfo);
        if (userInfoInsert < 0) {
            throw new LoginOrRegisterException(ProjectProperties.ERROR_CODE_USERNAME_EXISTED,"该手机号已经注册过了，请去登录！！！");
        }else{
            return (int) userInfoInsert;
        }
    }

    public List<AccountTypeInfo> getAccountTypeInfoList() {
        List<AccountTypeInfo> accountTypeInfoList = new ArrayList<>();
        Cursor accountTypeInfoCursor = db.query("accountTypeInfo", null, null, null, null, null, null);
        if (accountTypeInfoCursor != null && accountTypeInfoCursor.getCount() > 0) {
            while (accountTypeInfoCursor.moveToNext()){
                AccountTypeInfo accountTypeInfo = new AccountTypeInfo();
                accountTypeInfo.set_id(accountTypeInfoCursor.getInt(0));
                accountTypeInfo.setAccountTypeName(accountTypeInfoCursor.getString(1));
                accountTypeInfoList.add(accountTypeInfo);
            }
        }else{
            Toast.makeText(context, "暂无账单类别信息！！！", Toast.LENGTH_SHORT).show();
        }
        return accountTypeInfoList;
    }

    public void insertAccountInfo(ContentValues contentValues) {
        db.insert("accountInfo",null,contentValues);
    }
}
