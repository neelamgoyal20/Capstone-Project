package com.learn.teleprompter.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by E01090 on 7/12/2016.
 */
public class ScriptContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.learn.teleprompter";
    static final String URL = "content://" + PROVIDER_NAME + "/scripts";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String _TITLE = "title";
    static final String _CONTENT = "content";
    static final String _DATE = "date";


    // projection map for a query
    private HashMap<String, String> PROJECTION_MAP;

    static final int SCRIPTS = 1;
    static final int SCRIPT_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "scripts", SCRIPTS);
        uriMatcher.addURI(PROVIDER_NAME, "scripts/#", SCRIPT_ID);
    }

    /**
     * Database specific constant declarations
     */
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "scripts_db";
    static final String SCRIPT_TABLE_NAME = "scripts_tbl";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + SCRIPT_TABLE_NAME + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + _TITLE + " TEXT, " + _CONTENT + " TEXT, " + _DATE + " LONG  )";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
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

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        if(db == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(SCRIPT_TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(SCRIPT_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case SCRIPTS:
                qb.setProjectionMap(PROJECTION_MAP);
                break;

            case SCRIPT_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }


        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case SCRIPTS:
                count = db.delete(SCRIPT_TABLE_NAME, selection, selectionArgs);
                break;

            case SCRIPT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(SCRIPT_TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case SCRIPTS:
                count = db.update(SCRIPT_TABLE_NAME, values, selection, selectionArgs);
                break;

            case SCRIPT_ID:
                count = db.update(SCRIPT_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            /**
             * Get all  records
             */
            case SCRIPTS:
                return "vnd.android.cursor.dir/vnd.teleprompter.scripts";

            /**
             * Get a particular
             */
            case SCRIPT_ID:
                return "vnd.android.cursor.item/vnd.teleprompter.scripts";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}