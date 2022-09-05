package com.example.pakistanpoultry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://par.com.pk/news/category/poultry");
        browser.clearCache(true);
        browser.clearHistory();
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

    }
}
