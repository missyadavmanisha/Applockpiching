package com.codingblocks.applock;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FragmentC extends Fragment {

    Spinner secuarityspinner;
    Spinner generalspinner;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_protect, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        secuarityspinner=view.findViewById(R.id.sequarityspinner);
        generalspinner=view.findViewById(R.id.spinner);



        List<String> list = new ArrayList<String>();
        list.add("Security");
        list.add("unblock Settings");
        list.add("Sequrity Settings");
     //  list=new String[]{"Security","unblockSettings","SequritySettings"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getContext() , android.R.layout.simple_spinner_item,list);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secuarityspinner.setAdapter(stringArrayAdapter);
        secuarityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), Sequarityclass.class);

                if (position == 1) {

                    i.putExtra("unblocksetting", "unblocksetting");
                    startActivity(i);

                }
                if (position == 2) {
                   i.putExtra("sequaritysetting","sequaritysetting");
                   startActivity(i);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        List<String> list1 = new ArrayList<String>();
        list1.add("general");
        list1.add("power saving mode");
        list1.add("Re-lock after screen on");
        ArrayAdapter<String> stringArrayAdapter1 = new ArrayAdapter<String>( getContext() ,android.R.layout.simple_spinner_item,list1);
        stringArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generalspinner.setAdapter(stringArrayAdapter1);

        generalspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {

               Intent i=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                getContext().startActivity(i);


                }
                if (position == 2) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });



    }

    @Override
    public void onStop() {
        super.onStop();

    }
}


