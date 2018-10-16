package com.codingblocks.applock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class webview extends AppCompatActivity {
    EditText editText;
    ImageButton  button;
    WebView wv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view) ;
        editText=findViewById(R.id.web_edit);
        button=findViewById(R.id.web_button);
        wv=findViewById(R.id.web_view);
       String url1=editText.getText().toString();
       final String url="https://www.google.com/search?q="+""+url1;
        Log.e("TAG",url);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wv.setWebViewClient(new MyBrowser());

                wv.getSettings().setLoadsImagesAutomatically(true);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv.loadUrl(url);


            }
        });

    }

private class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
}
