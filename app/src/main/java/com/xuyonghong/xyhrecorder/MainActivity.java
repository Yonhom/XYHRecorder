package com.xuyonghong.xyhrecorder;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xuyonghong.xyhrecorder.adapter.RecordPagerAdapter;
import com.xuyonghong.xyhrecorder.fragment.RecordFragment;
import com.xuyonghong.xyhrecorder.fragment.SavedRecrodFragment;
import com.xuyonghong.xyhrecorder.listener.FragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.recorder_view_page)
    ViewPager recorderViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        // set the toolbar you provide as the activity's action bar
        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(Color.WHITE);

        // tab layout
//        for (int i = 0; i < 2; i++) {
//            tabLayout.addTab(tabLayout.newTab());
//        }
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        // integrate with a view pager, the twos movement is integrated together
        // this one line of code replace the above all
        tabLayout.setupWithViewPager(recorderViewPager); // integrate with view pager


        // adapter for the pager
        RecordPagerAdapter adapter = new RecordPagerAdapter(getSupportFragmentManager());
        // record fragment
        RecordFragment recordFragment = new RecordFragment();
        recordFragment.setmListener(this);
        adapter.addFragment(recordFragment);
        // saved record fragment
        SavedRecrodFragment savedRecrodFragment = new SavedRecrodFragment();
        savedRecrodFragment.setmListener(this);
        adapter.addFragment(savedRecrodFragment);
        recorderViewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Fragment fragment, Uri uri) {

    }
}
