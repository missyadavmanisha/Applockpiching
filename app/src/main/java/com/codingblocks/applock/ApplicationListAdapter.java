package com.codingblocks.applock;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class ApplicationListAdapter extends RecyclerView .Adapter<RecyclerView .ViewHolder> {

    List<AppInfo> installedApps = new ArrayList();
    private Context context;
    SharedPreferences sharedPreference;
    String requiredAppsType;

    public ApplicationListAdapter(List<AppInfo> appInfoList, Context context, String requiredAppsType) {
        installedApps = appInfoList;
        this.context = context;
        this.requiredAppsType = requiredAppsType;
        sharedPreference = context.getSharedPreferences("mypref", MODE_PRIVATE);

        List<AppInfo> lockedFilteredAppList = new ArrayList<AppInfo>();
        List<AppInfo> unlockedFilteredAppList = new ArrayList<AppInfo>();
        boolean flag = true;
        if (requiredAppsType.matches(APPLockConstants.LOCKED) || requiredAppsType.matches(APPLockConstants.UNLOCKED)) {
            for (int i = 0; i < installedApps.size(); i++) {
                flag = true;
                if (sharedPreference.getString(APPLockConstants.LOCKED,null)!=null) {
                    for (int j = 0; j < sharedPreference.getStringSet(APPLockConstants.LOCKED,null).size(); j++) {
                        List<String> sd=(List<String >)sharedPreference.getStringSet(APPLockConstants.LOCKED,null);

                        if (installedApps.get(i).getPackageName().matches(sd.get(j)))
                                {
                                lockedFilteredAppList.add(installedApps.get(i));
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    unlockedFilteredAppList.add(installedApps.get(i));
                }
            }
           /* if (requiredAppsType.matches(APPLockConstants.LOCKED)) {
                installedApps.clear();
                installedApps.addAll(lockedFilteredAppList);
            } else if (requiredAppsType.matches(APPLockConstants.UNLOCKED)) {
                installedApps.clear();
                installedApps.addAll(unlockedFilteredAppList);
            }*/
        }
    }

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView applicationName;
        public CardView cardView;
        public ImageView icon;
        public Switch switchView;

        public ViewHolder(View v) {
            super(v);
            applicationName = (TextView) v.findViewById(R.id.applicationName);
            cardView = (CardView) v.findViewById(R.id.card_view);
            icon = (ImageView) v.findViewById(R.id.icon);
            switchView = (Switch) v.findViewById(R.id.switchView);
        }
    }


    @Override
    public ApplicationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final AppInfo appInfo = installedApps.get(i);
        ViewHolder viewHolder1=(ViewHolder) viewHolder;
        viewHolder1.applicationName.setText(appInfo.getName());
        viewHolder1.icon.setBackgroundDrawable(appInfo.getIcon());

        viewHolder1.switchView.setOnCheckedChangeListener(null);
        viewHolder1.cardView.setOnClickListener(null);
      /*  if (checkLockedItem(appInfo.getPackageName())) {
            viewHolder1.switchView.setChecked(true);
        } else {
       viewHolder1.switchView.setChecked(false);
        }*/

        if (checkLockedItem(appInfo.getPackageName())) {
         viewHolder1.switchView.setChecked(true);
        } else {
           viewHolder1.switchView.setChecked(false);
        }

     viewHolder1.switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor= sharedPreference.edit();
                if (isChecked) {
                 //   AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Lock Clicked", "lock_clicked", appInfo.getPackageName());

                   editor.putString( APPLockConstants.LOCKED,appInfo.getPackageName());
                } else {
                    editor.putString(APPLockConstants.UNLOCKED,appInfo.getPackageName());
                  //  AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Unlock Clicked", "unlock_clicked", appInfo.getPackageName());
                }
            }
        });





    }

    /*Checks whether a particular app exists in SharedPreferences*/
    public boolean checkLockedItem(String checkApp) {
        boolean check = false;
        List<String> locked = (List<String >)sharedPreference.getStringSet(APPLockConstants.LOCKED,null) ;
        if (locked != null) {
            for (String lock : locked) {
                if (lock.equals(checkApp)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    // Replace the contents of a view (invoked by the layout manager)




        @Override
        public int getItemCount () {
            return installedApps.size();
        }



}
