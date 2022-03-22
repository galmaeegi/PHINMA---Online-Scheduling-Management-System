package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {

    ImageButton reset;
    EditText resetText;
    Integer resetNum = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        resetText = findViewById(R.id.reset_input);
        reset = findViewById(R.id.reset_button);

        Intent intent = new Intent(Reset.this,AdminInterface.class);


        /*Reset Button On Click*/
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(resetText.getText().toString())) {
                    Toast.makeText(Reset.this,"Invalid Input!", Toast.LENGTH_LONG).show();
                }
                /*RALPH DITO KA MAGLAGAY NG METHODS.. IF RESET PASSWORD IS CORRECT*/
                else if (resetText.getText().toString().equals(resetNum.toString())) {
                    AdminInterface admin = new AdminInterface();
                    admin.resetCounter();
                    Toast.makeText(Reset.this,"You reset the queue!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Reset.this,"Invalid username or password!", Toast.LENGTH_LONG).show();
                }

            }
        });

        /*Sign out*/
        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button2);

        smallSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Reset.this,AdminSignIn.class));

            }
        });






    }
}