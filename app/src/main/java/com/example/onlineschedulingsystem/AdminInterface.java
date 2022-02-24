package com.example.onlineschedulingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminInterface extends AppCompatActivity {

    private Button Next;
    private Button Rest;
    private int counter;
    private TextView counterTxt;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Load:
                    plusConter();
                    break;
                case R.id.Rest:
                    initCounter();
                    break;
            }
            String counter = counterTxt.getText().toString();
            root = FirebaseDatabase.getInstance().getReference().child("QUEUE");
            root.child(String.valueOf(Integer.parseInt(counter+1)));


            HashMap<String, String> usermap = new HashMap<>();
            usermap.put("Current Number",counter);
            root.setValue(usermap);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);

        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);
        smallSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminInterface.this,Login.class));
            }
        });

        counterTxt = (TextView) findViewById(R.id.countertxt);
        Next = (Button) findViewById(R.id.Load);
        Next.setOnClickListener(clickListener);
        Rest = (Button) findViewById(R.id.Rest);
        Rest.setOnClickListener(clickListener);
        initCounter();

    }
    private void initCounter() {
        counter = 0;
        counterTxt.setText(counter + "");

    }
    private void plusConter(){
        counter++;
        counterTxt.setText(counter + "");
    }
}