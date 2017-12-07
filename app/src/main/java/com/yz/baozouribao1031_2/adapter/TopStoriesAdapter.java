package com.yz.baozouribao1031_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.activity.DetailWebviewActivity;
import com.yz.baozouribao1031_2.bean.FirstPageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class TopStoriesAdapter extends PagerAdapter {

    ImageView imageViewItemTopStories;
    TextView textViewItemTopStories;
    private List<FirstPageBean.TopStoriesBean> mList = null;
    private LayoutInflater mInflater = null;
    private Context mContext = null;

    public TopStoriesAdapter(Context _Context, List<FirstPageBean.TopStoriesBean> _List) {
        this.mContext = _Context;
        this.mList = _List;
        mInflater = (LayoutInflater) mContext.getSystemService(_Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //视图填充器
        View view = mInflater.inflate(R.layout.item_top_stories, container, false);

        textViewItemTopStories = (TextView) view.findViewById(R.id.textView_item_top_stories);
        imageViewItemTopStories = (ImageView) view.findViewById(R.id.imageView_item_top_stories);
        textViewItemTopStories.setText(mList.get(position).getTitle());
        //利用picasso加载网络图片
        Glide.with(mContext).load(mList.get(position).getImage()).into(imageViewItemTopStories);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = mList.get(position).getShare_url();
                Intent intent = new Intent(mContext, DetailWebviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("urlString", urlString);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
//        container.removeView((View) object);
    }
}
