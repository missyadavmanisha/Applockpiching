package com.codingblocks.applock;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class FragmentD extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=new Intent();
        i.setAction(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        Toast.makeText(getContext(), "If you have not allowed , allow App Lock so that it can work properly", Toast.LENGTH_LONG).show();

        startActivity(i);
    }
}
