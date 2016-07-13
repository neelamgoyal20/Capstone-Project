package com.learn.teleprompter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.learn.teleprompter.dto.Script;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E01090 on 7/13/2016.
 */
public class ScripDBUtils {
    private static ScripDBUtils scripDBUtils;
    private static Context mContext;

    public static ScripDBUtils getInstance(Context context) {
        if (scripDBUtils == null) {
            mContext = context;
            scripDBUtils = new ScripDBUtils();

        }
        return scripDBUtils;
    }

    public static List<Script> getScripts(){
        List scriptsList = new ArrayList();
        String order = ScriptContentProvider._TITLE + " ASC";

        Cursor cursor = mContext.getContentResolver().query(ScriptContentProvider.CONTENT_URI, null, null, null, order);

        Script script = null;
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                try {
                    cursor.moveToNext();
                    script = new Script();
                    script.id = cursor.getInt(cursor.getColumnIndex(ScriptContentProvider._ID));
                    script.title = cursor.getString(cursor.getColumnIndex(ScriptContentProvider._TITLE));
                    script.content = cursor.getString(cursor.getColumnIndex(ScriptContentProvider._CONTENT));
                    script.date = cursor.getLong(cursor.getColumnIndex(ScriptContentProvider._DATE));
                    scriptsList.add(script);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        cursor.close();
        return scriptsList;
    }

    public Script getScriptById(int id) {
        String selection = ScriptContentProvider._ID + "=" + id;
        Cursor cursor = mContext.getContentResolver().query(ScriptContentProvider.CONTENT_URI, null, selection, null, null);

        Script script = null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            script = new Script();
            script.id = cursor.getInt(cursor.getColumnIndex(ScriptContentProvider._ID));
            script.title = cursor.getString(cursor.getColumnIndex(ScriptContentProvider._TITLE));
            script.content = cursor.getString(cursor.getColumnIndex(ScriptContentProvider._CONTENT));
            script.date = cursor.getLong(cursor.getColumnIndex(ScriptContentProvider._DATE));

        }
        cursor.close();
        return script;
    }

    public void insertScript(Script item) {
        ContentValues values = new ContentValues();
        values.put(ScriptContentProvider._TITLE, item.title);
        values.put(ScriptContentProvider._CONTENT, item.content);
        values.put(ScriptContentProvider._DATE, String.valueOf(item.date));
        mContext.getContentResolver().insert(ScriptContentProvider.CONTENT_URI, values);
    }

    public void updateScript(Script item) {
        ContentValues values = new ContentValues();
        values.put(ScriptContentProvider._TITLE, item.title);
        values.put(ScriptContentProvider._CONTENT, item.content);
        values.put(ScriptContentProvider._DATE, String.valueOf(item.date));
        String selection = ScriptContentProvider._ID + "=" + item.id;
        mContext.getContentResolver().update(ScriptContentProvider.CONTENT_URI, values, selection, null);
    }

    public void deleteScript(Script item) {
        String selection = ScriptContentProvider._ID + "=" + item.id;
        mContext.getContentResolver().delete(ScriptContentProvider.CONTENT_URI,
                selection, null);
    }
}
