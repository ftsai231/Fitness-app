package com.example.tsai.fitnesstrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkoutPlan_Activity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button addSetBtn;
    Button deleteSetBtn;
    Button updateSetBtn;
    EditText editWeight;
    EditText editReps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutplan);
        myDb = new DatabaseHelper(this);

        editWeight = findViewById(R.id.weight);
        editReps = findViewById(R.id.reps);
        addSetBtn = (Button) findViewById(R.id.add);
        deleteSetBtn = (Button) findViewById(R.id.delete);
        updateSetBtn = (Button) findViewById(R.id.update);

        addSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editWeight.getText().toString(), editReps.getText().toString());
                if(isInserted){
                    Toast.makeText(WorkoutPlan_Activity.this, "Set Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(WorkoutPlan_Activity.this, "Set not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });







    }


}
