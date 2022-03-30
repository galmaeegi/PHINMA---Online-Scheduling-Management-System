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

public class AdminInterface3 extends AppCompatActivity {

    private ImageButton Next;
    private ImageButton Rest;
    private int counter;
    private TextView counterTxt;
    private TextView T1Text;
    private TextView T2Text;
    private TextView T3Text;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference motherqueue = db.getReference();
    public int s3 = 0;

    Dialog dialog;


    private FirebaseDatabase dbTeller = FirebaseDatabase.getInstance();
    private DatabaseReference tellerThree = dbTeller.getReference();

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
            String telthree = counterTxt.getText().toString();
            tellerThree = FirebaseDatabase.getInstance().getReference().child("TELLER THREE");
            ///CREATING TELLER ONE CHILDREN///
            HashMap<String, String> tellerMap = new HashMap<>();
            tellerMap.put("NUMBER", telthree);
            tellerThree.setValue(tellerMap);

            s3 = s3+1;
            tellerThree = FirebaseDatabase.getInstance().getReference().child("SUMMARY THREE");
            ///CREATING TELLER ONE CHILDREN///
            HashMap<String, Integer> summary3 = new HashMap<>();
            summary3.put("NUMBER", s3);
            tellerThree.setValue(summary3);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface3);

        dialog = new Dialog(AdminInterface3.this);
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
                Toast.makeText(AdminInterface3.this,"The Queue has been Rest",Toast.LENGTH_SHORT).show();
                resetCounter();
                resetSummary();
                ///CREATING MOTHER DATA CHILD///
                String counter = counterTxt.getText().toString();
                motherqueue = FirebaseDatabase.getInstance().getReference().child("QUEUE");
                motherqueue.child(String.valueOf(Integer.parseInt(counter + 1)));
                ///CREATING MOTHER DATA CHILDREN///
                HashMap<String, String> usermap = new HashMap<>();
                usermap.put("CURRENT NUMBER", counter);
                motherqueue.setValue(usermap);

                ///CREATING TELLER ONE CHILD///
                String telthree = counterTxt.getText().toString();
                tellerThree = FirebaseDatabase.getInstance().getReference().child("TELLER THREE");
                ///CREATING TELLER ONE CHILDREN///
                HashMap<String, String> tellerMap = new HashMap<>();
                tellerMap.put("NUMBER", telthree);
                tellerThree.setValue(tellerMap);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminInterface3.this,"The Queue was not reset",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        ///Child Data Variables///
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("QUEUE");
        DatabaseReference TellerOnereference = FirebaseDatabase.getInstance().getReference().child("TELLER ONE");
        DatabaseReference TellerTworeference = FirebaseDatabase.getInstance().getReference().child("TELLER TWO");
        DatabaseReference TellerThreereference = FirebaseDatabase.getInstance().getReference().child("TELLER THREE");

        ///GETTING MODER QUEUE DAT///
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

        ///Getting TellerTwo DATA///
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

        ///GETTING TELLER THREE DATA///
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
        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);
        smallSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminInterface3.this, AdminSignIn.class));
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

    public void resetSummary(){
        s3 = 0;
        tellerThree = FirebaseDatabase.getInstance().getReference().child("SUMMARY THREE");
        ///CREATING TELLER ONE CHILDREN///
        HashMap<String, Integer> summary2 = new HashMap<>();
        summary2.put("NUMBER", s3);
        tellerThree.setValue(summary2);
    }
    @Override
    public void onBackPressed() {
    }
}