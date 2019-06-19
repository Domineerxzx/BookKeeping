package com.domineer.triplebro.bookkeeping.sourceOP;

import android.content.ContentValues;
import android.content.Context;
import android.os.Message;

import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.beans.UserInfo;
import com.domineer.triplebro.bookkeeping.exception.LoginOrRegisterException;
import com.domineer.triplebro.bookkeeping.handlers.AdPictureHandler;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;
import com.domineer.triplebro.bookkeeping.utils.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpOP implements ISource {

    private Context context;
    private InputStream inputStream;

    public HttpOP(Context context) {
        this.context = context;
    }

    @Override
    public List<AccountTypeInfo> getAccountTypeInfoList() {
        return null;
    }

    @Override
    public List<List<AccountInfo>> getListOfAccountInfoList(int user_id) {
        return null;
    }

    @Override
    public List<AccountInfo> getAllAccountInfoList(int user_id) {
        return null;
    }

    @Override
    public int updateAccountInfo(ContentValues contentValues, int id) {

        return id;
    }

    @Override
    public List<AccountInfo> getCollectionAccountList(int user_id) {
        return null;
    }

    @Override
    public void insertAccountInfo(ContentValues contentValues) {

    }

    @Override
    public UserInfo checkUser(String username, String password) throws LoginOrRegisterException {
        return null;
    }

    @Override
    public int registerUser(String username, String nickname, String password, File userHeadFile) throws LoginOrRegisterException {
        return 0;
    }

    public void getAdPicture(String path, final AdPictureHandler adPictureHandler){
        HttpUtils.sendOkHttpRequest(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String adPicture = response.body().string();
                Message message = new Message();
                message.obj = adPicture;
                message.what = ProjectProperties.AD_PICTURE;
                adPictureHandler.sendMessage(message);
                try {
                    Thread.sleep(2500);
                    adPictureHandler.sendEmptyMessage(ProjectProperties.SKIP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void insertAccountTypeInfo() {

    }

    @Override
    public int deleteAccountInfo(int id) {
        return 0;
    }
}
