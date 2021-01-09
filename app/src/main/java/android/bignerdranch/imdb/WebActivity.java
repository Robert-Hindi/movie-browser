package android.bignerdranch.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url;
        Intent intent = getIntent();

        if(intent.getStringExtra("url") != null) {
            url = intent.getStringExtra("url");
        } else {
            url = "http://www.imdb.com/search/title/?genres="
                    + intent.getStringExtra("genre");
        }

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(url);
    }
}