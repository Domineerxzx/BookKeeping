package com.domineer.triplebro.bookkeeping.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.managers.AccountManager;

import java.util.List;

public class AccountAdapter extends BaseAdapter {

    private Context context;
    private List<AccountInfo> accountInfoList;
    private AccountManager accountManager;
    private SharedPreferences userInfo;
    private int user_id;
    private int from;

    public AccountAdapter(Context context, List<AccountInfo> accountInfoList, AccountManager accountManager,int from) {
        this.context = context;
        this.accountInfoList = accountInfoList;
        this.accountManager = accountManager;
        this.from = from;
    }

    public List<AccountInfo> getAccountInfoList() {
        return accountInfoList;
    }

    public void setAccountInfoList(List<AccountInfo> accountInfoList) {
        this.accountInfoList = accountInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return accountInfoList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_account, null);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_number = convertView.findViewById(R.id.tv_number);
            viewHolder.tv_ps = convertView.findViewById(R.id.tv_ps);
            viewHolder.tv_type = convertView.findViewById(R.id.tv_type);
            viewHolder.iv_collection = convertView.findViewById(R.id.iv_collection);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_time.setText(accountInfoList.get(position).getAccountTime());
        viewHolder.tv_number.setText(accountInfoList.get(position).getAccountMoney());
        viewHolder.tv_ps.setText(accountInfoList.get(position).getAccountContent());
        viewHolder.tv_type.setText(accountInfoList.get(position).getAccountName());
        if(accountInfoList.get(position).getIsCollection() == 1){
            viewHolder.iv_collection.setBackgroundResource(R.mipmap.collection_click);
        }else{
            viewHolder.iv_collection.setBackgroundResource(R.mipmap.collection);
        }
        viewHolder.iv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountInfoList.get(position).getIsCollection() == 1){
                    viewHolder.iv_collection.setBackgroundResource(R.mipmap.collection);
                    accountInfoList.get(position).setIsCollection(0);
                    accountManager.updateAccountInfo(accountInfoList.get(position));
                    if(from == 2){
                        userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        user_id = userInfo.getInt("_id", -1);
                        accountInfoList = accountManager.getCollectionAccountList(user_id);
                    }
                    setAccountInfoList(accountInfoList);
                }else{
                    viewHolder.iv_collection.setBackgroundResource(R.mipmap.collection_click);
                    accountInfoList.get(position).setIsCollection(1);
                    accountManager.updateAccountInfo(accountInfoList.get(position));
                    setAccountInfoList(accountInfoList);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{

        private TextView tv_time;
        private TextView tv_number;
        private TextView tv_ps;
        private TextView tv_type;
        private ImageView iv_collection;

    }
}
