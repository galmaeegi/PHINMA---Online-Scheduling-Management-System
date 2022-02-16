package com.example.onlineschedulingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TransactionSample extends AppCompatActivity{


    Spinner dept_spinner, purpose_spinner, date_spinner;
    TextView purpose_view1, date_view, purposeTxt;
    EditText student_no;
    String valueFromStudent, valueFromDept, valueFromPurpose, valueFromDate;


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

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TransactionSample.this,Overview.class);
                valueFromStudent = student_no.getText().toString();
                intent.putExtra("studentKey",valueFromStudent);
                intent.putExtra("departmentKey",valueFromDept);
                intent.putExtra("purposeKey",valueFromPurpose);
                intent.putExtra("dateKey",valueFromDate);
                startActivity(intent);

            }
        });

        ImageButton smallSignOut = (ImageButton) findViewById(R.id.signout_button);

        smallSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TransactionSample.this,Login.class));

            }
        });



    }

}