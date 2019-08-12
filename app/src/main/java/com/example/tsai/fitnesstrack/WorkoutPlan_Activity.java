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
    private Button addRowButton;
    private TableLayout tableLayout;
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

        context = getApplicationContext();
        tableLayout = (TableLayout)findViewById(R.id.table_layout_table);
        addRowButton = (Button) findViewById(R.id.table_layout_add_row_button);

        viewDataTable();
        addData();
        updateData();
        deleteData();
        addTableRow();








    }



    public void addData(){
        addSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editSet.getText().toString(), editWeight.getText().toString(), editReps.getText().toString());
                if(isInserted){
                    Toast.makeText(WorkoutPlan_Activity.this, "Set Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(WorkoutPlan_Activity.this, "Set not Inserted", Toast.LENGTH_LONG).show();
                }

                /** add new row to the table **/

                TableRow tableRow = new TableRow(context);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);

                TextView tV1 = new TextView(context);
                tV1.setText(editSet.getText().toString());
                tV1.setTextSize(20);

                tableRow.addView(tV1, 0);

                TextView tV2 = new TextView(context);
                tV2.setText(editWeight.getText().toString());
                tableRow.addView(tV2, 1);

                TextView tV3 = new TextView(context);
                tV3.setText(editReps.getText().toString());
                tableRow.addView(tV3, 2);

                tableLayout.addView(tableRow);

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

                viewDataTable();
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

            }
        });
    }


    public void addTableRow(){
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

    public void viewDataTable(){
        Cursor res = myDb.getAllData();

        while(res.moveToNext()){

            TableRow tableRow = new TableRow(context);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);

            TextView tV1 = new TextView(context);
            tV1.setText(res.getString(0));
            tV1.setTextSize(20);

            tableRow.addView(tV1, 0);

            TextView tV2 = new TextView(context);
            tV2.setText(res.getString(1));
            tableRow.addView(tV2, 1);

            TextView tV3 = new TextView(context);
            tV3.setText(res.getString(2));
            tableRow.addView(tV3, 2);

            tableLayout.addView(tableRow);

        }

    }

}
