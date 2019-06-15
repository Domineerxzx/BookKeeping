package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.activities.AboutUsActivity;
import com.domineer.triplebro.bookkeeping.activities.CollectionActivity;
import com.domineer.triplebro.bookkeeping.activities.ContactUsActivity;
import com.domineer.triplebro.bookkeeping.activities.LoginActivity;
import com.domineer.triplebro.bookkeeping.activities.SettingActivity;
import com.domineer.triplebro.bookkeeping.activities.UserInfoActivity;

/**
 * @author Domineer
 * @data 2019/6/15,13:02
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MyselfFragment extends Fragment implements View.OnClickListener {

    private View fragment_myself;
    private ImageView iv_user_head;
    private ImageView iv_collection;
    private ImageView iv_collection_more;
    private ImageView iv_about_us;
    private ImageView iv_about_us_more;
    private ImageView iv_contact_us;
    private ImageView iv_contact_us_more;
    private ImageView iv_setting;
    private ImageView iv_setting_more;
    private RelativeLayout rl_collection;
    private RelativeLayout rl_about_us;
    private RelativeLayout rl_contact_us;
    private RelativeLayout rl_setting;
    private LinearLayout ll_user_info;
    private TextView tv_nickname;
    private TextView tv_username;
    private TextView tv_collection;
    private TextView tv_about_us;
    private TextView tv_contact_us;
    private TextView tv_setting;
    private SharedPreferences userInfo;
    private String username;
    private String nickname;
    private String userHead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_myself = View.inflate(getActivity(), R.layout.fragment_myself, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_myself;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        iv_user_head = (ImageView) fragment_myself.findViewById(R.id.iv_user_head);
        iv_collection = (ImageView) fragment_myself.findViewById(R.id.iv_collection);
        iv_collection_more = (ImageView) fragment_myself.findViewById(R.id.iv_collection_more);
        iv_about_us = (ImageView) fragment_myself.findViewById(R.id.iv_about_us);
        iv_about_us_more = (ImageView) fragment_myself.findViewById(R.id.iv_about_us_more);
        iv_contact_us = (ImageView) fragment_myself.findViewById(R.id.iv_contact_us);
        iv_contact_us_more = (ImageView) fragment_myself.findViewById(R.id.iv_contact_us_more);
        iv_setting = (ImageView) fragment_myself.findViewById(R.id.iv_setting);
        iv_setting_more = (ImageView) fragment_myself.findViewById(R.id.iv_setting_more);
        rl_collection = (RelativeLayout) fragment_myself.findViewById(R.id.rl_collection);
        rl_about_us = (RelativeLayout) fragment_myself.findViewById(R.id.rl_about_us);
        rl_contact_us = (RelativeLayout) fragment_myself.findViewById(R.id.rl_contact_us);
        rl_setting = (RelativeLayout) fragment_myself.findViewById(R.id.rl_setting);
        ll_user_info = (LinearLayout) fragment_myself.findViewById(R.id.ll_user_info);
        tv_nickname = (TextView) fragment_myself.findViewById(R.id.tv_nickname);
        tv_username = (TextView) fragment_myself.findViewById(R.id.tv_username);
        tv_collection = (TextView) fragment_myself.findViewById(R.id.tv_collection);
        tv_about_us = (TextView) fragment_myself.findViewById(R.id.tv_about_us);
        tv_contact_us = (TextView) fragment_myself.findViewById(R.id.tv_contact_us);
        tv_setting = (TextView) fragment_myself.findViewById(R.id.tv_setting);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        username = userInfo.getString("username", "");
        nickname = userInfo.getString("nickname", "");
        userHead = userInfo.getString("userHead", "");
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(nickname)) {
            tv_username.setText(R.string.usernameDefault);
            tv_nickname.setText(R.string.nicknameDefault);
        } else {
            tv_username.setText("ID：" + username);
            tv_nickname.setText(nickname);
        }
        if (TextUtils.isEmpty(username)) {
            Glide.with(getActivity()).load(R.drawable.user_head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        } else {
            Glide.with(getActivity()).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }
    }

    private void setOnClickListener() {
        iv_user_head.setOnClickListener(this);
        iv_collection.setOnClickListener(this);
        iv_collection_more.setOnClickListener(this);
        iv_about_us.setOnClickListener(this);
        iv_about_us_more.setOnClickListener(this);
        iv_contact_us.setOnClickListener(this);
        iv_contact_us_more.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        iv_setting_more.setOnClickListener(this);
        rl_collection.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        rl_contact_us.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        tv_nickname.setOnClickListener(this);
        tv_username.setOnClickListener(this);
        tv_collection.setOnClickListener(this);
        tv_about_us.setOnClickListener(this);
        tv_contact_us.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        ll_user_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
            case R.id.ll_user_info:
            case R.id.tv_nickname:
            case R.id.tv_username:
                String username = tv_username.getText().toString().trim();
                String nickname = tv_nickname.getText().toString().trim();
                if (username.equals(R.string.usernameDefault) && nickname.equals(R.string.nicknameDefault)) {
                    Intent login = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(login);
                } else {
                    Intent userInfo = new Intent(getActivity(), UserInfoActivity.class);
                    getActivity().startActivity(userInfo);
                }
                break;
            case R.id.rl_collection:
            case R.id.iv_collection:
            case R.id.tv_collection:
            case R.id.iv_collection_more:
                Intent collection = new Intent(getActivity(), CollectionActivity.class);
                getActivity().startActivity(collection);
                break;
            case R.id.rl_about_us:
            case R.id.iv_about_us:
            case R.id.tv_about_us:
            case R.id.iv_about_us_more:
                Intent about_us = new Intent(getActivity(), AboutUsActivity.class);
                getActivity().startActivity(about_us);
                break;
            case R.id.rl_contact_us:
            case R.id.iv_contact_us:
            case R.id.tv_contact_us:
            case R.id.iv_contact_us_more:
                Intent contact_us = new Intent(getActivity(), ContactUsActivity.class);
                getActivity().startActivity(contact_us);
                break;
            case R.id.rl_setting:
            case R.id.iv_setting:
            case R.id.tv_setting:
            case R.id.iv_setting_more:
                Intent setting = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(setting);
                break;
        }
    }
}
