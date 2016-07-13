/*
package com.learn.teleprompter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

*/
/**
 * Created by E01090 on 7/13/2016.
 *//*

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String _ID = "_id";
    static final String _TITLE = "title";
    static final String _CONTENT = "content";
    static final String _DATE = "date";

    static final String DATABASE_NAME = "scripts_db";
    static final String SCRIPT_TABLE_NAME = "scripts_tbl";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + SCRIPT_TABLE_NAME + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + _TITLE + " TEXT, " + _CONTENT + " TEXT, " + _DATE + " LONG  )";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCRIPT_TABLE_NAME);
        onCreate(db);
    }
}
{
}
*/
