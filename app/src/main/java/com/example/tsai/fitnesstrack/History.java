package com.example.tsai.fitnesstrack;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsai.fitnesstrack.R;

import org.w3c.dom.Text;

public class History extends AppCompatActivity {
    private DatabaseHelper myDb;
    private Context context = null;
    private LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        myDb = new DatabaseHelper(this);
        context = getApplicationContext();
        linearLayout = findViewById(R.id.LL);
        showTable("barbell bench press");

    }

    public void showTable(String exercise){
        Cursor res = myDb.getAllData(exercise);
        StringBuilder sb = new StringBuilder();
        int s = 0;
        while(res.moveToNext()){
            sb.append(res.getString(s++));
            sb.append("\n");
        }
        TextView tv = new TextView(context);
        tv.setText(sb.toString());
        linearLayout.addView(tv);
    }

}
