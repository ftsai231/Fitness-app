package com.example.tsai.fitnesstrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workoutDB";
    public static final String TABLE_NAME = "workout_table";
    public static final String Col_1 = "SETT";
    public static final String Col_2 = "WEIGHT";
    public static final String Col_3 = "REPS";
    public static final String Col_4 = "EXERCISE";
    public static final String Col_5 = "DATE";
    public static SimpleDateFormat date;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        date = new SimpleDateFormat("dd-M-yyyy");
        db.execSQL("CREATE TABLE " + TABLE_NAME + date.toString() +" (SETT INTEGER, WEIGHT TEXT , REPS TEXT, EXERCISE TEXT, DATE DATE);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);


    }


    // create new table with new exercise
    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (SETT INTEGER, WEIGHT TEXT , REPS TEXT, EXERCISE TEXT, DATE DATE);");
    }

    public void deleteTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ tableName);
//        db.execSQL("CREATE TABLE " + TABLE_NAME +" (SETT INTEGER, WEIGHT TEXT , REPS TEXT, EXERCISE TEXT, DATE DATE);");

    }


    public boolean insertData(String id, String weight, String reps, String exercise, Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, weight);
        contentValues.put(Col_3, reps);
        contentValues.put(Col_4, exercise);
        contentValues.put(Col_5, String.valueOf(date));
        long result = db.insert(TABLE_NAME, null, contentValues);
        System.out.print(result);
        if(result==-1){
            return false;
        }
        else return true;
    }

    public Integer deleteData(String id, String exercise){
        SQLiteDatabase db = this.getWritableDatabase();


        return db.delete(TABLE_NAME, "SETT = ?" + " AND " + "EXERCISE = ?", new String[] {id, exercise});
    }

    public boolean updateData(String set, String weight, String reps, String exercise, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, set);
        contentValues.put(Col_2, weight);
        contentValues.put(Col_3, reps);
        contentValues.put(Col_4, exercise);
        contentValues.put(Col_5, String.valueOf(date));

        db.update(TABLE_NAME, contentValues, "SETT = ?", new String[] {set});
        return true;
    }

    public Cursor getAllData(String exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE EXERCISE = " + "'" + exercise + "'" + ";", null);
        return res;
    }

    public Cursor getTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tableName + ";", null);
        return c;
    }
}
