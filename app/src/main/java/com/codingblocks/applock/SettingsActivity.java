package com.codingblocks.applock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<Settingslist> sns_lists=new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch aSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chamber) ;
        sharedPreferences=getSharedPreferences(APPLockConstants.MyPREFERENCES,MODE_PRIVATE);
        aSwitch=findViewById(R.id.switchView_setting);
        Resources resources=getResources();
        Drawable drawable=resources.getDrawable(R.drawable.ic_action_language);
        Drawable drawable1=resources.getDrawable(R.drawable.ic_lock_convience);
        Drawable drawable2=resources.getDrawable(R.drawable.ic_lock_new_app);
        Drawable drawable3=resources.getDrawable(R.drawable.ic_profiles);
        sns_lists.add(new Settingslist(drawable2,"Lock the new app","After you installed the new app,you can one tap to lock the app."));

        sns_lists.add(new Settingslist(drawable3,"Profiles","you can preset locked app groups.so can change profile more fast and easily for each place and situation"));
        sns_lists.add(new Settingslist(drawable,"Choose your language","System language"));
        sns_lists.add(new Settingslist(drawable1,"Lock Convience","Lock Convience Feature"));

        ListView listView=findViewById(R.id.list_view);
       SettingsAdapter settingsAdapter =new SettingsAdapter(sns_lists,this);
        listView.setAdapter(settingsAdapter) ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor=sharedPreferences.edit();
                Intent i;
                if(position==0)
                {
                   aSwitch.performClick();
                   if(aSwitch.isChecked())
                   {
                    editor.putBoolean(APPLockConstants.IS_LOCK_NEW_APP,true);
                    editor.apply();
                   }

                }
                if(position==1)
                {
                    i=  new Intent(SettingsActivity.this,PrivateSns.class);

                    startActivity(i);
                }
                if(position==2)
                {
                    i=  new Intent(SettingsActivity.this,PrivateSns.class);

                    startActivity(i);
                }
                if(position==3)
                {
                    i=  new Intent(SettingsActivity.this,PrivateSns.class);

                    startActivity(i);
                }

            }
        });



    }
}
