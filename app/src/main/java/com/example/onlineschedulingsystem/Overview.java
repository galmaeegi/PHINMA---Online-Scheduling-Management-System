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

        TextView studentDisplay = findViewById(R.id.studentno_text_view);
        TextView departmentDisplay = findViewById(R.id.department_text_view);
        TextView purposeDisplay = findViewById(R.id.purpose_text_view);
        TextView dateDisplay = findViewById(R.id.date_text_view);
        TextView queueDisplay = findViewById(R.id.queue_text_view);

        Bundle bn = getIntent().getExtras();

        String student = bn.getString("studentKey");
        studentDisplay.setText(student);

        String dept = bn.getString("departmentKey");
        departmentDisplay.setText(dept);

        String purpose = bn.getString("purposeKey");
        purposeDisplay.setText(purpose);

        String date = bn.getString("dateKey");
        dateDisplay.setText(date);

        String queue = bn.getString("queueKey");
        queueDisplay.setText(queue);


        ImageButton snoutReview = (ImageButton) findViewById(R.id.signout_review2);
        snoutReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this,Login.class));
            }
        });


    }

}