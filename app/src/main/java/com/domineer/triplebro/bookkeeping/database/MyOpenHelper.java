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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
