package com.domineer.triplebro.bookkeeping.sourceprovider;

import android.content.Context;

import com.domineer.triplebro.bookkeeping.enmuration.SourceType;
import com.domineer.triplebro.bookkeeping.interfaces.ISource;
import com.domineer.triplebro.bookkeeping.sourceOP.DataBaseOP;
import com.domineer.triplebro.bookkeeping.sourceOP.HttpOP;

import java.util.HashMap;
import java.util.Map;

public class SourceMap {

    public static Map<SourceType,ISource> generateSource(Context context){
        Map<SourceType, ISource> sourceMap = new HashMap<>();
        sourceMap.put(SourceType.SOURCE_FROM_SERVER,new HttpOP(context));
        sourceMap.put(SourceType.SOURCE_FROM_DB,new DataBaseOP(context));
        return sourceMap;
    }
}
