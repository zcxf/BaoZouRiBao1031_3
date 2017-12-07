package com.yz.baozouribao1031_2.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yz.baozouribao1031_2.R;

public class DetailWebviewActivity extends AppCompatActivity {

    private Context mContext = this;
    private WebView webView_detail;
    private String urlString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_webview);


        initView();
        initData();
    }

    private void initView() {
        webView_detail = (WebView) findViewById(R.id.webView_detail);
    }

    private void initData() {
        urlString = getIntent().getExtras().getString("urlString", "https://www.baidu.com");
        webView_detail.loadUrl(urlString);
        //使用webview打开网页
        webView_detail.setWebViewClient(new WebViewClient());
        //打开带javaScript的网页
        WebSettings settings = webView_detail.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
    /**
     * 改写物理键----返回的逻辑
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView_detail.canGoBack()) {
                webView_detail.goBack();//返回上一页
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
