package com.domineer.triplebro.bookkeeping.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.activities.AccountActivity;
import com.domineer.triplebro.bookkeeping.beans.AccountTypeInfo;
import com.domineer.triplebro.bookkeeping.interfaces.OnItemClickListener;

import java.util.List;

public class AccountTypeAdapter extends RecyclerView.Adapter<AccountTypeAdapter.ViewHolder> {

    private Context context;
    private List<AccountTypeInfo> data;
    private OnItemClickListener onItemClickListener;
    private int accountTypeId;
    private TextView lastTextView;

    public AccountTypeAdapter(Context context, List<AccountTypeInfo> data) {
        this.context = context;
        this.data = data;
    }

    public AccountTypeAdapter(Context context, List<AccountTypeInfo> data, int accountTypeId) {
        this.context = context;
        this.data = data;
        this.accountTypeId = accountTypeId;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<AccountTypeInfo> getData() {
        return data;
    }

    public void setData(List<AccountTypeInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_account_type, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ViewHolder holder = viewHolder;
        holder.tv_account_type.setText(data.get(i).getAccountTypeName());
        if (i == (accountTypeId - 1)) {
            holder.tv_account_type.setBackgroundResource(R.drawable.shape_button);
            holder.tv_account_type.setTextColor(Color.WHITE);
            lastTextView = holder.tv_account_type;
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener onItemClickListener;
        private TextView tv_account_type;


        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            initView(itemView);
        }

        private void initView(View itemView) {
            tv_account_type = itemView.findViewById(R.id.tv_account_type);
        }

        @Override
        public void onClick(View v) {
            if (lastTextView != null) {
                lastTextView.setBackgroundResource(R.drawable.shape_account_type);
                lastTextView.setTextColor(0xFFFFBB00);
            }
            if (lastTextView == tv_account_type) {
                lastTextView.setBackgroundResource(R.drawable.shape_account_type);
                lastTextView.setTextColor(0xFFFFBB00);
                lastTextView = null;
                onItemClickListener.onItemClick(v, -1);
            } else {
                lastTextView = tv_account_type;
                tv_account_type.setBackgroundResource(R.drawable.shape_button);
                tv_account_type.setTextColor(Color.WHITE);
                onItemClickListener.onItemClick(v, getPosition());
            }

        }
    }
}
