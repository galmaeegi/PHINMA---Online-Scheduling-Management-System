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
    private FirebaseFirestore mfirestore;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Next:
                    plusConter();
                    break;
                case R.id.Rest:
                    initCounter();
                    break;

            }
            String counter = counterTxt.getText().toString();
            Map<String, String> userMap = new HashMap<>();
            userMap.put("Queue", counter);

            mfirestore.collection("Queue").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(AdminInterface.this,"Queue Added", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String error = e.getMessage();
                    Toast.makeText(AdminInterface.this,"Error: "+ error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);
        mfirestore = FirebaseFirestore.getInstance();

        //Small Sign Out
        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);

        counterTxt = (TextView) findViewById(R.id.countertxt);

        Next = (Button) findViewById(R.id.Next);

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