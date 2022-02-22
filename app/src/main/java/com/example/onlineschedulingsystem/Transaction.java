package com.example.onlineschedulingsystem;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

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
    String temp;
    String valueFromQueue;

    DatabaseReference MonReff, TueReff, WedReff, ThuReff, FriReff, SatReff;

    long maxid = 0;

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
        MonReff = FirebaseDatabase.getInstance().getReference() .child("Monday");
        TueReff = FirebaseDatabase.getInstance().getReference() .child("Tuesday");
        WedReff = FirebaseDatabase.getInstance().getReference() .child("Wednesday");
        ThuReff = FirebaseDatabase.getInstance().getReference() .child("Thursday");
        FriReff = FirebaseDatabase.getInstance().getReference() .child("Friday");
        SatReff = FirebaseDatabase.getInstance().getReference() .child("Saturday");




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
                    temp = "Monday";

                } else if (valueFromDate.equals("Tuesday")) {
                    temp = "Tuesday";

                } else if (valueFromDate.equals("Wednesday")) {
                    temp = "Wednesday";

                } else if (valueFromDate.equals("Thursday")) {
                    temp = "Thursday";

                } else if (valueFromDate.equals("Friday")) {
                    temp = "Friday";

                } else if (valueFromDate.equals("Saturday")) {
                    temp = "Saturday";

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
                    Intent intent = new Intent(Transaction.this,Overview.class);
                    valueFromStudent = student_no.getText().toString();
                    intent.putExtra("studentKey",valueFromStudent);
                    intent.putExtra("departmentKey",valueFromDept);
                    intent.putExtra("purposeKey",valueFromPurpose);
                    intent.putExtra("dateKey",valueFromDate);
                    intent.putExtra("queueKey",valueFromQueue);
                    startActivity(intent);

                }

                else if (temp=="Tuesday") {
                    insertDataTue();


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
                else if (temp=="Wednesday") {
                    insertDataWed();


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
                else if (temp=="Thursday") {
                    insertDataThu();


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
                else if (temp=="Friday") {
                    insertDataFri();


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
                else if (temp=="Saturday") {
                    insertDataSat();


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


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        MonReff.push().setValue(monday);

    }

    /*Insertion Method (TUESDAY)*/
    private void insertDataTue() {


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        TueReff.push().setValue(monday);

    }

    /*Insertion Method (WEDNESDAY)*/
    private void insertDataWed() {


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        WedReff.push().setValue(monday);

    }

    /*Insertion Method (THURSDAY)*/
    private void insertDataThu() {


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        ThuReff.push().setValue(monday);

    }

    /*Insertion Method (FRIDAY)*/
    private void insertDataFri() {


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        FriReff.push().setValue(monday);

    }

    /*Insertion Method (SATURDAY)*/
    private void insertDataSat() {


        /*Sending Data to Firebase*/
        String student_ =  student_no.getText().toString();
        String department_ = dept_spinner.getSelectedItem().toString();
        String purpose_ = purpose_spinner.getSelectedItem().toString();

        Monday monday = new Monday(student_,department_,purpose_);


        SatReff.push().setValue(monday);

    }

}