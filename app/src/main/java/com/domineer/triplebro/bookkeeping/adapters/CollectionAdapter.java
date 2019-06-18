package com.domineer.triplebro.bookkeeping.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.beans.CollectionInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/6/17,23:14
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectionAdapter extends BaseAdapter {

    private Context context;
    private List<CollectionInfo> collectionInfoList;

    public CollectionAdapter(Context context, List<CollectionInfo> collectionInfoList) {
        this.context = context;
        this.collectionInfoList = collectionInfoList;
    }

    @Override
    public int getCount() {
        return collectionInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_myself, null);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public class ViewHolder{


    }
}
