package com.learn.teleprompter.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.learn.teleprompter.R;
import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.db.ScriptAsyncLoader;
import com.learn.teleprompter.dto.Script;
import com.learn.teleprompter.ui.adapters.ScriptListAdapter;
import com.learn.teleprompter.ui.listeners.IItemClickListener;
import com.learn.teleprompter.utils.CommonUtility;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

public class MyScriptsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Script>> {

    private RecyclerView mScriptsListView;
    private ScriptListAdapter mAdapter;
    private LoaderManager mLoaderManager;
    private int EDIT_SCRIPT_INVOKED = 1001;
    private int VIEW_SCRIPT_INVOKED = 1002;
    private int ADD_SCRIPT_INVOKED = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scripts);
        setTitle(R.string.title_my_scripts);
        mScriptsListView = (RecyclerView)findViewById(R.id.rv_scripts);
        FloatingActionButton addScript = (FloatingActionButton)findViewById(R.id.add_script);
        addScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MyScriptsActivity.this, AddScriptActivity.class);
                startActivityForResult(addIntent, ADD_SCRIPT_INVOKED);
            }
        });

        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);
        mLoaderManager.getLoader(0).forceLoad();
    }

    private void populateListData(final List<Script> resultList){
        IItemClickListener listener = new IItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent viewScriptIntent = new Intent(MyScriptsActivity.this, ViewScriptActivity.class);
                viewScriptIntent.putExtra("ViewScriptObj", resultList.get(position));
                startActivityForResult(viewScriptIntent, VIEW_SCRIPT_INVOKED);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScripDBUtils.getInstance(MyScriptsActivity.this).deleteScript(resultList.get(position));
                        mLoaderManager.getLoader(0).forceLoad();
                    }
                };
                DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                AlertDialog alertDialog = CommonUtility.getAlertDialog(MyScriptsActivity.this, getString(R.string.title_alert),
                        getString(R.string.confirmation_delete), getString(R.string.btn_txt_yes), positiveListener,
                        getString(R.string.btn_txt_no), negativeListener);
                alertDialog.show();
            }

            @Override
            public void onEditIconClick(View view, int position) {
                Intent editScriptIntent = new Intent(MyScriptsActivity.this, EditScriptActivity.class);
                editScriptIntent.putExtra("EditScriptObj", resultList.get(position));
                startActivityForResult(editScriptIntent, EDIT_SCRIPT_INVOKED);
            }
        };
        mAdapter = new ScriptListAdapter(MyScriptsActivity.this, resultList, listener);
        mScriptsListView.setHasFixedSize(true);
        mScriptsListView.setLayoutManager(new LinearLayoutManager(this));
        mScriptsListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Script>> onCreateLoader(int id, Bundle args) {
      /*
     * Takes action based on the ID of the Loader that's being created
     */
        switch (id) {
            case 0:
                return new ScriptAsyncLoader(MyScriptsActivity.this);
            default:
                // An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Script>> loader, List<Script> data) {
        populateListData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Script>> loader) {
        populateListData(new ArrayList<Script>());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoaderManager.getLoader(0).forceLoad();
    }
}
