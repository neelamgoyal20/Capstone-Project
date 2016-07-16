package com.learn.teleprompter.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by E01090 on 7/13/2016.
 */
public class CommonUtility {
    public static final String FONT_SIZE = "FONT_SIZE";
    public static final String FONT_COLOR = "FONT_COLOR";
    public static final String BG_COLOR = "BG_COLOR";
    public static final String SCROLL_RATE = "SCROLL_RATE";

    public static AlertDialog getAlertDialog(Context context, String title, String message, String btnPositive, DialogInterface.OnClickListener positiveClickListener, String btnNegative, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if (btnPositive != null) {
            builder.setPositiveButton(btnPositive, positiveClickListener);
        }
        if (btnNegative != null) {
            builder.setNegativeButton(btnNegative, negativeClickListener);
        }
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public static int readIntSharedPreference(Context ctx, String key)
    {
        SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(ctx);
        return sp.getInt(key, -1);
    }

    public static void writeIntSharedPreference(Context ctx,String key, int value){
        SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key, value);
        ed.commit();
    }
}
