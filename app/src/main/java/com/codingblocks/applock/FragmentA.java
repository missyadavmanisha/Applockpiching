package com.codingblocks.applock;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment {
    static String requiredAppsType;
    Button vault,Chamber,batterysaving,mare;

    public static FragmentA newInstance(String requiredApps) {
        requiredAppsType = requiredApps;
        FragmentA f = new FragmentA();
        return (f);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_a, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vault=view.findViewById(R.id.button_vault);
        Chamber =view.findViewById(R.id.button_chamber);
        batterysaving=view.findViewById(R.id.button_batterysaving);
        mare=view.findViewById(R.id.button_more);
        vault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Vault.class);
                startActivity(i);
            }
        });
        Chamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Chamberclass.class);
                startActivity(i);
            }
        });

        batterysaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       Intent i=new Intent();
       i.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
       startActivity(i);
            }
        });

        mare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),MoreActivity.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApplicationListAdapter hetrogeneousAdapter = new ApplicationListAdapter(((MainActivity) getActivity()).getListOfInstalledApp(getActivity()), (AppCompatActivity) getActivity(), requiredAppsType);

        recyclerView.setAdapter(hetrogeneousAdapter);


    }

}
