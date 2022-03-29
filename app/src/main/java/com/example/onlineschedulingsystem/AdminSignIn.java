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

    String correct_username1 = "admin1";
    String correct_password1 = "admin1";

    String correct_username2 = "admin2";
    String correct_password2 = "admin2";

    String correct_username3 = "admin3";
    String correct_password3 = "admin3";

    String correct_username4 = "1";
    String correct_password4 = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        username = findViewById(R.id.admin_user);
        password = findViewById(R.id.admin_password);
        adminBtn = findViewById(R.id.admin_proceed);



        Intent intent1 = new Intent(AdminSignIn.this,AdminInterface.class);
        Intent intent2 = new Intent(AdminSignIn.this,AdminInterface2.class);
        Intent intent3 = new Intent(AdminSignIn.this,AdminInterface3.class);
        Intent intent4 = new Intent(AdminSignIn.this,KingAdmin.class);


        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate Inputs for Admin

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(AdminSignIn.this,"Invalid Inputs.", Toast.LENGTH_LONG).show();
                } else if (username.getText().toString().equals(correct_username1)){
                    if (password.getText().toString().equals(correct_password1)) {
                        startActivity(intent1);
                        Toast.makeText(AdminSignIn.this,"Successfully logged in!", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(AdminSignIn.this,"Invalid username or password!", Toast.LENGTH_LONG).show();

                    }

                }

                else if (username.getText().toString().equals(correct_username2)){
                    if (password.getText().toString().equals(correct_password2)) {
                        startActivity(intent2);
                        Toast.makeText(AdminSignIn.this,"Successfully logged in!", Toast.LENGTH_LONG).show();

                    }

                }

                else if (username.getText().toString().equals(correct_username3)){
                    if (password.getText().toString().equals(correct_password3)) {
                        startActivity(intent3);
                        Toast.makeText(AdminSignIn.this,"Successfully logged in!", Toast.LENGTH_LONG).show();

                    }

                }

                else if (username.getText().toString().equals(correct_username4)){
                    if (password.getText().toString().equals(correct_password4)) {
                        startActivity(intent4);
                        Toast.makeText(AdminSignIn.this,"Successfully logged in!", Toast.LENGTH_LONG).show();

                    }

                }

                else {
                    Toast.makeText(AdminSignIn.this,"Incorrect username or password!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}