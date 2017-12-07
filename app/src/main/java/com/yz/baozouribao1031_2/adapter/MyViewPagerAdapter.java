package com.yz.baozouribao1031_2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList = null;
    private String[] mArrTitles = null;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> _list, String[] _arrTitles) {
        super(fm);
        this.mList = _list;
        this.mArrTitles = _arrTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mArrTitles[position];
    }
}
