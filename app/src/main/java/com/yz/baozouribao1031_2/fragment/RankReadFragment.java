package com.yz.baozouribao1031_2.fragment;

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

public class RankReadFragment extends Fragment {

    private Context mContext = null;
    private RecyclerView rankReadRecyclerView;
    private List<RankBean.DataBean> mList = null;

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
        rankReadRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        rankReadRecyclerView.setLayoutManager(layoutManager);
        rankReadRecyclerView.setHasFixedSize(true);
        rankReadRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
    }

    private void loadNetData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, URLConstants
                            .URL_RANK_READ + "read/day", null);
                    final RankBean rankBean = parseJsonToBean(json);
                    mList = rankBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RankRecyclerViewAdapter rankAdapter = new RankRecyclerViewAdapter
                                    (mContext, mList);
                            rankReadRecyclerView.setAdapter(rankAdapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private RankBean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<RankBean>() {
        }.getType());
    }
}
