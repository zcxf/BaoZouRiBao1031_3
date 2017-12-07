package com.yz.baozouribao1031_2.util;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.yz.baozouribao1031_2.helper.OkHttpClientHelper;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initGlide();
    }

    private void initGlide() {
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory
                (OkHttpClientHelper.getOkHttpSingletonInstance()));
    }

}
