package com.example.tsai.fitnesstrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WorkoutPlans extends AppCompatActivity {

    Button chestWorkout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_plan);

        chestWorkout = (Button) findViewById(R.id.btn_chest);
        chestWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkoutPlans.this, ChestPlan.class);
                startActivity(i);
            }
        });


    }

}
