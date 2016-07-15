package com.learn.teleprompter;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by E01090 on 7/15/2016.
 */
public class TeleprompterApplication extends Application {
    private Tracker mTeleprompterTracker;

    synchronized public Tracker getDefaultTracker(){
        if(mTeleprompterTracker == null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTeleprompterTracker = analytics.newTracker(R.xml.app_tracker);
        }
        return mTeleprompterTracker;
    }
}
