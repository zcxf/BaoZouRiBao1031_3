package com.yz.baozouribao1031_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.activity.DetailWebviewActivity;
import com.yz.baozouribao1031_2.bean.SearchBean;
import com.yz.baozouribao1031_2.helper.RecyclerViewAdapterHelper;

import java.util.List;


/**
 * Created by Administrator on 2016/11/2.
 */

public class SearchRecyclerViewAdapter extends RecyclerViewAdapterHelper<SearchBean.DataBean> {
    public SearchRecyclerViewAdapter(Context context, List<SearchBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item1_recyclerview_home_first_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchBean.DataBean dataBean = mList.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder myHolder = (ViewHolder) holder;
            Glide.with(mContext).load(dataBean.getThumbnail()).into(myHolder
                    .imageViewThumbnailItemRecyclerViewHomeHome);
            myHolder.textViewTitleItemRecyclerViewHomeHome.setText(dataBean.getTitle());
            myHolder.textViewNameItemRecyclerViewHomeHome.setText(dataBean.getAuthor_name() + " |" +
                    " " + dataBean.getSection_name());
        }
        //
        final String finalUrlString = mList.get(position).getShare_url();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("urlString", finalUrlString);
                Intent intent = new Intent(mContext, DetailWebviewActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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
}
