package com.domineer.triplebro.bookkeeping.interfaces;

import android.content.ContentValues;

import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.beans.UserInfo;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.handlers.AdPictureHandler;

import java.io.File;
import java.util.List;

public interface ISource {
    List<AccountTypeInfo> getAccountTypeInfoList();

    List<List<AccountInfo>> getListOfAccountInfoList(int user_id);

    List<AccountInfo> getAllAccountInfoList(int user_id);

    void updateAccountInfo(ContentValues contentValues, int id);

    List<AccountInfo> getCollectionAccountList(int user_id);

    void insertAccountInfo(ContentValues contentValues);

    UserInfo checkUser(String username, String password) throws LoginOrRegisterException;

    int registerUser(String username, String nickname, String password, File userHeadFile) throws LoginOrRegisterException;

    void getAdPicture(String adPicturePath, AdPictureHandler adPictureHandler);

    void insertAccountTypeInfo();
}
