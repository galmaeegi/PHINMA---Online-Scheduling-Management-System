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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminInterface extends AppCompatActivity {

    private ImageButton Next;
    private ImageButton Rest;
    private int counter;
    private TextView counterTxt;
    private TextView T1Text;
    private TextView T2Text;
    private TextView T3Text;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference motherqueue = db.getReference();


    private FirebaseDatabase dbTeller = FirebaseDatabase.getInstance();
    private DatabaseReference tellerOne = dbTeller.getReference();

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.next:
                    plusConter();

                    break;
                case R.id.Rest:
                    resetCounter();
                    break;
            }
            ///CREATING MOTHER DATA CHILD///
            String counter = counterTxt.getText().toString();
            motherqueue = FirebaseDatabase.getInstance().getReference().child("QUEUE");
            motherqueue.child(String.valueOf(Integer.parseInt(counter + 1)));
            ///CREATING MOTHER DATA CHILDREN///
            HashMap<String, String> usermap = new HashMap<>();
            usermap.put("CURRENT NUMBER", counter);
            motherqueue.setValue(usermap);

            ///CREATING TELLER ONE CHILD///
            String telone = counterTxt.getText().toString();
            tellerOne = FirebaseDatabase.getInstance().getReference().child("TELLER ONE");
            ///CREATING TELLER ONE CHILDREN///
            HashMap<String, String> tellerMap = new HashMap<>();
            tellerMap.put("NUMBER", telone);
            tellerOne.setValue(tellerMap);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("QUEUE");
        DatabaseReference TellerOnereference = FirebaseDatabase.getInstance().getReference().child("TELLER ONE");
        DatabaseReference TellerTworeference = FirebaseDatabase.getInstance().getReference().child("TELLER TWO");
        DatabaseReference TellerThreereference = FirebaseDatabase.getInstance().getReference().child("TELLER THREE");

        ///GETTING MOTHER DATA///
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            ///Setting data in TextView///
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                counterTxt.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ///GETTING TELLER ONE DATA///
        TellerOnereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String TellerOneData = dataSnapshot.child("NUMBER").getValue().toString();
                T1Text.setText(TellerOneData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /// GETTING TELLER TWO DATA///
        TellerTworeference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String TellerTwoData = dataSnapshot.child("NUMBER").getValue().toString();
                T2Text.setText(TellerTwoData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /// GETTING TELLER THREE DATA///
        TellerThreereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String TellerThreeData = dataSnapshot.child("NUMBER").getValue().toString();
                T3Text.setText(TellerThreeData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///SIGNOUT BUTTON///
        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);
        smallSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminInterface.this, AdminSignIn.class));
            }
        });
        T1Text = (TextView) findViewById(R.id.t1);
        T2Text = (TextView) findViewById(R.id.t2);
        T3Text = (TextView) findViewById(R.id.t3);
        counterTxt = (TextView) findViewById(R.id.countertxt);
        Next = (ImageButton) findViewById(R.id.next);
        Next.setOnClickListener(clickListener);
        Rest = (ImageButton) findViewById(R.id.Rest);
        Rest.setOnClickListener(clickListener);
        resetCounter();

    }

    private void resetCounter() {
        counter = 0;
        counterTxt.setText(counter + "");

    }

    private void plusConter() {
        counter = Integer.parseInt(counterTxt.getText().toString());
        counter++;
        counterTxt.setText(counter + "");
    }
    @Override
    public void onBackPressed() {
    }
}