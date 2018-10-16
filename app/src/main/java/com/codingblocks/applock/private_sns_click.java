package com.codingblocks.applock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class private_sns_click extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String url1=intent.getStringExtra("url");
        Intent i=new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url1));
        startActivity(i);
    }
}
