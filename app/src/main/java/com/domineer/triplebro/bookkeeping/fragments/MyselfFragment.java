package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.activities.AboutUsActivity;
import com.domineer.triplebro.bookkeeping.activities.CollectionActivity;
import com.domineer.triplebro.bookkeeping.activities.ContactUsActivity;
import com.domineer.triplebro.bookkeeping.activities.LoginActivity;
import com.domineer.triplebro.bookkeeping.activities.SettingActivity;
import com.domineer.triplebro.bookkeeping.database.MyOpenHelper;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;
import com.domineer.triplebro.bookkeeping.utils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.bookkeeping.utils.RealPathFromUriUtils;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * @author Domineer
 * @data 2019/6/15,13:02
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MyselfFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

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
    private RelativeLayout rl_user_head_large;
    private ImageView iv_user_head_large;
    private ImageView iv_close_user_head_large;
    private File userHeadFile;

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
        rl_user_head_large = (RelativeLayout) fragment_myself.findViewById(R.id.rl_user_head_large);
        iv_user_head_large = (ImageView) fragment_myself.findViewById(R.id.iv_user_head_large);
        iv_close_user_head_large = (ImageView) fragment_myself.findViewById(R.id.iv_close_user_head_large);
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
        iv_user_head.setOnLongClickListener(this);
        rl_user_head_large.setOnClickListener(this);
        iv_user_head_large.setOnLongClickListener(this);
        iv_close_user_head_large.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
            case R.id.ll_user_info:
            case R.id.tv_username:
            case R.id.tv_nickname:
                String username = tv_username.getText().toString().trim();
                String nickname = tv_nickname.getText().toString().trim();
                if (username.equals("暂无登录信息") || nickname.equals("点击  登录/注册")) {
                    Intent login = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(login);
                } else {
                    Toast.makeText(getActivity(), "长按头像可查看大头像", Toast.LENGTH_SHORT).show();
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
            case R.id.iv_close_user_head_large:
                rl_user_head_large.setVisibility(View.GONE);
                setClickableTrue();
                break;
            case R.id.rl_user_head_large:
                rl_user_head_large.setVisibility(View.GONE);
                setClickableTrue();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.iv_user_head:
                rl_user_head_large.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(username)) {
                    Glide.with(getActivity()).load(R.drawable.user_head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                } else {
                    Glide.with(getActivity()).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                }
                setClickableFalse();
                break;
            case R.id.iv_user_head_large:
                long timeStamp = System.currentTimeMillis();
                SharedPreferences.Editor edit = userInfo.edit();
                edit.putLong("timeStamp", timeStamp);
                edit.commit();
                ChooseUserHeadDialogUtil.showDialog(this, username, timeStamp);
                break;
        }
        return true;
    }

    private void setClickableFalse() {
        iv_user_head.setClickable(false);
        iv_collection.setClickable(false);
        iv_collection_more.setClickable(false);
        iv_about_us.setClickable(false);
        iv_about_us_more.setClickable(false);
        iv_contact_us.setClickable(false);
        iv_contact_us_more.setClickable(false);
        iv_setting.setClickable(false);
        iv_setting_more.setClickable(false);
        rl_collection.setClickable(false);
        rl_about_us.setClickable(false);
        rl_contact_us.setClickable(false);
        rl_setting.setClickable(false);
        tv_nickname.setClickable(false);
        tv_username.setClickable(false);
        tv_collection.setClickable(false);
        tv_about_us.setClickable(false);
        tv_contact_us.setClickable(false);
        tv_setting.setClickable(false);
        ll_user_info.setClickable(false);
    }

    private void setClickableTrue() {
        iv_user_head.setClickable(true);
        iv_collection.setClickable(true);
        iv_collection_more.setClickable(true);
        iv_about_us.setClickable(true);
        iv_about_us_more.setClickable(true);
        iv_contact_us.setClickable(true);
        iv_contact_us_more.setClickable(true);
        iv_setting.setClickable(true);
        iv_setting_more.setClickable(true);
        rl_collection.setClickable(true);
        rl_about_us.setClickable(true);
        rl_contact_us.setClickable(true);
        rl_setting.setClickable(true);
        tv_nickname.setClickable(true);
        tv_username.setClickable(true);
        tv_collection.setClickable(true);
        tv_about_us.setClickable(true);
        tv_contact_us.setClickable(true);
        tv_setting.setClickable(true);
        ll_user_info.setClickable(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        SharedPreferences.Editor edit = userInfo.edit();
        MyOpenHelper myOpenHelper = new MyOpenHelper(getActivity());
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    userHeadFile = new File(RealPathFromUriUtils.getRealPathFromUri(getActivity(), data.getData()));
                    Glide.with(getActivity()).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    long timeStamp = userInfo.getLong("timeStamp", -1);
                    userHeadFile = new File(getActivity().getFilesDir() + File.separator + "images" + File.separator + username + timeStamp + ".jpg");
                    Glide.with(getActivity()).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            edit.commit();
            writableDatabase.update("userInfo", contentValues, "telephone = ?", new String[]{username});
            writableDatabase.close();
        } else {
            Toast.makeText(getActivity(), "取消修改", Toast.LENGTH_SHORT).show();
        }
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }

}
