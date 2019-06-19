package com.domineer.triplebro.bookkeeping.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.beans.AccountInfo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;
import java.util.List;

public class PCInitUtils {

    public static void initPC(Context context,PieChart pc_account,List<String> accountTypeList,GridLayout gl_legendLayout,int[] colors,List<List<AccountInfo>> listOfAccountInfoList) {
        // 设置饼图是否接收点击事件，默认为true
        pc_account.setTouchEnabled(true);
        //设置饼图是否使用百分比
        pc_account.setUsePercentValues(true);
        //设置饼图右下角的文字描述
        pc_account.setDescription("");
        /*//设置饼图右下角的文字描述   文字位置
        pc_account.setDescriptionPosition(895, 1500);*/
        //设置饼图右下角的文字大小
        pc_account.setDescriptionTextSize(16);
        pc_account.setExtraOffsets(15, -20, 15, 0);//left   top  right  bottom

        //是否显示圆盘中间文字，默认显示
        pc_account.setDrawCenterText(true);
        pc_account.setHoleColor(Color.WHITE);//设置中间圆盘的颜色
        //设置圆盘中间文字
        pc_account.setCenterText(generateCenterSpannableText(listOfAccountInfoList));
        pc_account.setTransparentCircleColor(context.getResources().getColor(R.color.Baffle));

        pc_account.setHoleRadius(60); //设置中间圆盘的半径,值为所占饼图的百分比
        pc_account.setTransparentCircleRadius(63); //设置中间透明圈的半径,值为所占饼图的百分比

        pc_account.setDrawCenterText(true);//饼状图中间可以添加文字

        //设置圆盘是否转动，默认转动
        pc_account.setRotationEnabled(true);
        //设置初始旋转角度
        pc_account.setRotationAngle(100);

        customizeLegend(context,pc_account,accountTypeList,gl_legendLayout,colors);
        //绑定数据
        bindData(context,pc_account,accountTypeList.size(),accountTypeList,listOfAccountInfoList);
    }

    //绑定数据
    private static void bindData(Context context, PieChart pc_account, int count, List<String> accountTypeList, List<List<AccountInfo>> listOfAccountInfoList) {
        /**
         * nameList用来表示每个饼块上的文字内容
         * 如：部分一，部分二，部分三
         */
        List<String> nameList = accountTypeList;
        /**
         * valueList将一个饼形图分成三部分，各个区域的百分比的值
         * Entry构造函数中
         * 第一个值代表所占比例，
         * 第二个值代表区域位置
         * （可以有第三个参数，表示携带的数据object）这里没用到
         */
        List<Entry> valueList = new ArrayList<Entry>();
        valueList.add(new Entry(((float) listOfAccountInfoList.get(0).size())/((float) listOfAccountInfoList.get(5).size()), 0));
        valueList.add(new Entry(((float) listOfAccountInfoList.get(1).size())/((float) listOfAccountInfoList.get(5).size()), 1));
        valueList.add(new Entry(((float) listOfAccountInfoList.get(2).size())/((float) listOfAccountInfoList.get(5).size()), 2));
        valueList.add(new Entry(((float) listOfAccountInfoList.get(3).size())/((float) listOfAccountInfoList.get(5).size()), 3));
        valueList.add(new Entry(((float) listOfAccountInfoList.get(4).size())/((float) listOfAccountInfoList.get(5).size()), 4));

        //显示在比例图上
        PieDataSet dataSet = new PieDataSet(valueList, "");
        //设置各个饼状图之间的距离
        dataSet.setSliceSpace(0f);
        // 部分区域被选中时多出的长度
        dataSet.setSelectionShift(15f);

        // 设置饼图各个区域颜色
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(context.getResources().getColor(R.color.App_Color));
        colors.add(context.getResources().getColor(R.color.Black));
        colors.add(context.getResources().getColor(R.color.colorAccent));
        colors.add(context.getResources().getColor(R.color.colorPrimary));
        colors.add(context.getResources().getColor(R.color.colorPrimaryDark));
        dataSet.setColors(colors);

        PieData data = new PieData(nameList, dataSet);
        //设置以百分比显示
        data.setValueFormatter(new PercentFormatter());
        //区域文字的大小
        data.setValueTextSize(11f);
        //设置区域文字的颜色
        data.setValueTextColor(Color.WHITE);
        //设置区域文字的字体
        data.setValueTypeface(Typeface.DEFAULT);

        pc_account.setData(data);

        //设置是否显示区域文字内容
        pc_account.setDrawSliceText(!pc_account.isDrawSliceTextEnabled());
        //设置是否显示区域百分比的值
        for (IDataSet<?> set : pc_account.getData().getDataSets()) {
            set.setDrawValues(set.isDrawValuesEnabled());
        }
        // undo all highlights
        pc_account.highlightValues(null);
        pc_account.invalidate();
    }

    //中间显示的文字数据
    private static SpannableString generateCenterSpannableText(List<List<AccountInfo>> listOfAccountInfoList) {
        SpannableString s = new SpannableString("账单总数\n"+listOfAccountInfoList.get(5).size());
        s.setSpan(new RelativeSizeSpan(1.2f), 0, 4, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, 4, 0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 4, 0);
        s.setSpan(new RelativeSizeSpan(3.8f), 4, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 4, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 4, s.length(), 0);
        return s;
    }

    //定制图例，通过代码生成布局
    private static void customizeLegend(Context context, PieChart pc_account, List<String> accountTypeList, GridLayout gl_legendLayout,int[] colors) {
        Legend legend = pc_account.getLegend();//设置比例图
        legend.setEnabled(false);//图例不显示

        for (int i = 0; i < accountTypeList.size(); i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.
                    LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;//设置比重为1
            LinearLayout layout = new LinearLayout(context);//单个图例的布局
            layout.setOrientation(LinearLayout.HORIZONTAL);//水平排列
            layout.setGravity(Gravity.CENTER_VERTICAL);//垂直居中
            layout.setLayoutParams(lp);

            //添加color
            LinearLayout.LayoutParams colorLP = new LinearLayout.
                    LayoutParams(20, 20);
            colorLP.setMargins(30, 10, 10, 10);//int left, int top, int right, int bottom
            LinearLayout colorLayout = new LinearLayout(context);
            colorLayout.setLayoutParams(colorLP);
            colorLayout.setBackgroundColor(context.getResources().getColor(colors[i]));
            layout.addView(colorLayout);

            //添加data
            TextView dataTV = new TextView(context);
            dataTV.setText(accountTypeList.get(i) + "");
            dataTV.setTextSize(12f);
            layout.addView(dataTV);
            gl_legendLayout.addView(layout);//legendLayout为外层布局即整个图例布局，是在xml文件中定义
        }
    }
}
