package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AdminSignIn extends AppCompatActivity {

    EditText username, password;
    ImageButton adminBtn;

    String correct_username = "admin";
    String correct_password = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        username = findViewById(R.id.admin_user);
        password = findViewById(R.id.admin_password);
        adminBtn = findViewById(R.id.admin_proceed);

        Intent intent = new Intent(AdminSignIn.this,AdminInterface.class);


        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate Inputs for Admin

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(AdminSignIn.this,"Invalid Inputs.", Toast.LENGTH_LONG).show();
                } else if (username.getText().toString().equals(correct_username)){
                    if (password.getText().toString().equals(correct_password)) {
                        startActivity(intent);
                        Toast.makeText(AdminSignIn.this,"Successfully logged in!", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(AdminSignIn.this,"Incorrect username or password!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}