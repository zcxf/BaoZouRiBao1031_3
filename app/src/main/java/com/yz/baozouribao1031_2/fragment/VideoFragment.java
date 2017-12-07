package com.yz.baozouribao1031_2.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.VideoRecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.VideoBean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class VideoFragment extends Fragment {

    private Context mContext = null;
    private RecyclerView videoRecyclerView;
    private List<VideoBean.DataBean> mList = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        loadNetData();
    }


    private void initView(View view) {
        videoRecyclerView = (RecyclerView) view.findViewById(R.id
                .recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        videoRecyclerView.setLayoutManager(layoutManager);
        videoRecyclerView.setHasFixedSize(true);
        videoRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
    }

    private void loadNetData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, URLConstants
                            .URL_VIDEO, null);
                    VideoBean videoBean = parseJsonToBean(json);
                    mList = videoBean.getData();
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //
                            VideoRecyclerViewAdapter videoAdapter = new
                                    VideoRecyclerViewAdapter(mContext, mList);
                            videoRecyclerView.setAdapter(videoAdapter);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private VideoBean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<VideoBean>() {
        }.getType());
    }

}
