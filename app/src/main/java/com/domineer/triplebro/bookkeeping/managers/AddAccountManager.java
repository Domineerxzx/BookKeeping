package com.domineer.triplebro.bookkeeping.managers;

import android.content.ContentValues;
import android.content.Context;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddAccountManager {

    private Context context;
    private ISource dataBaseOP;

    public AddAccountManager(Context context) {
        this.context = context;
    }

    public List<AccountTypeInfo> getAccountTypeInfoList() {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        List<AccountTypeInfo> accountTypeInfoList = dataBaseOP.getAccountTypeInfoList();
        return accountTypeInfoList;
    }

    public void addAccount(int user_id, String ps, String account_number, AccountTypeInfo accountTypeInfo) {
        dataBaseOP = (DataBaseOP)SourceFactory.sourceCreate(context,SourceType.SOURCE_FROM_DB);
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_type_id",accountTypeInfo.get_id());
        contentValues.put("user_id",user_id);
        contentValues.put("account_name",accountTypeInfo.getAccountTypeName());
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        contentValues.put("account_time",simpleDateFormat.format(date));
        contentValues.put("account_content",ps);
        contentValues.put("account_money",account_number);
        contentValues.put("is_collection",0);
        dataBaseOP.insertAccountInfo(contentValues);
    }
}
