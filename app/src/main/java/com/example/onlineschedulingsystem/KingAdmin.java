package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class KingAdmin extends AppCompatActivity {

    ImageButton activate, setslots, summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_king_admin);

        setslots = findViewById(R.id.setslots_btn);
        summary = findViewById(R.id.summary_btn);


        Intent intent2 = new Intent(KingAdmin.this,SetSlots.class);
        Intent intent3 = new Intent(KingAdmin.this,Summary_interface.class);

        setslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });

        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button3);

        smallSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(KingAdmin.this,AdminSignIn.class));

            }
        });





    }

    @Override
    public void onBackPressed() {
    }


}