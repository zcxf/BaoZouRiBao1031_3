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
 * Created by Administrator on 2016/11/2.
 */

public class RankFragment extends Fragment {

    private Context mContext = null;
    private TabLayout tabLayoutRank;
    private ViewPager viewPagerRank;

    private String[] mArrTabTitlesRank = null;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
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
        tabLayoutRank = (TabLayout) view.findViewById(R.id.tabLayout_rank);
        viewPagerRank = (ViewPager) view.findViewById(R.id.viewPager_rank);
    }

    private void initData() {
        mArrTabTitlesRank = getResources().getStringArray(R.array.arrTabTitlesRank);
//        if (mList.size() == 0) {
            //添加Fragment到list集合
            RankReadFragment rankReadFragment = new RankReadFragment();
            mList.add(rankReadFragment);
            RankVoteFragment rankVoteFragment = new RankVoteFragment();
            mList.add(rankVoteFragment);
            RankCommentFragment rankCommentFragment = new RankCommentFragment();
            mList.add(rankCommentFragment);
//        }

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getActivity()
                .getSupportFragmentManager(), mList, mArrTabTitlesRank);
        viewPagerRank.setOffscreenPageLimit(3);
        viewPagerRank.setAdapter(adapter);
        tabLayoutRank.setupWithViewPager(viewPagerRank);
    }
}
