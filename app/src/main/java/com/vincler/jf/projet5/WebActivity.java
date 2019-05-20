package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private static final String KEY_URL_WEBVIEW = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        String url = args.getString(KEY_URL_WEBVIEW);


        WebView webView = findViewById(R.id.activity_webview_webview);


        Log.i("TAG_URL ", url);
        webView.loadUrl(url);


    }
}
