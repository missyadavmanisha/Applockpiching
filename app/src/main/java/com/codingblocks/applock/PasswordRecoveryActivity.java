package com.codingblocks.applock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by amitshekhar on 02/05/15.
 */
public class PasswordRecoveryActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    Spinner questionsSpinner;
    EditText answer;
    Button confirmButton;
    int questionNumber = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_recovery_password);
        //Google Analytics
        Tracker t = ((AppLockApplication) getApplication()).getTracker(AppLockApplication.TrackerName.APP_TRACKER);
        t.setScreenName(APPLockConstants.PASSWORD_RECOVERY_SCREEN);
        t.send(new HitBuilders.AppViewBuilder().build());

        confirmButton = (Button) findViewById(R.id.confirmButton);
        questionsSpinner = (Spinner) findViewById(R.id.questionsSpinner);
        answer = (EditText) findViewById(R.id.answer);

        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();


        List<String> list = new ArrayList<String>();
        list.add("Select your security question?");
        list.add("What is your pet name?");
        list.add("Who is your favorite teacher?");
        list.add("Who is your favorite actor?");
        list.add("Who is your favorite actress?");
        list.add("Who is your favorite cricketer?");
        list.add("Who is your favorite footballer?");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionsSpinner.setAdapter(stringArrayAdapter);

        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                questionNumber = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber != 0 && !answer.getText().toString().isEmpty()) {
                    if (sharedPreferences.getInt(APPLockConstants.QUESTION_NUMBER, 0) == questionNumber && sharedPreferences.getString(APPLockConstants.ANSWER, "").matches(answer.getText().toString())) {
                        editor.putBoolean(APPLockConstants.IS_PASSWORD_SET, false);
                        editor.commit();
                        editor.putString(APPLockConstants.PASSWORD, "");
                        editor.apply();
                        Intent i = new Intent(PasswordRecoveryActivity.this, PasswordSetActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your question and answer didn't matches", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please select a question and write an answer", Toast.LENGTH_SHORT).show();
                }
             //   AppLockLogEvents.logEvents(AppLockConstants.PASSWORD_RECOVER_SET_SCREEN, "Recover", "recover", "");

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
