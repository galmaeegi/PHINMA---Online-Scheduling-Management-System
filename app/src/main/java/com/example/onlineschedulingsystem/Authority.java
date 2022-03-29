package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Authority extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);

        ImageButton auth_btn_student = (ImageButton) findViewById(R.id.auth_student);
        auth_btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Authority.this, Login.class));
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