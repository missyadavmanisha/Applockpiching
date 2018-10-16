package com.codingblocks.applock;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  //  private Drawer.Result result = null;
    FragmentManager fragmentManager;
    Context context;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    long numOfTimesAppOpened = 0;
    boolean isRated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        numOfTimesAppOpened = sharedPreferences.getLong(APPLockConstants.NUM_OF_TIMES_APP_OPENED, 0) + 1;
        isRated = sharedPreferences.getBoolean(APPLockConstants.IS_RATED, false);
        editor.putLong(APPLockConstants.NUM_OF_TIMES_APP_OPENED, numOfTimesAppOpened);
        editor.apply();
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager=findViewById(R.id.viewpager);

        pagerAdapter pagerAdapter = new pagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout=findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(pagerAdapter);


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if ( (!isRated)&& (numOfTimesAppOpened == 5 || numOfTimesAppOpened == 8 || numOfTimesAppOpened == 10 || numOfTimesAppOpened >= 12)) {
            showRateDialog().show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    EditText et = new EditText(getBaseContext());
                  //  et.setTextAppearance(getBaseContext()); // add some style
                  //  et.setTag();  // add a tag

                 return true;
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rate) {
            showRateDialog().show();
            // Handle the camera action
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_settings) {
            i=new Intent(this,SettingsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            i=new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("https://www.google.com"));
            startActivity(i);

        } else if (id == R.id.nav_contactus) {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            // only email apps should handle this
            String addresses="missyadavmanisha@gmail.com";
            String subject ="Applock";
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT,subject );
         //   intent.putExtra(Intent.EXTRA_REFERRER,"lavishamalik1@gmail.com");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static List<AppInfo> getListOfInstalledApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<AppInfo> installedApps = new ArrayList();
        List<PackageInfo> apps = packageManager.getInstalledPackages(PackageManager.SIGNATURE_MATCH);
        if (apps != null && !apps.isEmpty()) {

            for (int i = 0; i < apps.size(); i++) {
                PackageInfo p = apps.get(i);
                ApplicationInfo appInfo = null;
                try {
                    appInfo = packageManager.getApplicationInfo(p.packageName, 0);
                    AppInfo app = new AppInfo();
                    app.setName(p.applicationInfo.loadLabel(packageManager).toString());
                    app.setPackageName(p.packageName);
                    app.setVersionName(p.versionName);
                    app.setVersionCode(p.versionCode);
                    app.setIcon(p.applicationInfo.loadIcon(packageManager));

                    //check if the application is not an application system
//                    Intent launchIntent = app.getLaunchIntent(context);
//                    if (launchIntent != null && (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                    installedApps.add(app);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            //sort the list of applications alphabetically
//            if (installedApps.size() > 0) {
//                Collections.sort(installedApps, new Comparator() {
//
//                    @Override
//                    public int compare(final AppInfo app1, final AppInfo app2) {
//                        return app1.getName().toLowerCase(Locale.getDefault()).compareTo(app2.getName().toLowerCase(Locale.getDefault()));
//                    }
//                });
//            }
            return installedApps;
        }
        return null;
    }


    public Dialog showRateDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
        WMLP.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(WMLP);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.activity_rate);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
           final Button flatButton = (Button) dialog.findViewById(R.id.button);
       final boolean[] canGoToPlayStore = {false};
        final float[] ratingGivenByUser = {0};


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                flatButton.setVisibility(View.VISIBLE);
                editor.putBoolean(APPLockConstants.IS_RATED, true);
                editor.commit();
                ratingGivenByUser[0] = rating;
                if (rating >= 4) {
                    canGoToPlayStore[0] = true;
                    flatButton.setText("Show your love on playstore");
                } else {
                    canGoToPlayStore[0] = false;
                    flatButton.setText("Thanks for your rating");
                }

            }
        });

        flatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGoToPlayStore[0]) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=applock.mindorks.com.applock");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW);
                    goToMarket.setData(uri);
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goToMarket);
                    dialog.cancel();
                   // AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Going To Playstore To Rate", "going_to_playstore_to_rate", String.valueOf(ratingGivenByUser[0]));
                } else {
                    dialog.cancel();
                }

            }
        });


        return dialog;
    }




}


