package com.xuyonghong.xyhrecorder;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xuyonghong.xyhrecorder.adapter.RecordPagerAdapter;
import com.xuyonghong.xyhrecorder.fragment.RecordFragment;
import com.xuyonghong.xyhrecorder.fragment.SavedRecrodFragment;
import com.xuyonghong.xyhrecorder.listener.FragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener {
    @BindView(R.id.pager_strip)
    PagerTabStrip tabStrip;
    @BindView(R.id.recorder_view_page)
    ViewPager recorderViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
