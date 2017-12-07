package com.yz.baozouribao1031_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.activity.VideoPlayActivity;
import com.yz.baozouribao1031_2.bean.VideoBean;
import com.yz.baozouribao1031_2.helper.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class VideoRecyclerViewAdapter extends
        RecyclerViewAdapterHelper<VideoBean.DataBean> {
    public VideoRecyclerViewAdapter(Context context, List<VideoBean.DataBean>
            list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_video_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoBean.DataBean dataBean = mList.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder myHolder = (ViewHolder) holder;
            Glide.with(mContext).load(dataBean.getImage()).into(myHolder
                    .imageViewImage);
            myHolder.textViewTitle.setText(dataBean.getTitle());
            myHolder.textViewPlayCount.setText(dataBean.getPlay_count_string() + " 播放");
        }

        //传递视频播放mp4地址
        final String videoUrlString = mList.get(position).getFile_url();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("videoUrl", videoUrlString);
                mContext.startActivity(intent);
            }
        });

        //传递视频播放网页
//        final String finalUrlString = mList.get(position).getShare_url();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("urlString", finalUrlString);
//                Intent intent = new Intent(mContext, DetailWebviewActivity.class);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
//            }
//        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewImage;
        private TextView textViewTitle;
        private TextView textViewPlayCount;

        public ViewHolder(View view) {
            super(view);
            imageViewImage = (ImageView) view.findViewById(R.id
                    .imageView_thumbnail);
            textViewTitle = (TextView) view.findViewById(R.id
                    .textView_title);
            textViewPlayCount = (TextView) view.findViewById(R.id
                    .textView_play_count);
        }
    }
}
