package com.yz.baozouribao1031_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.activity.Channel2Activity;
import com.yz.baozouribao1031_2.bean.Channel2Bean;
import com.yz.baozouribao1031_2.bean.ChannelBean;
import com.yz.baozouribao1031_2.helper.RecyclerViewAdapterHelper;
import com.yz.baozouribao1031_2.util.URLConstants;

import java.util.List;


/**
 * Created by Administrator on 2016/11/2.
 */

public class ChannelRecyclerViewAdapter extends
        RecyclerViewAdapterHelper<ChannelBean.DataBean> {
    public ChannelRecyclerViewAdapter(Context context, List<ChannelBean.DataBean>
            list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item1_recyclerview_home_first_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelBean.DataBean dataBean = mList.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder myHolder = (ViewHolder) holder;
            Glide.with(mContext).load(dataBean.getThumbnail()).into(myHolder
                    .imageViewThumbnailItemRecyclerViewHomeHome);
            myHolder.textViewTitleItemRecyclerViewHomeHome.setText(dataBean.getName());
            myHolder.textViewNameItemRecyclerViewHomeHome.setText(dataBean.getSummary());
        }
        //
        final String finalUrlString = URLConstants.URL_CHANNEL2 + mList.get
                (position).getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Channel2Activity.class);
                intent.putExtra("urlString", finalUrlString);
                mContext.startActivity(intent);
//                Channel2Bean channel2Bean = parseJsonToBean(finalUrlString);
//                List<Channel2Bean.DataBean> mListChannel2 = channel2Bean.getData();
//                Channel2RecyclerViewAdapter channel2Adapter = new Channel2RecyclerViewAdapter(mContext,
//                        mListChannel2);

//                Bundle bundle = new Bundle();
//                bundle.putString("urlString", finalUrlString);
//                Intent intent = new Intent(mContext, DetailWebviewActivity.class);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewThumbnailItemRecyclerViewHomeHome;
        private TextView textViewTitleItemRecyclerViewHomeHome;
        private TextView textViewNameItemRecyclerViewHomeHome;

        public ViewHolder(View view) {
            super(view);
            imageViewThumbnailItemRecyclerViewHomeHome = (ImageView) view.findViewById(R.id
                    .imageView_thumbnail_item_recyclerView_home_home);
            textViewTitleItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_title_item_recyclerView_home_home);
            textViewNameItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_name_item_recyclerView_home_home);
        }
    }

    private Channel2Bean parseJsonToBean(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<Channel2Bean>() {
        }.getType());
    }
}
