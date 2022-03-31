package com.example.onlineschedulingsystem;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Transaction extends AppCompatActivity{


    Spinner dept_spinner, purpose_spinner, date_spinner;
    TextView purpose_view1, date_view, purposeTxt;
    EditText student_no;
    String valueFromStudent, valueFromDept, valueFromPurpose, valueFromDate;
    String temp = "";
    String valueFromQueue;
    Boolean hasChange;
    Boolean m_check, t_check, w_check, th_check, f_check, s_check;

    FirebaseDatabase firebaseDatabase;



    DatabaseReference MonReff, TueReff, WedReff, ThuReff, FriReff, SatReff;




    long m_maxid,t_maxid, w_maxid, th_maxid, f_maxid, s_maxid = 0;

    /*Placement variables for admin's custom chosen slots*/
    int m_slots;
    int t_slots;
    int w_slots;
    int th_slots;
    int f_slots;
    int s_slots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        /*Proceed Button*/
        ImageButton save = (ImageButton) findViewById(R.id.proceed_button2);

        /*EditText Variables*/
        student_no = findViewById(R.id.studentno_input);


        /*Spinner varbiables*/
        purposeTxt = findViewById(R.id.purpose_text_view);
        dept_spinner = findViewById(R.id.dept_spinner);
        purpose_spinner = findViewById(R.id.purpose_spinner);
        date_spinner = findViewById(R.id.date_spinner);

        /*Getting child instances*/
        MonReff = FirebaseDatabase.getInstance().getReference().child("Monday");
        TueReff = FirebaseDatabase.getInstance().getReference() .child("Tuesday");
        WedReff = FirebaseDatabase.getInstance().getReference() .child("Wednesday");
        ThuReff = FirebaseDatabase.getInstance().getReference() .child("Thursday");
        FriReff = FirebaseDatabase.getInstance().getReference() .child("Friday");
        SatReff = FirebaseDatabase.getInstance().getReference() .child("Saturday");


        /*Getting admin chosen slots to students*/
        DatabaseReference MonSlot = FirebaseDatabase.getInstance().getReference().child("Monday Slot");
        DatabaseReference TueSlot = FirebaseDatabase.getInstance().getReference().child("Tuesday Slot");
        DatabaseReference WedSlot = FirebaseDatabase.getInstance().getReference().child("Wednesday Slot");
        DatabaseReference ThuSlot = FirebaseDatabase.getInstance().getReference().child("Thursday Slot");
        DatabaseReference FriSlot = FirebaseDatabase.getInstance().getReference().child("Friday Slot");
        DatabaseReference SatSlot = FirebaseDatabase.getInstance().getReference().child("Saturday Slot");

        /*Data Snapshot Monday Slot*/
        MonSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mondaySlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                m_slots = Integer.parseInt(mondaySlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TueSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tuesdaySlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                t_slots = Integer.parseInt(tuesdaySlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        WedSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String wedSlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                w_slots = Integer.parseInt(wedSlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ThuSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String thuSlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                th_slots = Integer.parseInt(thuSlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FriSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String friSlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                f_slots = Integer.parseInt(friSlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SatSlot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String satSlotDataSnapshot = dataSnapshot.child("CURRENT NUMBER").getValue().toString();
                s_slots = Integer.parseInt(satSlotDataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        /*Checking slots if full or not*/
        mondayCheck();
        tuesdayCheck();
        wednesdayCheck();
        thursdayCheck();
        fridayCheck();
        saturdayCheck();


        /*Getting children count in Firebase*/
        MonReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                if(snapshot1.exists()) {
                    m_maxid=(snapshot1.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TueReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                if(snapshot2.exists()) {
                    t_maxid=(snapshot2.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        WedReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                if(snapshot3.exists()) {
                    w_maxid=(snapshot3.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ThuReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot4) {
                if(snapshot4.exists()) {
                    th_maxid=(snapshot4.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FriReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot5) {
                if(snapshot5.exists()) {
                    f_maxid=(snapshot5.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SatReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot6) {
                if(snapshot6.exists()) {
                    s_maxid=(snapshot6.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        /*Department Spinner*/
        String[] departmentArray = getResources().getStringArray(R.array.department);
        ArrayAdapter departmentAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departmentArray);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_spinner.setAdapter(departmentAdapter);

        /*Purpose Spinner*/
        String[] purposeArray = getResources().getStringArray(R.array.purpose);
        ArrayAdapter purposeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, purposeArray);
        purposeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpose_spinner.setAdapter(purposeAdapter);



        /*Date Spinner*/
        String[] dateArray = getResources().getStringArray(R.array.date);
        ArrayAdapter dateAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dateArray);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date_spinner.setAdapter(dateAdapter);


        /*DEPARTMENT = setOnItemSelectedLister*/
        dept_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueFromDept = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*PURPOSE = setOnItemSelectedLister*/
        purpose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueFromPurpose = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*DATE = setOnItemSelectedLister*/
        date_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueFromDate = parent.getItemAtPosition(position).toString();
                if (valueFromDate.equals("Monday")) {
                    if (!mondayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         MONDAY is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Monday";
                    }
                }

                else if (valueFromDate.equals("Tuesday")) {
                    if (!tuesdayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         Tuesday is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Tuesday";
                    }

                }

                else if (valueFromDate.equals("Wednesday")) {
                    if (!wednesdayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         Wednesday is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Wednesday";
                    }

                }

                else if (valueFromDate.equals("Thursday")) {
                    if (!thursdayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         Thursday is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Thursday";
                    }

                } else if (valueFromDate.equals("Friday")) {
                    if (!fridayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         Friday is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Friday";
                    }

                } else if (valueFromDate.equals("Saturday")) {
                    if (!saturdayCheck()){
                        Toast toast =Toast.makeText(Transaction.this, "         Saturday is FULL! \n Please choose another day!",Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        temp = "Saturday";
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //Save values to firebase
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (temp=="Monday") {

                        insertDataMon();
                        //Sending data to Overview.class
                        SendDataOverview();
                }

                else if (temp=="Tuesday") {
                    insertDataTue();
                    //Sending data to Overview.class
                    SendDataOverview();

                }
                else if (temp=="Wednesday") {
                    insertDataWed();
                    //Sending data to Overview.class
                    SendDataOverview();


                }
                else if (temp=="Thursday") {
                    insertDataThu();
                    //Sending data to Overview.class
                    SendDataOverview();
                }
                else if (temp=="Friday") {
                    insertDataFri();
                    //Sending data to Overview.class
                    SendDataOverview();

                }
                else if (temp=="Saturday") {
                    insertDataSat();
                    //Sending data to Overview.class
                    SendDataOverview();

                }



            }
        });

        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);

        smallSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Transaction.this,Login.class));

            }
        });

    }







    /*Insertion Method (MONDAY)*/
    private void insertDataMon() {


        valueFromQueue = String.valueOf(m_maxid+1);
        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);
        MonReff.child(String.valueOf(m_maxid+1)).setValue((monday));



    }

    /*Insertion Method (TUESDAY)*/
    private void insertDataTue() {

        valueFromQueue = String.valueOf(t_maxid+1);
        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);
        TueReff.child(String.valueOf(t_maxid+1)).setValue((monday));

    }

    /*Insertion Method (WEDNESDAY)*/
    private void insertDataWed() {

        valueFromQueue = String.valueOf(w_maxid+1);
        /*Sending Data to Firebase*/

        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        WedReff.child(String.valueOf(w_maxid+1)).setValue((monday));

    }

    /*Insertion Method (THURSDAY)*/
    private void insertDataThu() {

        valueFromQueue = String.valueOf(th_maxid+1);
        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        ThuReff.child(String.valueOf(th_maxid+1)).setValue((monday));

    }

    /*Insertion Method (FRIDAY)*/
    private void insertDataFri() {

        valueFromQueue = String.valueOf(f_maxid+1);
        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        FriReff.child(String.valueOf(f_maxid+1)).setValue((monday));

    }

    /*Insertion Method (SATURDAY)*/
    private void insertDataSat() {

        valueFromQueue = String.valueOf(s_maxid+1);
        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        SatReff.child(String.valueOf(s_maxid+1)).setValue((monday));


    }
    //Sending data to Overview.class
    private void SendDataOverview() {
        isTransact();
        //Sending data to Overview.class
        Intent intent = new Intent(Transaction.this,Overview.class);

        valueFromStudent = student_no.getText().toString();
        intent.putExtra("studentKey",valueFromStudent);
        intent.putExtra("departmentKey",valueFromDept);
        intent.putExtra("purposeKey",valueFromPurpose);
        intent.putExtra("dateKey",valueFromDate);
        intent.putExtra("queueKey",valueFromQueue);



        startActivity(intent);
    }
    /*To check if the student has transacted already*/
    public boolean isTransact() {
        hasChange = true;
        return hasChange;
    }
    /*Reset Button for student to transact again.*/
    public boolean isReset() {
        hasChange = false;
        return hasChange;

    }

    /*Checking if the day is full*/
    public boolean mondayCheck() {
        if (m_maxid >= m_slots) {
            m_check = false;
        } else {
            m_check = true;
        }
        return m_check;
    }

    /*Checking if the day is full*/
    public boolean tuesdayCheck() {
        if (t_maxid >= t_slots) {
            t_check = false;
        } else {
            t_check = true;
        }
        return t_check;
    }

    /*Checking if the day is full*/
    public boolean wednesdayCheck() {
        if (w_maxid >= w_slots) {
            w_check = false;
        } else {
            w_check = true;
        }
        return w_check;
    }

    /*Checking if the day is full*/
    public boolean thursdayCheck() {
        if (th_maxid >= th_slots) {
            th_check = false;
        } else {
            th_check = true;
        }
        return th_check;
    }

    /*Checking if the day is full*/
    public boolean fridayCheck() {
        if (f_maxid >= f_slots) {
            f_check = false;
        } else {
            f_check = true;
        }
        return f_check;
    }

    /*Checking if the day is full*/
    public boolean saturdayCheck() {
        if (s_maxid >= s_slots) {
            s_check = false;
        } else {
            s_check = true;
        }
        return s_check;
    }

}