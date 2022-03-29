package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetSlots extends AppCompatActivity {

    EditText et_mon, et_tue, et_wed, et_thu, et_fri, et_sat;
    ImageButton setslots_proceed;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference mondaySlot = db.getReference();
    private DatabaseReference tuesdaySlot = db.getReference();
    private DatabaseReference wednesdaySlot = db.getReference();
    private DatabaseReference thursdaySlot = db.getReference();
    private DatabaseReference fridaySlot = db.getReference();
    private DatabaseReference saturdaySlot = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_slots);

        et_mon = findViewById(R.id.et_mon);
        et_tue = findViewById(R.id.et_tue);
        et_wed = findViewById(R.id.et_wed);
        et_thu = findViewById(R.id.et_thu);
        et_fri = findViewById(R.id.et_fri);
        et_sat = findViewById(R.id.et_sat);






        setslots_proceed = findViewById(R.id.setslots_proceed);



        setslots_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_value = et_mon.getText().toString();
                mondaySlot = FirebaseDatabase.getInstance().getReference().child("Monday Slot");
                mondaySlot.child((String.valueOf(Integer.parseInt(m_value))));
                HashMap<String, Integer> usermap_m = new HashMap<>();
                usermap_m.put("CURRENT NUMBER", Integer.parseInt(m_value));
                mondaySlot.setValue(usermap_m);

                String t_value = et_tue.getText().toString();
                tuesdaySlot = FirebaseDatabase.getInstance().getReference().child("Tuesday Slot");
                tuesdaySlot.child((String.valueOf(Integer.parseInt(t_value))));
                HashMap<String, Integer> usermap_t = new HashMap<>();
                usermap_t.put("CURRENT NUMBER", Integer.parseInt(t_value));
                tuesdaySlot.setValue(usermap_t);

                String w_value = et_wed.getText().toString();
                wednesdaySlot = FirebaseDatabase.getInstance().getReference().child("Wednesday Slot");
                wednesdaySlot.child((String.valueOf(Integer.parseInt(w_value))));
                HashMap<String, Integer> usermap_w = new HashMap<>();
                usermap_w.put("CURRENT NUMBER", Integer.parseInt(w_value));
                wednesdaySlot.setValue(usermap_w);

                String th_value = et_thu.getText().toString();
                thursdaySlot = FirebaseDatabase.getInstance().getReference().child("Thursday Slot");
                thursdaySlot.child((String.valueOf(Integer.parseInt(th_value))));
                HashMap<String, Integer> usermap_th = new HashMap<>();
                usermap_th.put("CURRENT NUMBER", Integer.parseInt(th_value));
                thursdaySlot.setValue(usermap_th);

                String f_value = et_fri.getText().toString();
                fridaySlot = FirebaseDatabase.getInstance().getReference().child("Friday Slot");
                fridaySlot.child((String.valueOf(Integer.parseInt(f_value))));
                HashMap<String, Integer> usermap_f = new HashMap<>();
                usermap_f.put("CURRENT NUMBER", Integer.parseInt(f_value));
                fridaySlot.setValue(usermap_f);

                String s_value = et_sat.getText().toString();
                saturdaySlot = FirebaseDatabase.getInstance().getReference().child("Saturday Slot");
                saturdaySlot.child((String.valueOf(Integer.parseInt(s_value))));
                HashMap<String, Integer> usermap_s = new HashMap<>();
                usermap_s.put("CURRENT NUMBER", Integer.parseInt(s_value));
                saturdaySlot.setValue(usermap_s);

                Toast toast =Toast.makeText(SetSlots.this, "You successfully adjusted slots!",Toast.LENGTH_LONG);
                toast.show();




            }
        });

        ImageButton home_button = (ImageButton) findViewById(R.id.home_small);

        home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SetSlots.this,KingAdmin.class));

            }
        });


    }

}