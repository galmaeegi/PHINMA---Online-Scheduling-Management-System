package com.example.onlineschedulingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Summary_interface extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private TextView summary1;
    private TextView summary2;
    private TextView summary3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_interface);


        summary1 = findViewById(R.id.s1);
        summary2 = findViewById(R.id.s2);
        summary3 = findViewById(R.id.s3);
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Getting Summary Data//
        DatabaseReference SumRef1 = FirebaseDatabase.getInstance().getReference().child("SUMMARY ONE");
        DatabaseReference SumRef2 = FirebaseDatabase.getInstance().getReference().child("SUMMARY TWO");
        DatabaseReference SumRef3 = FirebaseDatabase.getInstance().getReference().child("SUMMARY THREE");

        SumRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s1data = snapshot.child("NUMBER").getValue().toString();
                summary1.setText(s1data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        SumRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s2data = snapshot.child("NUMBER").getValue().toString();
                summary2.setText(s2data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SumRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s3data = snapshot.child("NUMBER").getValue().toString();
                summary3.setText(s3data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}