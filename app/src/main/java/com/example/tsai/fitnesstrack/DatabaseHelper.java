package com.example.tsai.fitnesstrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workoutDB";
    public static final String TABLE_NAME = "workout_table";
    public static final String Col_1 = "SETT";
    public static final String Col_2 = "WEIGHT";
    public static final String Col_3 = "REPS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (SETT INTEGER PRIMARY KEY AUTOINCREMENT, WEIGHT TEXT , REPS TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String weight, String reps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, weight);
        contentValues.put(Col_3, reps);
        long result = db.insert(TABLE_NAME, null, contentValues);
        System.out.print(result);
        if(result==-1){
            return false;
        }
        else return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
