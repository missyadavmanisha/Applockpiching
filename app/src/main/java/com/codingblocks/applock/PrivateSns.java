package com.codingblocks.applock;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PrivateSns extends AppCompatActivity {

    ArrayList<sns_list>sns_lists=new ArrayList<>();
    @Override
    protected void onStop() {
        super.onStop();
        setContentView(R.layout.chamber_xml) ;
        Resources resources=getResources();
        Drawable drawable=resources.getDrawable(R.drawable.twitter);
        Drawable drawable1=resources.getDrawable(R.drawable.linkdien);
        Drawable drawable2=resources.getDrawable(R.drawable.google);
        Drawable drawable3=resources.getDrawable(R.drawable.facebook);
        sns_lists.add(new sns_list(drawable,"Twitter"));
        sns_lists.add(new sns_list(drawable3,"Facebook"));
        sns_lists.add(new sns_list(drawable2,"Google+"));
        sns_lists.add(new sns_list(drawable1,"Linkedin"));

        ListView listView=findViewById(R.id.list_view);
        snsAdapter snsAdapter=new snsAdapter(sns_lists,this);
        listView.setAdapter(snsAdapter) ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i;
                if(position==0)
                {
                i=  new Intent(PrivateSns.this,private_sns_click.class);
                 i.putExtra("url","https://www.twitter.com");
                  startActivity(i);
                }
                if(position==1)
                {
                    i=  new Intent(PrivateSns.this,private_sns_click.class);
                    i.putExtra("url","https://www.facebook.com");
                    startActivity(i);
                }
                if(position==2)
                {
                    i=  new Intent(PrivateSns.this,private_sns_click.class);
                    i.putExtra("url","https://www..com");
                    startActivity(i);
                }
                if(position==3)
                {
                    i=  new Intent(PrivateSns.this,private_sns_click.class);
                    i.putExtra("url","https://www.linkedin.com");
                    startActivity(i);
                }

            }
        });



    }
}
