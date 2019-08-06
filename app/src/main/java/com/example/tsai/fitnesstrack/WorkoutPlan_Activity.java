package com.example.tsai.fitnesstrack;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutPlan_Activity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private Button addSetBtn;
    private Button deleteSetBtn;
    private Button updateSetBtn;
    private EditText editSet;
    private EditText editWeight;
    private EditText editReps;
    private TextView tableSet;
    private TextView tableWeight;
    private TextView tableReps;

    private Context context = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutplan);
        myDb = new DatabaseHelper(this);

        editSet = (EditText) findViewById(R.id.set);
        editWeight = (EditText) findViewById(R.id.weight);
        editReps = (EditText) findViewById(R.id.reps);
        addSetBtn = (Button) findViewById(R.id.add);
        deleteSetBtn = (Button) findViewById(R.id.delete);
        updateSetBtn = (Button) findViewById(R.id.update);
        tableSet = (TextView) findViewById(R.id.table_Set);
        tableWeight = (TextView) findViewById(R.id.table_weight);
        tableReps = (TextView) findViewById(R.id.table_reps);
        viewData();
        addData();
        updateData();
        deleteData();


        /*************************************************************/

        context = getApplicationContext();
        final TableLayout tableLayout = (TableLayout)findViewById(R.id.table_layout_table);

        Button addRowButton = (Button) findViewById(R.id.table_layout_add_row_button);
        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow tableRow = new TableRow(context);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);

                Button btn = new Button(context);
                btn.setText("New Row");
                tableRow.addView(btn, 0);

                CheckBox checkBox = new CheckBox(context);
                checkBox.setText("Check it");
                tableRow.addView(checkBox, 1);

                tableLayout.addView(tableRow);



            }
        });


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

                viewData();

            }
        });
    }



    public void deleteData(){
        deleteSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editSet.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(WorkoutPlan_Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(WorkoutPlan_Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                viewData();
            }

        });
    }

    public void updateData(){
        updateSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editSet.getText().toString(),
                                            editWeight.getText().toString(),
                                            editReps.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(WorkoutPlan_Activity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(WorkoutPlan_Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();

                viewData();
            }
        });
    }


    public String[] viewData(){
        Cursor res = myDb.getAllData();
        StringBuilder sb_set = new StringBuilder();
        StringBuilder sb_weight = new StringBuilder();
        StringBuilder sb_reps = new StringBuilder();
        sb_set.append("Set \n");
        sb_weight.append("Weight\n");
        sb_reps.append("Reps\n");
        while(res.moveToNext()){
            sb_set.append(res.getString(0)+"\n");
            sb_weight.append(res.getString(1) + "\n");
            sb_reps.append(res.getString(2) + "\n");
        }
        String[] strArr = new String[3];
        strArr[0] = sb_set.toString();
        strArr[1] = sb_weight.toString();
        strArr[2] = sb_reps.toString();
        tableSet.setText(strArr[0]);
        tableWeight.setText(strArr[1]);
        tableReps.setText(strArr[2]);
        return strArr;
    }

}
