package com.codingblocks.applock;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {

    public pagerAdapter (FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
             return FragmentA .newInstance(APPLockConstants.ALL_APPS);


            case 1:
                return new FragmentC();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
     switch (position)
     {
         case 0:
             return "PRIVICY";
         case 1:
             return "PROTECT";
     }
     return "";
    }
}
