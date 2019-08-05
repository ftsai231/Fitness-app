package com.example.tsai.fitnesstrack;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutPlan_Activity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button addSetBtn;
    Button deleteSetBtn;
    Button updateSetBtn;
    EditText editWeight;
    EditText editReps;
    TextView workoutTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutplan);
        myDb = new DatabaseHelper(this);

        editWeight = (EditText) findViewById(R.id.weight);
        editReps = (EditText) findViewById(R.id.reps);
        addSetBtn = (Button) findViewById(R.id.add);
        deleteSetBtn = (Button) findViewById(R.id.delete);
        updateSetBtn = (Button) findViewById(R.id.update);
        workoutTable = (TextView) findViewById(R.id.table);

        addData();


    }

    public void addData(){
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
                String str = viewData();
                workoutTable.setText(str);
            }
        });
    }

    public String viewData(){
        Cursor res = myDb.getAllData();
        StringBuilder sb = new StringBuilder();
        while(res.moveToNext()){
            sb.append("Set: " + res.getString(0)+"   ");
            sb.append("Weight: " + res.getString(1) + "   ");
            sb.append("Reps: " + res.getString(2) + "\n");
        }
        return sb.toString();

    }


}
