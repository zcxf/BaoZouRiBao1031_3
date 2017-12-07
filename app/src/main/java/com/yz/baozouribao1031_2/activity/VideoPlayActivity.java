package com.yz.baozouribao1031_2.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yz.baozouribao1031_2.R;

public class VideoPlayActivity extends AppCompatActivity {

    private Context mContext = this;
    private VideoView videoViewVideoPlay;
    private String videoUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        initView();
        initData();
    }

    private void initData() {
        videoUrl = getIntent().getStringExtra("videoUrl");
        //给videoView添加控制器
        videoViewVideoPlay.setMediaController(new MediaController(mContext));
        //设置视频播放地址
        videoViewVideoPlay.setVideoURI(Uri.parse(videoUrl));
        //让videoView获得焦点
        videoViewVideoPlay.requestFocus();
        //开始播放
        videoViewVideoPlay.start();

    }

    private void initView() {
        videoViewVideoPlay = (VideoView) findViewById(R.id.videoVideo_videoPlay);
    }
}
