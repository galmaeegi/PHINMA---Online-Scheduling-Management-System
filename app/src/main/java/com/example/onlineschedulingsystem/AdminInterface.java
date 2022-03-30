package com.example.onlineschedulingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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
    public int s1 = 0;

    Dialog dialog;

    private FirebaseDatabase dbTeller = FirebaseDatabase.getInstance();
    private DatabaseReference tellerOne = dbTeller.getReference();

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.next:
                    plusConter();
                    break;
            }
            Rest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();
                }
            });
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

            //////////////////////////////////////////////////////////////
            //Creating Summary one//
            s1 = s1+1;
            tellerOne = FirebaseDatabase.getInstance().getReference().child("SUMMARY ONE");
            ///CREATING TELLER ONE CHILDREN///
            HashMap<String, Integer> summary1 = new HashMap<>();
            summary1.put("NUMBER", s1);
            tellerOne.setValue(summary1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);

        dialog = new Dialog(AdminInterface.this);
        dialog.setContentView(R.layout.custom_dialog);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }

        ///DIALOG with Okay and Cancel button///
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button okay = dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.Cancel);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminInterface.this,"The queue has been reset",Toast.LENGTH_SHORT).show();
                resetCounter();
                resetSummary();
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

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

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

    public void resetCounter() {
        counter = 0;
        counterTxt.setText(counter + "");

    }

    private void plusConter() {
        counter = Integer.parseInt(counterTxt.getText().toString());
        counter++;
        counterTxt.setText(counter + "");
    }
    public void resetSummary(){
        s1 = 0;
        tellerOne = FirebaseDatabase.getInstance().getReference().child("SUMMARY ONE");
        ///CREATING TELLER ONE CHILDREN///
        HashMap<String, Integer> summary1 = new HashMap<>();
        summary1.put("NUMBER", s1);
        tellerOne.setValue(summary1);
    }
    @Override
    public void onBackPressed() {
    }
}