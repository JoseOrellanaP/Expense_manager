package com.example.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "EXPENSES.db";
    public static final String TABLENAME = "EXPENSESTABLE";
    public static final String COL1 = "ID";
    public static final String COL2 = "YEAR";
    public static final String COL3 = "MONTH";
    public static final String COL4 = "DAY";
    public static final String COL5 = "TYPE";
    public static final String COL6 = "AMOUNT";

    public DataBaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLENAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " YEAR INTEGER, MONTH INTEGER, DAY INTEGER, TYPE TEXT, AMOUNT INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS "+TABLENAME);
    }

    public boolean addData (String year, String month, String day, String type, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, year);
        contentValues.put(COL3, month);
        contentValues.put(COL4, day);
        contentValues.put(COL5, type);
        contentValues.put(COL6, amount);
        long added = db.insert(TABLENAME, null, contentValues);

        if (added == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getData (String type, String month, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT "+ COL6+ " FROM "+ TABLENAME+" WHERE TYPE = "+"'"+type+"'"
                + " AND MONTH = "+month+ " AND YEAR = "+ year, null);
        return data;
    }

    public Cursor getDataM (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT "+ COL3 +" FROM "+TABLENAME, null);
        return data;
    }

    public Cursor getDataTotal(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLENAME, null);
        return data;
    }
}
