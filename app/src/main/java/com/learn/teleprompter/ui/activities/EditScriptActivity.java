package com.learn.teleprompter.ui.activities;

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
public class EditScriptActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText edtContent;
    private Script scriptFromIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_script);
        setTitle(R.string.title_edit_script);
        Button btnUpdateScript = (Button)findViewById(R.id.btn_save);
        tvTitle = (TextView)findViewById(R.id.script_title);
        edtContent = (EditText)findViewById(R.id.script_content);

        scriptFromIntent = getIntent().getExtras().getParcelable("EditScriptObj");
        tvTitle.setText(scriptFromIntent.title);
        edtContent.setText(scriptFromIntent.content);
        btnUpdateScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScript();
            }
        });
    }

    private void updateScript(){
        scriptFromIntent.content = edtContent.getText().toString();
        ScripDBUtils.getInstance(this).updateScript(scriptFromIntent);
        finish();
    }
}
