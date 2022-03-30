package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Calendar;

public class Authority extends AppCompatActivity {

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        dialog = new Dialog(Authority.this);
        dialog.setContentView(R.layout.custom_diag);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }

        ///DIALOG with Okay and Cancel button///
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button okay = dialog.findViewById(R.id.ok);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        ImageButton auth_btn_student = (ImageButton) findViewById(R.id.auth_student);
        auth_btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //variable for the time
                int hourOfDay = 7;
                int closingday = 16;
                //Getting the devices time//
                Calendar calendar = Calendar.getInstance();
                int hr = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                //Comparing the Mobile to to the Set Time 7AM-11:59pm
                if (hr>=hourOfDay && hr<=closingday){
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    c.set(Calendar.MINUTE, closingday);
                    Intent intent = new Intent(Authority.this, Login.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Authority.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
                else{
                    dialog.show();
                }
            }

        });

        ImageButton auth_btn_admin = (ImageButton) findViewById(R.id.auth_admin);
        auth_btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Authority.this, AdminSignIn.class));
            }
        });

        ImageButton skipAsGuest = (ImageButton) findViewById(R.id.home_small);
        skipAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Authority.this, Transaction.class));
            }
        });

    }

}