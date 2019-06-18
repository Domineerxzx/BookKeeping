package com.domineer.triplebro.bookkeeping.managers;

import android.content.ContentValues;
import android.content.Context;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/6/18,22:10
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AddAccountManager {

    private Context context;
    private DataBaseOP dataBaseOP;

    public AddAccountManager(Context context) {
        this.context = context;
    }

    public List<AccountTypeInfo> getAccountTypeInfoList() {
        dataBaseOP = new DataBaseOP(context);
        List<AccountTypeInfo> accountTypeInfoList = dataBaseOP.getAccountTypeInfoList();
        return accountTypeInfoList;
    }

    public void addAccount(int user_id, String ps, String account_number, AccountTypeInfo accountTypeInfo) {
        dataBaseOP = new DataBaseOP(context);
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
        dataBaseOP.insertAccountInfo(contentValues);
    }
}
