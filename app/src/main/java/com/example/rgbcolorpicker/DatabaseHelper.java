package com.example.rgbcolorpicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "Favorite";
    private static final String DATABASENAME = "COLOR.db";
    private static final String COL1 = "ID";
    private static final String COL2 = "rgb";
    private static final String COL3 = "hex";
    private static final String COL4 = "hsv";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_color_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT NOT NULL, " +
                COL3 + " TEXT NOT NULL, " +
                COL4 + " TEXT NOT NULL "
                +");";


        db.execSQL(SQL_CREATE_color_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }





    public void delete_color(int id){

    }

}
