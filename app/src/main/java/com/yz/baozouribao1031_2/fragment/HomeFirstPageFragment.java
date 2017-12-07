package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.activity.DetailWebviewActivity;
import com.yz.baozouribao1031_2.adapter.DataRecyclerViewAdapter;
import com.yz.baozouribao1031_2.adapter.TopStoriesAdapter;
import com.yz.baozouribao1031_2.bean.FirstPageBean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.GlideImageLoader;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class HomeFirstPageFragment extends Fragment {
    private Context mContext = null;
    //private PullToRefreshScrollView pullToRefreshScrollView;

    private FirstPageBean firstPageBean;
    private Banner topStoriesBanner;
    private RecyclerView dataRecyclerView;

    private TopStoriesAdapter topStoriesAdapter;
    private DataRecyclerViewAdapter dataAdapter;

    private List<FirstPageBean.TopStoriesBean> mListTopStories = new ArrayList<>();
    private List<FirstPageBean.DataBean> mListData = new ArrayList<>();
    //banner的图片和标题的集合
    private List<String> mListImageUrls = new ArrayList<>();
    private List<String> mListTitles = new ArrayList<>();

    //当前加载的页数
    private int curPage = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //设置banner图片集合
                    topStoriesBanner.setImages(mListImageUrls);
                    //设置banner标题集合（当banner样式有显示title时）
                    topStoriesBanner.setBannerTitles(mListTitles);
                    topStoriesBanner.start();
                    topStoriesBanner.setOnBannerClickListener(new OnBannerClickListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Intent intent = new Intent(mContext, DetailWebviewActivity.class);
                            intent.putExtra("urlString", mListTopStories.get(position - 1)
                                    .getShare_url());
                            startActivity(intent);
                        }
                    });
                    //给dataRecyclerView设置适配器
                    dataAdapter = new DataRecyclerViewAdapter(mContext, mListData);
                    dataRecyclerView.setAdapter(dataAdapter);
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_first_page, container, false);
//        pullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id
//                .pullToRefreshScrollView_home);
//        topStoriesViewPagerFragmentHomeHome = (ViewPager) view.findViewById(R.id
//                .topStories_viewPager_fragment_home_home);
        topStoriesBanner = (Banner) view.findViewById(R.id.topStories_banner);
        //设置banner样式
        topStoriesBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        topStoriesBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        topStoriesBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        topStoriesBanner.isAutoPlay(true);
        //设置轮播时间
        topStoriesBanner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        topStoriesBanner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        dataRecyclerView = (RecyclerView) view.findViewById(R.id
                .data_recyclerView_fragment_home_home);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadNetData();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public FirstPageBean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<FirstPageBean>() {
        }.getType());
    }


    private void initView() {
//        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
//        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase
//                .OnRefreshListener2<ScrollView>() {
//
//
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                curPage = 1;
//                loadNetData();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                curPage++;
//                loadNetData();
//            }
//        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        dataRecyclerView.setLayoutManager(layoutManager);
        dataRecyclerView.setHasFixedSize(true);
        dataRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
    }

    private void loadNetData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, URLConstants
                            .URL_HOME, null);
                    firstPageBean = parseJsonToBean(json);

                    mListTopStories = firstPageBean.getTop_stories();
                    mListData = firstPageBean.getData();
                    for (FirstPageBean.TopStoriesBean dataBean : mListTopStories) {
                        mListImageUrls.add(dataBean.getImage());
                        mListTitles.add(dataBean.getTitle());
                    }
                    handler.sendEmptyMessage(0);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //给topStories设置适配器
////                            topStoriesAdapter = new TopStoriesAdapter(mContext,
// mListTopStories);
////                            topStoriesViewPagerFragmentHomeHome.setAdapter(topStoriesAdapter);
//                            //设置banner图片集合
//                            topStoriesBanner.setImages(mListImageUrls);
//                            //设置banner标题集合（当banner样式有显示title时）
//                            topStoriesBanner.setBannerTitles(mListTitles);
//                            topStoriesBanner.start();
//                            topStoriesBanner.setOnBannerClickListener(new OnBannerClickListener
// () {
//                                @Override
//                                public void OnBannerClick(int position) {
//                                    Intent intent = new Intent(mContext, DetailWebviewActivity
//                                            .class);
//                                    intent.putExtra("urlString", mListTopStories.get(position -
//                                            1).getShare_url());
//                                    startActivity(intent);
//                                }
//                            });
//                            //给dataRecyclerView设置适配器
//                            dataAdapter = new DataRecyclerViewAdapter(mContext, mListData);
//                            dataRecyclerView.setAdapter(dataAdapter);
////                            pullToRefreshScrollView.onRefreshComplete();
//                        }
//                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Util.isOnMainThread()) {
            Glide.with(mContext).pauseRequests();
        }
    }
}
