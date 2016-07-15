package com.learn.teleprompter.utils;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import com.learn.teleprompter.R;
import com.learn.teleprompter.dto.ScrollScriptView;

/**
 * Created by E01090 on 7/15/2016.
 */
public class ScrollTask extends AsyncTask<ScrollScriptView, ScrollScriptView, View> {
    private boolean mViewCanScroll;

    public ScrollTask() {
        super();
        this.mViewCanScroll = true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(View view) {
        Snackbar.make(
                view,
                R.string.script_ended,
                Snackbar.LENGTH_SHORT
        ).show();

    }

    @Override
    protected void onProgressUpdate(ScrollScriptView... values) {
        View view = values[0].getView();
        int scrollRate = values[0].getScrollRate();
        if(view.canScrollVertically(scrollRate)){
            view.scrollBy(0, scrollRate);
        } else {
            mViewCanScroll = false;
        }
    }

    @Override
    protected View doInBackground(ScrollScriptView... params) {
        View view = params[0].getView();
        int scrollRate = params[0].getScrollRate();
        while(mViewCanScroll){
            try{
                Thread.sleep(50);
            } catch (InterruptedException e){
                Log.e(getClass().getSimpleName(), e.getMessage());
            }
            ScrollScriptView scrollObj = new ScrollScriptView();
            scrollObj.setView(view);
            scrollObj.setScrollRate(scrollRate);
            publishProgress(scrollObj);
        }
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return view;
    }
}
