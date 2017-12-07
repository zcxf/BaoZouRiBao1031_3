package com.yz.baozouribao1031_2.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2016/11/3.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
