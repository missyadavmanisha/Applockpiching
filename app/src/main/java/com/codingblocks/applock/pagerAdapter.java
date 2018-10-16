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
              return  FragmentA .newInstance(APPLockConstants.LOCKED);


            case 2:
                return  FragmentA .newInstance(APPLockConstants.UNLOCKED);


            case 3:
                return new FragmentD();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
     switch (position)
     {
         case 0:
             return "All apps";
         case 1:
             return "Locked apps";
         case 2:
             return "unlock apps";
         case 3:
             return "Settings";
     }
     return "";
    }
}
