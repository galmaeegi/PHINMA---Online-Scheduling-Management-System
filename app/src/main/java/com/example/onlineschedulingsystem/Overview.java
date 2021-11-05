package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Overview extends AppCompatActivity {

    ImageButton snoutReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        TextView purposeDisplay = findViewById(R.id.purpose_text_view);
        TextView dateDisplay = findViewById(R.id.date_text_view);
        TextView timeDisplay = findViewById(R.id.time_text_view);
        Bundle bn = getIntent().getExtras();
        String purpose = bn.getString("purposeKey");
        purposeDisplay.setText(purpose);

        String date = bn.getString("dateKey");
        dateDisplay.setText(date);

        String time = bn.getString("timeKey");
        timeDisplay.setText(time);


        ImageButton snoutReview = (ImageButton) findViewById(R.id.signout_review2);
        snoutReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this,Login.class));
            }
        });


    }

}