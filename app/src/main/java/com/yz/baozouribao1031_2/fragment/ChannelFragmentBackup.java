package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.ChannelRecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.ChannelBean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ChannelFragmentBackup extends android.support.v4.app.Fragment {

    private Context mContext = null;
    private RecyclerView channelRecyclerView;
    private List<ChannelBean.DataBean> mList = new ArrayList<>();
    private ChannelBean channelBean = null;
    private PullToRefreshScrollView pullToRefreshScrollViewChannel;
    private ChannelRecyclerViewAdapter channelAdapter;
    private String urlChannel = URLConstants.URL_CHANNEL;
    private int curPage = 1;
    private boolean isClean = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_only_backup, container, false);
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
        channelRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        channelRecyclerView.setLayoutManager(layoutManager);
        channelRecyclerView.setHasFixedSize(true);
        channelRecyclerView.addItemDecoration(new SpacesItemDecoration(15));

        channelAdapter = new ChannelRecyclerViewAdapter(mContext, mList);
        channelRecyclerView.setAdapter(channelAdapter);

        pullToRefreshScrollViewChannel = (PullToRefreshScrollView) view.findViewById(R.id
                .pullToReFreshScrollView);
        pullToRefreshScrollViewChannel.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollViewChannel.setOnRefreshListener(new PullToRefreshBase
                .OnRefreshListener2<ScrollView>() {


            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                isClean = true;
                urlChannel = URLConstants.URL_CHANNEL;
                initData();
                curPage = 1;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                isClean = false;
                int totalPage = channelBean.getTotal_pages();
                if (totalPage > curPage) {
                    curPage++;
                    urlChannel = String.format(URLConstants.URL_CHANNEL_FORMAT, curPage);
                    initData();
                } else {
                    Toast.makeText(mContext, "没有更多了，亲~", Toast.LENGTH_SHORT).show();
                    pullToRefreshScrollViewChannel.onRefreshComplete();
                }
            }
        });

    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, urlChannel, null);
                    channelBean = parseJsonToBean(json);
                    mList = channelBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ChannelRecyclerViewAdapter channelAdapter = new
//                                    ChannelRecyclerViewAdapter(mContext, mList);
                            channelAdapter.reloadRecyclerView(mList, isClean);
                            channelRecyclerView.setAdapter(channelAdapter);
//                            channelRecyclerView.
                            isClean = false;
                            pullToRefreshScrollViewChannel.onRefreshComplete();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ChannelBean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<ChannelBean>() {
        }.getType());
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.choose_day_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}
