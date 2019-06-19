package com.domineer.triplebro.bookkeeping.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.handlers.AdPictureHandler;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;
import com.domineer.triplebro.bookkeeping.sourceOP.HttpOP;
import com.domineer.triplebro.bookkeeping.sourceprovider.SourceFactory;

import java.io.InputStream;

public class MyService extends Service {


    private ISource httpOP;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public void getAdPicture(AdPictureHandler adPictureHandler) {
            MyService.this.getAdPicture(adPictureHandler);
        }
    }

    private void getAdPicture(final AdPictureHandler adPictureHandler) {

        httpOP = (HttpOP)SourceFactory.sourceCreate(this,SourceType.SOURCE_FROM_SERVER);
        httpOP.getAdPicture(ProjectProperties.AD_PICTURE_PATH,adPictureHandler);

    }
}
