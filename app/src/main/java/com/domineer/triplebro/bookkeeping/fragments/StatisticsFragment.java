package com.domineer.triplebro.bookkeeping.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.domineer.triplebro.bookkeeping.managers.StatisticsManager;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;
import com.domineer.triplebro.bookkeeping.utils.PCInitUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private View fragment_statistics;
    private PieChart pc_account;
    private List<String> accountTypeList;
    private GridLayout gl_legendLayout;
    private StatisticsManager statisticsManager;
    private List<List<AccountInfo>> listOfAccountInfoList;
    private SharedPreferences userInfo;
    private int user_id;
    private RelativeLayout rl_not_null;
    private RelativeLayout rl_null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_statistics = View.inflate(getActivity(), R.layout.fragment_statistics, null);
        initData();
        initView();
        setOnClickListener();
        return fragment_statistics;
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("_id", -1);
        if(user_id == -1){
            Toast.makeText(getActivity(), "还没登录呢，不能查看统计信息", Toast.LENGTH_SHORT).show();
            return;
        }
        statisticsManager = new StatisticsManager(getActivity());
        accountTypeList = statisticsManager.getAccountTypeNameList();
        if(accountTypeList.size() == 0){
            Toast.makeText(getActivity(), "暂无账单类别信息", Toast.LENGTH_SHORT).show();
            return;
        }
        listOfAccountInfoList = statisticsManager.getListOfAccountInfoList(user_id);
    }

    private void initView() {
        pc_account = (PieChart) fragment_statistics.findViewById(R.id.pc_account);
        gl_legendLayout = (GridLayout) fragment_statistics.findViewById(R.id.gl_legendLayout);
        rl_not_null = (RelativeLayout) fragment_statistics.findViewById(R.id.rl_not_null);
        rl_null = (RelativeLayout) fragment_statistics.findViewById(R.id.rl_null);
        if(user_id == -1){
            Toast.makeText(getActivity(), "还没登录呢，不能查看统计信息", Toast.LENGTH_SHORT).show();
            rl_not_null.setVisibility(View.GONE);
            rl_null.setVisibility(View.VISIBLE);
            return;
        }else{
            rl_not_null.setVisibility(View.VISIBLE);
            rl_null.setVisibility(View.GONE);
        }
        if(accountTypeList.size() == 0){
            Toast.makeText(getActivity(), "暂无账单类别信息", Toast.LENGTH_SHORT).show();
            return;
        }
        PCInitUtils.initPC(getActivity(),pc_account,accountTypeList,gl_legendLayout,ProjectProperties.colors,listOfAccountInfoList);
    }



    private void setOnClickListener() {
        // 设置一个选中区域监听
        pc_account.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                pc_account.setDescription(accountTypeList.get(e.getXIndex()) + "类占比" +  e.getVal()*100 + "%  账单数："+(int)(e.getVal()*listOfAccountInfoList.get(5).size()));
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

}
