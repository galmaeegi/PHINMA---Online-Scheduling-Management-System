package com.example.onlineschedulingsystem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;
import java.util.HashMap;
import java.util.Map;

public class StudentQueue extends AppCompatActivity {

    TextView textView;
    Button button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView at1;
    private TextView at2;
    private TextView at3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_queue);

        textView = findViewById(R.id.StudentCounter);
        at1 = findViewById(R.id.aq1);
        at2 = findViewById(R.id.aq2);
        at3 = findViewById(R.id.aq3);
        button = findViewById(R.id.reset);
        firebaseDatabase = FirebaseDatabase.getInstance();

        ///Getting Child Data///
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("QUEUE");
        DatabaseReference TellerOnereference = FirebaseDatabase.getInstance().getReference().child("TELLER ONE");
        DatabaseReference TellerTworeference = FirebaseDatabase.getInstance().getReference().child("TELLER TWO");
        DatabaseReference TellerThreereference = FirebaseDatabase.getInstance().getReference().child("TELLER THREE");


        TellerOnereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t1data = dataSnapshot.child("NUMBER").getValue().toString();
                at1.setText(t1data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        TellerTworeference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t2data = dataSnapshot.child("NUMBER").getValue().toString();
                at2.setText(t2data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TellerThreereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t3data = dataSnapshot.child("NUMBER").getValue().toString();
                at3.setText(t3data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override

            ///Setting data in TextView///
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String data = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
            textView.setText(data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Transaction().isReset();
                startActivity(new Intent(StudentQueue.this, MainPage.class));
            }
        });

        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);

        smallSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentQueue.this,Login.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}