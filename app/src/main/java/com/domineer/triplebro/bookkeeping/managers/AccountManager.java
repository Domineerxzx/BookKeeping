package com.domineer.triplebro.bookkeeping.managers;

import android.content.ContentValues;
import android.content.Context;

import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

import java.util.List;

public class AccountManager {

    private Context context;
    private ISource dataBaseOP;

    public AccountManager(Context context) {
        this.context = context;
    }

    public List<AccountInfo> getAllAccountInfoList(int user_id) {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        List<AccountInfo> accountInfoList = dataBaseOP.getAllAccountInfoList(user_id);
        return accountInfoList;
    }

    public void updateAccountInfo(AccountInfo accountInfo) {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_collection",accountInfo.getIsCollection());
        dataBaseOP.updateAccountInfo(contentValues,accountInfo.get_id());
    }

    public List<AccountInfo> getCollectionAccountList(int user_id) {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        List<AccountInfo> accountInfoList = dataBaseOP.getCollectionAccountList(user_id);
        return accountInfoList;
    }
}
