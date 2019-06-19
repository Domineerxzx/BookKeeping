package com.domineer.triplebro.bookkeeping.managers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {

    private Context context;
    private ISource dataBaseOP;

    public StatisticsManager(Context context) {
        this.context = context;
    }

    public List<String> getAccountTypeNameList() {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        List<AccountTypeInfo> accountTypeInfoList = dataBaseOP.getAccountTypeInfoList();
        List<String> accountTypeNameList = new ArrayList<String>();
        for (AccountTypeInfo accountTypeInfo : accountTypeInfoList) {
            accountTypeNameList.add(accountTypeInfo.getAccountTypeName());
        }
        return accountTypeNameList;
    }

    public List<List<AccountInfo>> getListOfAccountInfoList(int user_id) {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        List<List<AccountInfo>> listOfAccountInfoList = dataBaseOP.getListOfAccountInfoList(user_id);
        return listOfAccountInfoList;
    }
}
