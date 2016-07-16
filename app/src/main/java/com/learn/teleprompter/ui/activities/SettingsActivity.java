package com.learn.teleprompter.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learn.teleprompter.R;
import com.learn.teleprompter.utils.CommonUtility;

/**
 * Created by E01090 on 7/15/2016.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mFontColor;
    private TextView mFontSize;
    private TextView mBGColor;
    private TextView mScrollRate;
    private int scrollRate[], fontSize[], fontColors[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mFontColor = (TextView) findViewById(R.id.font_color);
        mFontSize = (TextView) findViewById(R.id.font_size);
        mBGColor = (TextView) findViewById(R.id.bg_color);
        mScrollRate = (TextView) findViewById(R.id.scroll_rate);
        LinearLayout fontColorLayout = (LinearLayout) findViewById(R.id.fontColorLayout);
        fontColorLayout.setOnClickListener(this);
        LinearLayout fontSizeLayout = (LinearLayout) findViewById(R.id.fontSizeLayout);
        fontSizeLayout.setOnClickListener(this);
        LinearLayout bgColorLayout = (LinearLayout) findViewById(R.id.bgColorLayout);
        bgColorLayout.setOnClickListener(this);
        LinearLayout scrollRateLayout = (LinearLayout) findViewById(R.id.scrollRateLayout);
        scrollRateLayout.setOnClickListener(this);
        fontSize = new int[]{12, 16, 20, 24, 28, 32, 36, 40};
        scrollRate = new int[]{1,2,3,4,5,6,7,8,9};
        fontColors = new int[]{R.color.white, R.color.holo_red_light, R.color.holo_green_light,
                               R.color.holo_orange_light, R.color.holo_blue_light,
                               R.color.holo_purple, R.color.darker_gray, R.color.black};
        displaySettings();
    }

    private void showSelectFontSizeDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final String[] fontSizeArray = new String[fontSize.length];
        for(int i=0; i<fontSize.length; i++){
            fontSizeArray[i]=fontSize[i]+"";
        }

        alertDialogBuilder.setItems(fontSizeArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Log.d("Position", "pos" + pos);
                CommonUtility.writeIntSharedPreference(SettingsActivity.this, CommonUtility.FONT_SIZE, fontSize[pos]);
                mFontSize.setText(fontSizeArray[pos]);
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void showSelectScrollRateDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final String[] scrollrateArray = new String[scrollRate.length];
        for(int i=0; i<scrollRate.length; i++){
            scrollrateArray[i]=scrollRate[i]+"";
        }

        alertDialogBuilder.setItems(scrollrateArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Log.d("Position", "pos" + pos);
                CommonUtility.writeIntSharedPreference(SettingsActivity.this, CommonUtility.SCROLL_RATE, scrollRate[pos]);
                mScrollRate.setText(scrollrateArray[pos]);
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void showSelectColorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final String[] colorsArray = {"White", "Red", "Green", "Orange", "Blue", "Purple", "Gray", "Black"};
        alertDialogBuilder.setItems(colorsArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Log.d("Position", "pos" + pos);
                CommonUtility.writeIntSharedPreference(SettingsActivity.this, CommonUtility.FONT_COLOR, fontColors[pos]);
                mFontColor.setText(colorsArray[pos]);
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void showSelectBGColorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final String[] colorsArray = {"White", "Red", "Green", "Orange", "Blue", "Purple", "Gray", "Black"};
        alertDialogBuilder.setItems(colorsArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Log.d("Position", "pos" + pos);
                CommonUtility.writeIntSharedPreference(SettingsActivity.this, CommonUtility.BG_COLOR, fontColors[pos]);
                mBGColor.setText(colorsArray[pos]);
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fontColorLayout:
                showSelectColorDialog();
                break;
            case R.id.fontSizeLayout:
                showSelectFontSizeDialog();
                break;
            case R.id.bgColorLayout:
                showSelectBGColorDialog();
                break;
            case R.id.scrollRateLayout:
                showSelectScrollRateDialog();
                break;
        }
    }

    private void displaySettings(){
        int fontColor = CommonUtility.readIntSharedPreference(this, CommonUtility.FONT_COLOR);
        if(fontColor == -1){
            mFontColor.setText("White");
        } else {
            mFontColor.setText(getPreferenceColorText(fontColor));
        }
        int fontSize = CommonUtility.readIntSharedPreference(this, CommonUtility.FONT_SIZE);
        if(fontSize == -1){
            fontSize = 16;
        }
        mFontSize.setText(fontSize+"");
        int BGColor = CommonUtility.readIntSharedPreference(this, CommonUtility.BG_COLOR);
        if(BGColor == -1){
            mBGColor.setText("Black");
        } else {
            mBGColor.setText(getPreferenceColorText(BGColor));
        }

        int scrollRate = CommonUtility.readIntSharedPreference(this, CommonUtility.SCROLL_RATE);
        if(scrollRate == -1){
            scrollRate = 2;
        }
        mScrollRate.setText(scrollRate + "");
    }
    private String getPreferenceColorText(int color) {
        switch (color) {
            case R.color.white:
                return "White";
            case R.color.holo_red_light:
                return "Red";
            case R.color.holo_green_light:
                return "Green";
            case R.color.holo_blue_light:
                return "Blue";
            case R.color.black:
                return "Black";
            case R.color.darker_gray:
                return "Gray";
            case R.color.holo_purple:
                return "Purple";
        }
        return "Black";
    }
}
