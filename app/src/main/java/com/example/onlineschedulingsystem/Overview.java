package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Overview extends AppCompatActivity {

    ImageButton snoutReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        /*Please TAKE A SCREENSHOT*/
        TextView textView = findViewById(R.id.take);
        String text = "Please TAKE A SCREENSHOT as this will become your valid receipt for verification.";

        SpannableString ss = new SpannableString(text);

        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);

        ss.setSpan(fcsRed, 7, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

        TextView studentDisplay = findViewById(R.id.studentno_text_view);
        TextView departmentDisplay = findViewById(R.id.department_text_view);
        TextView purposeDisplay = findViewById(R.id.purpose_text_view);
        TextView dateDisplay = findViewById(R.id.date_text_view);
        TextView queueDisplay = findViewById(R.id.queue_text_view);
        TextView youDisplay = findViewById(R.id.youare);

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

        youDisplay.setText(queue);



        ImageButton snoutReview = (ImageButton) findViewById(R.id.signout_review2);
        snoutReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this,WelcomeBack.class));
            }
        });
        ImageButton home = (ImageButton) findViewById(R.id.skip_as_guest);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this,StudentQueue.class));
            }
        });


    }
    @Override
    public void onBackPressed() {
    }

}