package com.example.tastybites;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Webview extends AppCompatActivity {

    private WebView mWebView;
    private boolean backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        int tblId = intent.getIntExtra("TABLEID", 0);
        Log.d("message", String.valueOf(tblId)+" peyechi ");
        //make url
        final String URL = "http://192.168.1.10:8000/tab/"+tblId;

        //webview
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadUrl(URL);



    }

    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {

            mWebView.goBack();
            //mWebView.reload();

        } else {
            if (backPressed) {
                super.onBackPressed();
                return;
            }

            this.backPressed = true;
            Toast.makeText(this, "Press Back One More Time To Exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressed = false;
                }
            }, 2000);

        }

    }
}
