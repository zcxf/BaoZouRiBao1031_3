package com.yz.baozouribao1031_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.SearchRecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.SearchBean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class SearchFragment extends Fragment {

    private Context mContext;
    private Toolbar toolbar;
    private RecyclerView searchRecyclerView;
    private SearchView searchViewSearch;

    private List<SearchBean.DataBean> mListSearchs;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    SearchRecyclerViewAdapter searchAdapter = new SearchRecyclerViewAdapter
                            (mContext, mListSearchs);
                    searchRecyclerView.setAdapter(searchAdapter);
                    break;
            }
        }
    };

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

    private void initView(View view) {
        searchRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
        searchViewSearch = (SearchView) getActivity().findViewById(R.id.searchView_search);
        searchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                postOkHttp(URLConstants.URL_SEARCH, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void postOkHttp(String path, String searchString) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("page", "1").add("keyword",
                searchString).build();

        Request request = new Request.Builder().url(path).post(formBody).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                mListSearchs = parseJsonToBean(json).getData();
                handler.sendEmptyMessage(0);
            }
        });
    }

    private SearchBean parseJsonToBean(String jsonStirng) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStirng, new TypeToken<SearchBean>() {
        }.getType());
    }
}
