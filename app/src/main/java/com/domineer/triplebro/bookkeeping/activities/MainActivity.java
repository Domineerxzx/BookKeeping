package com.domineer.triplebro.bookkeeping.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.fragments.AddAccountFragment;
import com.domineer.triplebro.bookkeeping.fragments.BottomFragment;

public class MainActivity extends Activity {

    private FrameLayout fl_top;
    private FrameLayout fl_bottom;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        fl_top = (FrameLayout) findViewById(R.id.fl_top);
        fl_bottom = (FrameLayout) findViewById(R.id.fl_bottom);
    }

    private void initData() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_top,new AddAccountFragment());
        fragmentTransaction.replace(R.id.fl_bottom,new BottomFragment());
        fragmentTransaction.commit();
    }
}
