package com.learn.teleprompter.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.learn.teleprompter.R;
import com.learn.teleprompter.service.TeleprompterService;

/**
 * Created by E01090 on 7/15/2016.
 */
public class TeleprompterWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        Intent intent = new Intent(context, TeleprompterService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[0]);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        rv.setRemoteAdapter(appWidgetIds[0], R.id.stack_view, intent);
        appWidgetManager.updateAppWidget(appWidgetIds[0], rv);

    }
}
