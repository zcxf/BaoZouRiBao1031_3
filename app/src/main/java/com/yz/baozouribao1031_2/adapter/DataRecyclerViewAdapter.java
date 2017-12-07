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
import com.yz.baozouribao1031_2.bean.FirstPageBean;
import com.yz.baozouribao1031_2.helper.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class DataRecyclerViewAdapter extends RecyclerViewAdapterHelper<FirstPageBean.DataBean> {

    private static final int STATE1 = 0, STATE2 = 1;

    public DataRecyclerViewAdapter(Context context, List<FirstPageBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getDisplay_type() == 1) {
            return STATE1;
        }
        return STATE2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case STATE1:
                view = mInflater.inflate(R.layout.item1_recyclerview_home_first_page, parent, false);
                return new ViewHolder1(view);
            case STATE2:
                view = mInflater.inflate(R.layout.item2_recyclerview_home_first_page, parent, false);
                return new ViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindMyViewHolder(final RecyclerView.ViewHolder holder, int position) {
        FirstPageBean.DataBean dataBean = mList.get(position);
        int displayType= dataBean.getDisplay_type();
        String urlString = "";
        if (holder instanceof ViewHolder1) {
            ViewHolder1 holder1 = (ViewHolder1) holder;

            Glide.with(mContext).load(dataBean.getThumbnail()).into(holder1
                    .imageViewThumbnailItemRecyclerViewHomeHome);
            holder1.textViewTitleItemRecyclerViewHomeHome.setText(dataBean.getTitle());
            holder1.textViewNameItemRecyclerViewHomeHome.setText(dataBean.getAuthor_name());
            urlString = mList.get(position).getShare_url();
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            Glide.with(mContext).load(dataBean.getThumbnail()).into(holder2
                    .imageViewThumbnailItemRecyclerViewHomeHome);
            Glide.with(mContext).load(dataBean.getAuthor_avatar()).into(holder2
                    .imageViewAuthorAvatarItemRecyclerViewHomeHome);
            holder2.textViewTitleItemRecyclerViewHomeHome.setText(dataBean.getTitle());
            holder2.textViewAuthorNameItemRecyclerViewHomeHome.setText(dataBean.getAuthor_name());
            urlString = mList.get(position).getUrl();
        }
        final String finalUrlString = urlString;
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

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private ImageView imageViewThumbnailItemRecyclerViewHomeHome;
        private TextView textViewTitleItemRecyclerViewHomeHome;
        private TextView textViewNameItemRecyclerViewHomeHome;

        public ViewHolder1(View view) {
            super(view);
            imageViewThumbnailItemRecyclerViewHomeHome = (ImageView) view.findViewById(R.id
                    .imageView_thumbnail_item_recyclerView_home_home);
            textViewTitleItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_title_item_recyclerView_home_home);
            textViewNameItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_name_item_recyclerView_home_home);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        private ImageView imageViewThumbnailItemRecyclerViewHomeHome;
        private TextView textViewTitleItemRecyclerViewHomeHome;
        private ImageView imageViewAuthorAvatarItemRecyclerViewHomeHome;
        private TextView textViewAuthorNameItemRecyclerViewHomeHome;

        public ViewHolder2(View view) {
            super(view);
            imageViewThumbnailItemRecyclerViewHomeHome = (ImageView) view.findViewById(R.id
                    .imageView_thumbnail_item_recyclerView_home_home);
            textViewTitleItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_title_item_recyclerView_home_home);
            imageViewAuthorAvatarItemRecyclerViewHomeHome = (ImageView) view.findViewById(R.id
                    .imageView_author_avatar_item_recyclerView_home_home);
            textViewAuthorNameItemRecyclerViewHomeHome = (TextView) view.findViewById(R.id
                    .textView_authorName_item_recyclerView_home_home);
        }
    }
}
