package com.learn.teleprompter.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.learn.teleprompter.R;
import com.learn.teleprompter.db.ScripDBUtils;
import com.learn.teleprompter.dto.Script;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by E01090 on 7/15/2016.
 */
public class TeleprompterService extends RemoteViewsService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TeleprompterWidgetRemoteViewsFactory(this);
    }

    class TeleprompterWidgetRemoteViewsFactory implements RemoteViewsFactory{
        private Context mContext;
        private List<Script> mScriptList = new ArrayList<>();

        public TeleprompterWidgetRemoteViewsFactory(Context ctx){
            this.mContext = ctx;
        }
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            mScriptList = ScripDBUtils.getInstance(mContext).getScripts();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mScriptList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if(mScriptList.size()==0){
                return null;
            }
            RemoteViews scriptView = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_layout);

            Script script = mScriptList.get(position);
            scriptView.setTextViewText(R.id.scriptTitle, script.title);
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            String dateStr = dateFormat.format(script.date);
            scriptView.setTextViewText(R.id.scriptDate, dateStr);
            return scriptView;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
