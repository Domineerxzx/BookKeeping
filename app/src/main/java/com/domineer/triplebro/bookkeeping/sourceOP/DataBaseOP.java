package com.domineer.triplebro.bookkeeping.sourceOP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.beans.UserInfo;
import com.domineer.triplebro.bookkeeping.database.MyOpenHelper;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.handlers.AdPictureHandler;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataBaseOP implements ISource {

    private Context context;
    private final MyOpenHelper myOpenHelper;
    private final SQLiteDatabase db;
    private AccountInfo accountInfo;

    public DataBaseOP(Context context) {
        this.context = context;
        myOpenHelper = new MyOpenHelper(context);
        db = myOpenHelper.getWritableDatabase();
    }

    public UserInfo checkUser(String username, String password) throws LoginOrRegisterException {

        Cursor userInfoCursor = db.query("userInfo", null, "telephone = ?", new String[]{username}, null, null, null);
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            userInfoCursor.moveToNext();
            if (userInfoCursor.getString(2).equals(password)) {
                UserInfo userInfo = new UserInfo();
                userInfo.set_id(userInfoCursor.getInt(0));
                userInfo.setTelephone(userInfoCursor.getString(1));
                userInfo.setPassword(userInfoCursor.getString(2));
                userInfo.setNickname(userInfoCursor.getString(3));
                userInfo.setUserHead(userInfoCursor.getString(4));
                userInfoCursor.close();
                return userInfo;
            } else {
                userInfoCursor.close();
                throw new LoginOrRegisterException(ProjectProperties.ERROR_CODE_PASSWORD_ERROR, "密码错误，请检查后进行登录！！！");
            }
        } else {
            if (userInfoCursor != null) {
                userInfoCursor.close();
            }
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

    @Override
    public void getAdPicture(String adPicturePath, AdPictureHandler adPictureHandler) {

    }

    @Override
    public void insertAccountTypeInfo() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type_name", "教育");
        db.insert("accountTypeInfo", null, contentValues);
        contentValues.put("type_name", "餐饮");
        db.insert("accountTypeInfo", null, contentValues);
        contentValues.put("type_name", "出行");
        db.insert("accountTypeInfo", null, contentValues);
        contentValues.put("type_name", "购物");
        db.insert("accountTypeInfo", null, contentValues);
        contentValues.put("type_name", "医疗");
        db.insert("accountTypeInfo", null, contentValues);
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
        if (accountTypeInfoCursor != null) {
            accountTypeInfoCursor.close();
        }
        return accountTypeInfoList;
    }

    public void insertAccountInfo(ContentValues contentValues) {
        db.insert("accountInfo",null,contentValues);
    }

    public void updateAccountInfo(ContentValues contentValues, int id) {
        db.update("accountInfo",contentValues,"_id = ?",new String[]{String.valueOf(id)});
    }

    public List<AccountInfo> getAllAccountInfoList(int user_id) {
        List<AccountInfo> accountInfoList = new ArrayList<>();
        Cursor accountInfoCursor = db.query("accountInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (accountInfoCursor != null && accountInfoCursor.getCount() > 0) {
            while (accountInfoCursor.moveToNext()){
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.set_id(accountInfoCursor.getInt(0));
                accountInfo.setAccountTypeId(accountInfoCursor.getInt(1));
                accountInfo.setUserId(accountInfoCursor.getInt(2));
                accountInfo.setAccountName(accountInfoCursor.getString(3));
                accountInfo.setAccountTime(accountInfoCursor.getString(4));
                accountInfo.setAccountContent(accountInfoCursor.getString(5));
                accountInfo.setAccountMoney(accountInfoCursor.getString(6));
                accountInfo.setIsCollection(accountInfoCursor.getInt(7));
                accountInfoList.add(accountInfo);
            }
        }else{
            Toast.makeText(context, "暂无账单信息！！！", Toast.LENGTH_SHORT).show();
        }
        if (accountInfoCursor != null) {
            accountInfoCursor.close();
        }
        return accountInfoList;
    }

    public List<List<AccountInfo>> getListOfAccountInfoList(int user_id) {
        List<List<AccountInfo>> listOfAccountInfoList = new ArrayList<>();
        List<AccountInfo> accountInfoEduList = new ArrayList<>();
        List<AccountInfo> accountInfoEatList = new ArrayList<>();
        List<AccountInfo> accountInfoOutList = new ArrayList<>();
        List<AccountInfo> accountInfoShopList = new ArrayList<>();
        List<AccountInfo> accountInfoMidList = new ArrayList<>();
        List<AccountInfo> accountInfoAllList = new ArrayList<>();
        Cursor accountInfoCursor = db.query("accountInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (accountInfoCursor != null && accountInfoCursor.getCount() > 0) {
            while (accountInfoCursor.moveToNext()){
                accountInfo = new AccountInfo();
                accountInfo.set_id(accountInfoCursor.getInt(0));
                accountInfo.setAccountTypeId(accountInfoCursor.getInt(1));
                accountInfo.setUserId(accountInfoCursor.getInt(2));
                accountInfo.setAccountName(accountInfoCursor.getString(3));
                accountInfo.setAccountTime(accountInfoCursor.getString(4));
                accountInfo.setAccountContent(accountInfoCursor.getString(5));
                accountInfo.setAccountMoney(accountInfoCursor.getString(6));
                accountInfo.setIsCollection(accountInfoCursor.getInt(7));
                switch (accountInfoCursor.getInt(1)){
                    case 1:
                        accountInfoEduList.add(accountInfo);
                        break;
                    case 2:
                        accountInfoEatList.add(accountInfo);
                        break;
                    case 3:
                        accountInfoOutList.add(accountInfo);
                        break;
                    case 4:
                        accountInfoShopList.add(accountInfo);
                        break;
                    case 5:
                        accountInfoMidList.add(accountInfo);
                        break;
                }
                accountInfoAllList.add(accountInfo);
            }
        }
        listOfAccountInfoList.add(accountInfoEduList);
        listOfAccountInfoList.add(accountInfoEatList);
        listOfAccountInfoList.add(accountInfoOutList);
        listOfAccountInfoList.add(accountInfoShopList);
        listOfAccountInfoList.add(accountInfoMidList);
        listOfAccountInfoList.add(accountInfoAllList);
        if (accountInfoCursor != null) {
            accountInfoCursor.close();
        }
        return listOfAccountInfoList;
    }

    public List<AccountInfo> getCollectionAccountList(int user_id) {
        List<AccountInfo> accountInfoList = new ArrayList<>();
        Cursor accountInfoCursor = db.query("accountInfo", null, "user_id = ? and is_collection = ?", new String[]{String.valueOf(user_id), String.valueOf(1)}, null, null, null);
        if (accountInfoCursor != null && accountInfoCursor.getCount() > 0) {
            while (accountInfoCursor.moveToNext()){
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.set_id(accountInfoCursor.getInt(0));
                accountInfo.setAccountTypeId(accountInfoCursor.getInt(1));
                accountInfo.setUserId(accountInfoCursor.getInt(2));
                accountInfo.setAccountName(accountInfoCursor.getString(3));
                accountInfo.setAccountTime(accountInfoCursor.getString(4));
                accountInfo.setAccountContent(accountInfoCursor.getString(5));
                accountInfo.setAccountMoney(accountInfoCursor.getString(6));
                accountInfo.setIsCollection(accountInfoCursor.getInt(7));
                accountInfoList.add(accountInfo);
            }
        }else{
            Toast.makeText(context, "暂无收藏信息！！！", Toast.LENGTH_SHORT).show();
        }
        if (accountInfoCursor != null) {
            accountInfoCursor.close();
        }
        return accountInfoList;
    }
}
