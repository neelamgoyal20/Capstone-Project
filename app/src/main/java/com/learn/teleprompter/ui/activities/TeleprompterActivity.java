package com.learn.teleprompter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.learn.teleprompter.R;

import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.dto.Script;
import com.learn.teleprompter.dto.ScrollScriptView;
import com.learn.teleprompter.utils.ScrollTask;

/**
 * Created by E01090 on 7/13/2016.
 */
public class TeleprompterActivity extends AppCompatActivity {

    private TextView tvContent;
    private Script scriptFromIntent;
    private ScrollTask mScrollTask;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_script);
        scriptFromIntent = getIntent().getExtras().getParcelable("ViewScriptObj");
        setTitle(scriptFromIntent.title);
        tvContent = (TextView)findViewById(R.id.script_content);
        scrollView = (ScrollView)findViewById(R.id.scroll_view);
        tvContent.setText(scriptFromIntent.content);

        mScrollTask = new ScrollTask();
        ScrollScriptView scrollObj = new ScrollScriptView();
        scrollObj.setScrollRate(6);
        scrollObj.setView(scrollView);
        mScrollTask.execute(scrollObj);
    }

}
