package com.learn.teleprompter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.learn.teleprompter.R;

import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.dto.Script;
import com.learn.teleprompter.dto.ScrollScriptView;
import com.learn.teleprompter.utils.CommonUtility;
import com.learn.teleprompter.utils.ScrollTask;

/**
 * Created by E01090 on 7/13/2016.
 */
public class TeleprompterActivity extends AppCompatActivity {

    private TextView tvContent;
    private Script scriptFromIntent;
    private ScrollTask mScrollTask;
    private ScrollView scrollView;
    private int scrollRate, fontSize, fontColor, BGColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_script);
        scriptFromIntent = getIntent().getExtras().getParcelable("ViewScriptObj");
        setTitle(scriptFromIntent.title);
        tvContent = (TextView)findViewById(R.id.script_content);
        scrollView = (ScrollView)findViewById(R.id.scroll_view);
        applySettings();
        tvContent.setText(scriptFromIntent.content);


        mScrollTask = new ScrollTask();
        ScrollScriptView scrollObj = new ScrollScriptView();
        scrollObj.setScrollRate(6);
        scrollObj.setView(scrollView);
        mScrollTask.execute(scrollObj);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.prompter_settings:
                launchSettingsScreen();
                break;
            case R.id.prompter_refresh:
                restartPropter();
                break;
        }

        return true;
    }

    private void launchSettingsScreen(){
        Intent settingIntent = new Intent(TeleprompterActivity.this, SettingsActivity.class);
        startActivityForResult(settingIntent, 1001);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001){
            applySettings();
        }
    }

    private void applySettings(){
        fontColor = CommonUtility.readIntSharedPreference(this, CommonUtility.FONT_COLOR);
        if(fontColor == -1){
            fontColor = getResources().getColor(R.color.white);
        }
        fontSize = CommonUtility.readIntSharedPreference(this, CommonUtility.FONT_SIZE);
        if(fontSize == -1){
            fontSize = 16;
        }
        BGColor = CommonUtility.readIntSharedPreference(this, CommonUtility.BG_COLOR);
        if(BGColor == -1){
            BGColor = getResources().getColor(R.color.black);
        }
        scrollRate = CommonUtility.readIntSharedPreference(this, CommonUtility.SCROLL_RATE);
        if(scrollRate == -1){
            scrollRate = 2;
        }
        tvContent.setTextColor(fontColor);
        tvContent.setTextSize(fontSize);
        tvContent.setBackgroundColor(BGColor);
        scrollView.setBackgroundColor(BGColor);
    }
    private void restartPropter(){

    }
}
