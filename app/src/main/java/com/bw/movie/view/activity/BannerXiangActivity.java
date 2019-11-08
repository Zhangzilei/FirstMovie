package com.bw.movie.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bw.movie.R;

public class BannerXiangActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_xiang);
        initView();

        web.setWebViewClient(new WebViewClient());
        WebSettings settings = web.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);

        Intent intent = getIntent();

        String urls = intent.getStringExtra("url");
        String[] split = urls.split(",");
        for (int i = 0; i < split.length; i++) {
            web.loadUrl(split[i]);
        }

    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
    }
}
