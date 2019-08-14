package com.example.tsai.fitnesstrack;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.*;

public class WorkoutPlan_Activity extends AppCompatActivity {


    private Button addSetBtn1;
    private Button deleteSetBtn1;
    private Button updateSetBtn1;
    private Button addSetBtn2;
    private Button deleteSetBtn2;
    private Button updateSetBtn2;
    private Button addSetBtn3;
    private Button deleteSetBtn3;
    private Button updateSetBtn3;
    private Button addSetBtn4;
    private Button deleteSetBtn4;
    private Button updateSetBtn4;
    private Button addSetBtn5;
    private Button deleteSetBtn5;
    private Button updateSetBtn5;
    private Button addSetBtn6;
    private Button deleteSetBtn6;
    private Button updateSetBtn6;

    private DatabaseHelper myDb;
    private Button deleteAllBtn;
    private Context context = null;

    private EditText editSet1;
    private EditText editWeight1;
    private EditText editReps1;
    private EditText editSet2;
    private EditText editWeight2;
    private EditText editReps2;
    private EditText editSet3;
    private EditText editWeight3;
    private EditText editReps3;
    private EditText editSet4;
    private EditText editWeight4;
    private EditText editReps4;
    private EditText editSet5;
    private EditText editWeight5;
    private EditText editReps5;
    private EditText editSet6;
    private EditText editWeight6;
    private EditText editReps6;

    private TableLayout tableLayout1;
    private TableLayout tableLayout2;
    private TableLayout tableLayout3;
    private TableLayout tableLayout4;
    private TableLayout tableLayout5;
    private TableLayout tableLayout6;


    private final String exercise1 = "barbell bench press";
    private final String exercise2 = "dumbbell bench press";
    private final String exercise3 = "incline barbell bench press";
    private final String exercise4 = "dips for chest";
    private final String exercise5 = "bench cable fly";
    private final String exercise6 = "incline dumbbell flies";

    private SimpleDateFormat date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutplan);

        myDb = new DatabaseHelper(this);
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        context = getApplicationContext();

        editSet1 = (EditText) findViewById(R.id.set);
        editWeight1 = (EditText) findViewById(R.id.weight);
        editReps1 = (EditText) findViewById(R.id.reps);
        editSet2 = (EditText) findViewById(R.id.set2);
        editWeight2 = (EditText) findViewById(R.id.weight2);
        editReps2 = (EditText) findViewById(R.id.reps2);
        editSet3 = (EditText) findViewById(R.id.set3);
        editWeight3 = (EditText) findViewById(R.id.weight3);
        editReps3 = (EditText) findViewById(R.id.reps3);
        editSet4 = (EditText) findViewById(R.id.set4);
        editWeight4 = (EditText) findViewById(R.id.weight4);
        editReps4 = (EditText) findViewById(R.id.reps4);
        editSet5 = (EditText) findViewById(R.id.set5);
        editWeight5 = (EditText) findViewById(R.id.weight5);
        editReps5 = (EditText) findViewById(R.id.reps5);
        editSet6 = (EditText) findViewById(R.id.set6);
        editWeight6 = (EditText) findViewById(R.id.weight6);
        editReps6 = (EditText) findViewById(R.id.reps6);



        deleteAllBtn = (Button) findViewById(R.id.deleteAll);

        addSetBtn1 = (Button) findViewById(R.id.add);
        deleteSetBtn1 = (Button) findViewById(R.id.delete);
        updateSetBtn1 = (Button) findViewById(R.id.update);
        addSetBtn2 = (Button) findViewById(R.id.add2);
        deleteSetBtn2 = (Button) findViewById(R.id.delete2);
        updateSetBtn2 = (Button) findViewById(R.id.update2);
        addSetBtn3 = (Button) findViewById(R.id.add3);
        deleteSetBtn3 = (Button) findViewById(R.id.delete3);
        updateSetBtn3 = (Button) findViewById(R.id.update3);
        addSetBtn4 = (Button) findViewById(R.id.add4);
        deleteSetBtn4 = (Button) findViewById(R.id.delete4);
        updateSetBtn4 = (Button) findViewById(R.id.update4);
        addSetBtn5 = (Button) findViewById(R.id.add5);
        deleteSetBtn5 = (Button) findViewById(R.id.delete5);
        updateSetBtn5 = (Button) findViewById(R.id.update5);
        addSetBtn6 = (Button) findViewById(R.id.add6);
        deleteSetBtn6 = (Button) findViewById(R.id.delete6);
        updateSetBtn6 = (Button) findViewById(R.id.update6);

        tableLayout1 = (TableLayout)findViewById(R.id.table_layout_table);
        tableLayout2 = (TableLayout)findViewById(R.id.table_layout_table2);
        tableLayout3 = (TableLayout)findViewById(R.id.table_layout_table3);
        tableLayout4 = (TableLayout)findViewById(R.id.table_layout_table4);
        tableLayout5 = (TableLayout)findViewById(R.id.table_layout_table5);
        tableLayout6 = (TableLayout)findViewById(R.id.table_layout_table6);
//        addRowButton = (Button) findViewById(R.id.table_layout_add_row_button);

        viewDataTable(exercise1, tableLayout1);
        viewDataTable(exercise2, tableLayout2);
        viewDataTable(exercise3, tableLayout3);
        viewDataTable(exercise4, tableLayout4);
        viewDataTable(exercise5, tableLayout5);
        viewDataTable(exercise6, tableLayout6);
        addData(addSetBtn1, exercise1, tableLayout1, editSet1, editWeight1, editReps1);
        addData(addSetBtn2, exercise2, tableLayout2, editSet2, editWeight2, editReps2);
        addData(addSetBtn3, exercise3, tableLayout3, editSet3, editWeight3, editReps3);
        addData(addSetBtn4, exercise4, tableLayout4, editSet4, editWeight4, editReps4);
        addData(addSetBtn5, exercise5, tableLayout5, editSet5, editWeight5, editReps5);
        addData(addSetBtn6, exercise6, tableLayout6, editSet6, editWeight6, editReps6);
        updateData();
        deleteData(deleteSetBtn1, exercise1);
        deleteAllData();
    }

    @Override
    public void onResume(){
        super.onResume();
        myDb = new DatabaseHelper(this);
    }

    public void addData(Button btn, final String exercise, final TableLayout table, final EditText editSet, final EditText editWeight, final EditText editReps){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editSet.getText().toString(), editWeight.getText().toString(), editReps.getText().toString(), exercise, date);
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

                TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

                TextView tV1 = new TextView(context);
                tV1.setLayoutParams(params);
                tV1.setText(editSet.getText().toString());
                tV1.setTextColor(Color.BLACK);
                tV1.setTextSize(20);

                tableRow.addView(tV1, 0);

                TextView tV2 = new TextView(context);
                tV2.setLayoutParams(params);
                tV2.setText(editWeight.getText().toString());
                tV2.setTextColor(Color.BLACK);
                tV2.setTextSize(20);

                tableRow.addView(tV2, 1);

                TextView tV3 = new TextView(context);
                tV2.setLayoutParams(params);
                tV3.setText(editReps.getText().toString());
                tV3.setTextColor(Color.BLACK);
                tV3.setTextSize(20);
                tableRow.addView(tV3, 2);

                table.addView(tableRow);


            }
        });
    }



    public void deleteData(Button btn, final String exercise){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editSet1.getText().toString(), exercise);
                if(deletedRows > 0)
                    Toast.makeText(WorkoutPlan_Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(WorkoutPlan_Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

            }

        });
    }

    public void deleteAllData(){
        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteTable();
                while (tableLayout1.getChildCount() > 2) tableLayout1.removeView(tableLayout1.getChildAt(tableLayout1.getChildCount() - 1));
            }
        });
    }

    public void updateData(){
        updateSetBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editSet1.getText().toString(),
                                            editWeight1.getText().toString(),
                                            editReps1.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(WorkoutPlan_Activity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(WorkoutPlan_Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();



            }
        });
    }


//    public void addTableRow(){
//        addRowButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TableRow tableRow = new TableRow(context);
//                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                tableRow.setLayoutParams(layoutParams);
//
//                Button btn = new Button(context);
//                btn.setText("New Row");
//                tableRow.addView(btn, 0);
//
//                CheckBox checkBox = new CheckBox(context);
//                checkBox.setText("Check it");
//                tableRow.addView(checkBox, 1);
//
//                tableLayout.addView(tableRow);
//            }
//        });
//    }

    public void viewDataTable(String exercise, TableLayout table){
        Cursor res = myDb.getAllData(exercise);

        while(res.moveToNext()){

            TableRow tableRow = new TableRow(context);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);

            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            TextView tV1 = new TextView(context);
            tV1.setLayoutParams(params);
            tV1.setText(res.getString(0));
            tV1.setTextColor(Color.BLACK);
            tV1.setTextSize(20);

            tableRow.addView(tV1, 0);

            TextView tV2 = new TextView(context);
            tV2.setLayoutParams(params);
            tV2.setText(res.getString(1));
            tV2.setTextColor(Color.BLACK);
            tV2.setTextSize(20);

            tableRow.addView(tV2, 1);

            TextView tV3 = new TextView(context);
            tV2.setLayoutParams(params);
            tV3.setText(res.getString(2));
            tV3.setTextColor(Color.BLACK);
            tV3.setTextSize(20);
            tableRow.addView(tV3, 2);

            table.addView(tableRow);

        }

    }

}
