package com.learn.teleprompter.db;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.learn.teleprompter.dto.Script;

import java.util.List;

/**
 * Created by E01090 on 7/13/2016.
 */
public class ScriptAsyncLoader extends AsyncTaskLoader<List<Script>> {

    public ScriptAsyncLoader(Context context) {
        super(context);
    }

    @Override
    public List<Script> loadInBackground() {
        return ScripDBUtils.getInstance(getContext()).getScripts();
    }
}
