package com.learn.teleprompter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.learn.teleprompter.R;
import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.dto.Script;

/**
 * Created by E01090 on 7/13/2016.
 */
public class ViewScriptActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvContent;
    private Script scriptFromIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_script);
        setTitle(R.string.title_view_script);
        Button btnPromptScript = (Button)findViewById(R.id.btn_save);
        tvTitle = (TextView)findViewById(R.id.script_title);
        tvContent = (TextView)findViewById(R.id.script_content);
        scriptFromIntent = getIntent().getExtras().getParcelable("ViewScriptObj");
        tvTitle.setText(scriptFromIntent.title);
        tvContent.setText(scriptFromIntent.content);
        btnPromptScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPrompter();
            }
        });
    }

    private void launchPrompter(){
        Intent viewScriptIntent = new Intent(ViewScriptActivity.this, TeleprompterActivity.class);
        viewScriptIntent.putExtra("ViewScriptObj", scriptFromIntent);
        startActivity(viewScriptIntent);
    }
}
