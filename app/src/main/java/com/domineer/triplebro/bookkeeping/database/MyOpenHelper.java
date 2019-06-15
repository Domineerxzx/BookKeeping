package com.domineer.triplebro.bookkeeping.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Domineer
 * @data 2019/6/15,12:33
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MyOpenHelper extends SQLiteOpenHelper {



    public MyOpenHelper(Context context) {
        super(context, "BookKeeping", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //用户表
        db.execSQL("create table userInfo(_id integer primary key autoincrement,telephone varchar(20) unique," +
                "password varchar(20),nickname varchar(20),user_head varchar(200))");

        //收藏表
        db.execSQL("create table colloectionInfo(_id integer primary key autoincrement,account_id integer," +
                "collection_user_id integer)");

        //账单表
        db.execSQL("create table accountInfo(_id integer primary key autoincrement,account_type_id integer," +
                "user_id integer,account_name varchar(20),account_time varchar(10),account_content varchar(200)," +
                "account_money varchar(30),is_collection integer)");

        //账单类别表
        db.execSQL("create table accountTypeInfo(_id integer primary key autoincrement,telephone varchar(20)" +
                " unique,password varchar(20),nickname varchar(20),user_head varchar(200))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
