package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private static final String KEY_URL_WEBVIEW = "url";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        configureToolbar();
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        String url = args.getString(KEY_URL_WEBVIEW);


        WebView webView = findViewById(R.id.activity_webview_webview);
        webView.loadUrl(url);


    }

    private void configureToolbar() {

        this.toolbar = (findViewById(R.id.webview_toolbar));
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.article);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
