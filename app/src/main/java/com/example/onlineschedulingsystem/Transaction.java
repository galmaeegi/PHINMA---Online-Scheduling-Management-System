package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class Transaction extends AppCompatActivity{


    Spinner purpose_spinner, date_spinner, time_spinner;
    TextView purpose_view1, date_view, time_view, purposeTxt;
    String valueFromPurpose, valueFromDate, valueFromTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        /*Proceed Button*/
        ImageButton save = (ImageButton) findViewById(R.id.proceed_button2);


        /*Spinner varbiables*/
        purposeTxt = findViewById(R.id.purpose_text_view);
        purpose_spinner = findViewById(R.id.purpose_spinner);
        date_spinner = findViewById(R.id.date_spinner);
        time_spinner = findViewById(R.id.time_spinner);



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

        /*Time Spinner*/
        String[] timeArray = getResources().getStringArray(R.array.time);
        ArrayAdapter timeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeArray);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(timeAdapter);



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


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*TIME = setOnItemSelectedLister*/
        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueFromTime = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Transaction.this,Overview.class);
                intent.putExtra("purposeKey",valueFromPurpose);
                intent.putExtra("dateKey",valueFromDate);
                intent.putExtra("timeKey",valueFromTime);
                startActivity(intent);

            }
        });

    }

}