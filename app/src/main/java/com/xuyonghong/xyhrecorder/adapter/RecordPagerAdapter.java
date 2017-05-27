package com.xuyonghong.xyhrecorder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xuyonghong.xyhrecorder.fragment.RecordFragment;
import com.xuyonghong.xyhrecorder.fragment.SavedRecrodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyonghong on 2017/5/27.
 */

public class RecordPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments = new ArrayList<>();
    List<String> fragmentTitles = new ArrayList<>();

    public RecordPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            fragments.add(fragment);
        }
    }

    /**
     * the title returned by this method will be assigned to the
     * corresponding tablayout indicator
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (fragments.get(position) instanceof RecordFragment)
            return RecordFragment.TITLE;
        if (fragments.get(position) instanceof SavedRecrodFragment)
            return SavedRecrodFragment.TITLE;
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
