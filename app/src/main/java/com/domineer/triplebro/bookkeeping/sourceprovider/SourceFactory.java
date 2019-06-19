package com.domineer.triplebro.bookkeeping.sourceprovider;

import android.content.Context;

import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;

public class SourceFactory {
    public static ISource sourceCreate(Context context, SourceType type){
        return SourceMap.generateSource(context).get(type);
    }
}
