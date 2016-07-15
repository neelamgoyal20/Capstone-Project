package com.learn.teleprompter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.learn.teleprompter.R;
import com.learn.teleprompter.TeleprompterApplication;
import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.dto.Script;

/**
 * Created by E01090 on 7/13/2016.
 */
public class AddScriptActivity extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_script);
        setTitle(R.string.title_add_script);
        Button btnAddScript = (Button)findViewById(R.id.btn_save);
        edtTitle = (EditText)findViewById(R.id.script_title);
        edtContent = (EditText)findViewById(R.id.script_content);

        btnAddScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertScript();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        TeleprompterApplication application = (TeleprompterApplication) getApplication();
        Tracker trackerObj = application.getDefaultTracker();
        trackerObj.setScreenName(getClass().getName());
        trackerObj.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void insertScript(){
        Script scriptObj = new Script();
        scriptObj.title = edtTitle.getText().toString();
        scriptObj.content = edtContent.getText().toString();
        scriptObj.date = System.currentTimeMillis();
        ScripDBUtils.getInstance(this).insertScript(scriptObj);
        finish();
    }
}
