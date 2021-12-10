package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nepse_brokersettlement.R;

public class WebsiteView extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    TextView loading;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webView = (WebView) findViewById(R.id.webview);
        loading = (TextView) findViewById(R.id.loading);
        progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();

        toolbar.setTitle(intent.getStringExtra("name"));
        setSupportActionBar(toolbar);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(intent.getStringExtra("website"));

    }
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.setVisibility(View.VISIBLE);
            loading.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}