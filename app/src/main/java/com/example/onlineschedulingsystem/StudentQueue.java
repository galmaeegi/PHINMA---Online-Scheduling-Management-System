package com.example.onlineschedulingsystem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_queue);

        textView = findViewById(R.id.StudentCounter);
        button = findViewById(R.id.reset);
        firebaseDatabase = FirebaseDatabase.getInstance();

        ///Getting Child Data///
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("QUEUE");

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

    }
}