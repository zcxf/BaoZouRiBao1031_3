package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.RankRecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.RankBean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class RankCommentFragment extends android.support.v4.app.Fragment {

    private Context mContext = null;
    private RecyclerView rankCommentRecycler;
    private List<RankBean.DataBean> mlist = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    RankRecyclerViewAdapter rankAdapter = new RankRecyclerViewAdapter(mContext,
                            mlist);
                    rankCommentRecycler.setAdapter(rankAdapter);
                    break;
            }
        }
    };

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
        loadNetData();
    }

    private void initView(View view) {
        rankCommentRecycler = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        rankCommentRecycler.setLayoutManager(layoutManager);
        rankCommentRecycler.setHasFixedSize(true);
        rankCommentRecycler.addItemDecoration(new SpacesItemDecoration(20));
    }

    private void loadNetData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, URLConstants
                            .URL_RANK_READ + "comment/day", null);
                    RankBean rankBean = parsejsonToBean(json);
                    mlist = rankBean.getData();
                    handler.sendEmptyMessage(0);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            RankRecyclerViewAdapter rankAdapter = new RankRecyclerViewAdapter
//                                    (mContext, mlist);
//                            rankCommentRecycler.setAdapter(rankAdapter);
//                        }
//                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private RankBean parsejsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<RankBean>() {
        }.getType());
    }
}
