package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class HomeFragment extends Fragment {

    private Context mContext = null;
    private String[] mArrTabTitlesHome = null;
    private List<Fragment> mList = new ArrayList<>();
    private TabLayout tabLayoutHome;
    private ViewPager viewPagerHome;
    private MyViewPagerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getContext();
        initFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView(View view) {
        tabLayoutHome = (TabLayout) view.findViewById(R.id.tabLayout_home);
        viewPagerHome = (ViewPager) view.findViewById(R.id.viewPager_home);

    }

    private void initFragment() {
        mArrTabTitlesHome = getResources().getStringArray(R.array.arrTabTitlesHome);
        //添加Fragment到list集合
        HomeFirstPageFragment homeFirstPageFragment = new HomeFirstPageFragment();
        mList.add(homeFirstPageFragment);
        UserContributeFragment userContributeFragment = new UserContributeFragment();
        mList.add(userContributeFragment);
        VideoFragment videoFragment = new VideoFragment();
        mList.add(videoFragment);
    }

    private void initData() {
        adapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), mList,
                mArrTabTitlesHome);
        viewPagerHome.setOffscreenPageLimit(3);
        viewPagerHome.setAdapter(adapter);
        tabLayoutHome.setupWithViewPager(viewPagerHome);
    }

}
