package com.learn.teleprompter.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by E01090 on 7/13/2016.
 */
public class CommonUtility {
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
    public static void showAlertDialog(){

    }
}
