package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.Channel2RecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.Channel2Bean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Channel2Fragment extends android.support.v4.app.Fragment {

    private Context mContext = null;
    private RecyclerView channel2RecyclerView;
    private List<Channel2Bean.DataBean> mList = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_only, container, false);
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
        channel2RecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        channel2RecyclerView.setLayoutManager(layoutManager);
        channel2RecyclerView.setHasFixedSize(true);
        channel2RecyclerView.addItemDecoration(new SpacesItemDecoration(15));
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // TODO 增加id
                    String json = OkHttpClientHelper.getStringFromURL(mContext, URLConstants
                            .URL_CHANNEL2 + "id", null);
                    Channel2Bean channelBean = parseJsonToBean(json);
                    mList = channelBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Channel2RecyclerViewAdapter channelAdapter = new
                                    Channel2RecyclerViewAdapter(mContext, mList);
                            channel2RecyclerView.setAdapter(channelAdapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Channel2Bean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<Channel2Bean>() {
        }.getType());
    }
}
