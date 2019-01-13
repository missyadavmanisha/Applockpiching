package com.codingblocks.applock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.takwolf.android.lock9.Lock9View;
/**
 * Created by amitshekhar on 30/04/15.
 */
public class PasswordActivity extends AppCompatActivity {
    Lock9View lock9View;
    SharedPreferences sharedPreferences;
    Context context;
    Button forgetPassword;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_password);

              forgetPassword = (Button) findViewById(R.id.forgetPassword);
        lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor=sharedPreferences.edit();

        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {
                if (sharedPreferences.getString(APPLockConstants.PASSWORD, "").matches(password)) {
                    Toast.makeText(getApplicationContext(), "Success : Password Match", Toast.LENGTH_SHORT).show();
                    editor.putInt(APPLockConstants.NO_OF_TIMES_PASSWORD_FALSE,0);
                    editor.apply();
                    Intent i = new Intent(PasswordActivity.this, LoadingActivity.class);
                    startActivity(i);

                  //  AppLockLogEvents.logEvents(AppLockConstants.PASSWORD_CHECK_SCREEN, "Correct Password", "correct_password", "");
                } else {
                    if(sharedPreferences.getBoolean(APPLockConstants.IS_SWITCH,false)) {
                        int nooftimewrong = sharedPreferences.getInt(APPLockConstants.NO_OF_TIMES_PASSWORD_FALSE, 0);
                        if (nooftimewrong >= 2) {
                            Intent intent = new Intent(getBaseContext(), picturered.class);
                            startActivity(intent);
                            onStop();

                        }


                        editor.putInt(APPLockConstants.NO_OF_TIMES_PASSWORD_FALSE, nooftimewrong + 1);
                        editor.apply();
                    }
                    Toast.makeText(getApplicationContext(), "Wrong Pattern Try Again", Toast.LENGTH_SHORT).show();
                  //  AppLockLogEvents.logEvents(AppLockConstants.PASSWORD_CHECK_SCREEN, "Wrong Password", "wrong_password", "");
                }
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PasswordActivity.this, PasswordRecoveryActivity.class);
                startActivity(i);
               // AppLockLogEvents.logEvents(APPLockConstants.PASSWORD_CHECK_SCREEN, "Forget Password", "forget_password", "");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        GoogleAnalytics.getInstance(context).reportActivityStart(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        GoogleAnalytics.getInstance(context).reportActivityStop(this);
        super.onStop();
        super.onStop();
    }
}
