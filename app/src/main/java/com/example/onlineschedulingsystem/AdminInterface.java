package com.example.onlineschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminInterface extends AppCompatActivity {

    private Button Next;
    private Button Rest;
    private int counter;
    private TextView counterTxt;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Next:
                    plusConter();
                    break;
                case R.id.Rest:
                    initCounter();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);
        counterTxt = (TextView) findViewById(R.id.countertxt);
        Next = (Button) findViewById(R.id.Next);
        Next.setOnClickListener(clickListener);
        Rest = (Button) findViewById(R.id.Rest);
        Rest.setOnClickListener(clickListener);
        initCounter();
    }
    private void initCounter() {
        counter = 0;
        counterTxt.setText(counter + "");

    }
    private void plusConter(){
        counter++;
        counterTxt.setText(counter + "");
    }
}