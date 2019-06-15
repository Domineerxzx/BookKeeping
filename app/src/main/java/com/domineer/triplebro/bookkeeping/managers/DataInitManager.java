package com.domineer.triplebro.bookkeeping.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author Domineer
 * @data 2019/6/15,13:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataInitManager implements ServiceConnection {

    private Context context;

    public DataInitManager(Context context) {
        this.context = context;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
