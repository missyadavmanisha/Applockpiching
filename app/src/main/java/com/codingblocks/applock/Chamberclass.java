package com.codingblocks.applock;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Chamberclass extends AppCompatActivity {
    ArrayList<chamber_value> chamber_values=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chamber) ;
        Resources res = getResources();

        Drawable drawable=res.getDrawable(R.drawable.broser);
        Drawable drawable1=res.getDrawable(R.drawable.chaticon);
        Drawable drawable2=res.getDrawable(R.drawable.intruder);

        chamber_values.add(new chamber_value( drawable,"Incogito browser","No browser history,keep safe"));
        chamber_values.add(new chamber_value(drawable1,"Private SNS","Manage multiple private accounts"));
        chamber_values.add(new chamber_value(drawable2,"Intruder selfie","Take a photo of intruder"));


       ListView listView = findViewById(R.id.list_view);

        chamberAdapter  superheroAdapter = new chamberAdapter(chamber_values, this);

        listView.setAdapter(superheroAdapter);
        // chamber_value chamberValue1=  (chamber_value) listView.getItemAtPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent i=new Intent(Chamberclass.this,webview.class);
                    startActivity(i);
                }
                if(position==1)
                {
                Intent intent=new Intent(Chamberclass.this,PrivateSns.class);
                startActivity(intent);
                }
                if(position==2)
                {
                   Intent intent=new Intent(Chamberclass.this,intruderselfie.class);
                  startActivity(intent);

                }


            }
        });




    }
}
