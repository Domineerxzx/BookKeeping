package com.domineer.triplebro.bookkeeping.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.handlers.AdPictureHandler;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.services.MyService;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

public class DataInitManager implements ServiceConnection {

    private Context context;
    private AdPictureHandler adPictureHandler;
    private ISource databaseOP;

    public DataInitManager(Context context, AdPictureHandler adPictureHandler) {
        this.context = context;
        this.adPictureHandler = adPictureHandler;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.MyBinder myBinder = (MyService.MyBinder) service;
        myBinder.getAdPicture(adPictureHandler);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public void getAdPicture() {
        Intent intent = new Intent(context, MyService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    public void insertAccountTypeInfo() {
        databaseOP = (DataBaseOP)SourceFactory.sourceCreate(context, SourceType.SOURCE_FROM_DB);
        databaseOP.insertAccountTypeInfo();
    }
}
