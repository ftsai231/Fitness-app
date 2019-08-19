package com.example.tsai.fitnesstrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    Button button;
    Button workoutDict;
    Button workoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        workoutDict = (Button) findViewById(R.id.btn_mybutton);
//        workoutDict.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent i = new Intent(MainActivity.this, StartActivity.class);
//                startActivity(i);
//            }
//        });

        workoutPlan = findViewById(R.id.start);
        workoutPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WorkoutPlans.class);
                startActivity(i);
            }
        });

    }





}
