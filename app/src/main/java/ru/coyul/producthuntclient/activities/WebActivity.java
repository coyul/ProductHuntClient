package ru.coyul.producthuntclient.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ru.coyul.producthuntclient.R;

public class WebActivity extends AppCompatActivity {

    private WebView mProductWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        setUpWebView();
        mProductWebView.loadUrl(getIntent().getStringExtra("url"));
    }


    public void setUpWebView() {
        mProductWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.web_progress_bar);
        mProductWebView.getSettings().setBuiltInZoomControls(true);
        mProductWebView.getSettings().setDisplayZoomControls(false);
        mProductWebView.getSettings().setJavaScriptEnabled(true);

        mProductWebView.setWebViewClient(new WebViewClient());
        mProductWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }

}
