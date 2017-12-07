package com.yz.baozouribao1031_2.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.adapter.Channel2RecyclerViewAdapter;
import com.yz.baozouribao1031_2.bean.Channel2Bean;
import com.yz.baozouribao1031_2.decoration.SpacesItemDecoration;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;

import java.io.IOException;
import java.util.List;

public class Channel2Activity extends AppCompatActivity {

    private Context mContext = this;
    private RecyclerView recyclerView_channel2;
    private List<Channel2Bean.DataBean> mList = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            switch (msg.what) {
                case 0:
                    Channel2RecyclerViewAdapter channel2Adapter = new Channel2RecyclerViewAdapter
                            (mContext, mList);
                    recyclerView_channel2.setAdapter(channel2Adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recycler_only);

        initView();

        initData();
    }

    private void initData() {
        final String urlString = getIntent().getStringExtra("urlString");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, urlString, null);
                    mList = parseJaonToBean(json).getData();
                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initView() {
        recyclerView_channel2 = (RecyclerView) findViewById(R.id.recyclerView_fragment_only);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false);
        recyclerView_channel2.setLayoutManager(layoutManager);
        recyclerView_channel2.setHasFixedSize(true);
        recyclerView_channel2.addItemDecoration(new SpacesItemDecoration(20));
    }

    private Channel2Bean parseJaonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<Channel2Bean>() {
        }.getType());
    }
}
