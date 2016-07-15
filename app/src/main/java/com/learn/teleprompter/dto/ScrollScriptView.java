package com.learn.teleprompter.dto;

import android.view.View;

/**
 * Created by E01090 on 7/15/2016.
 */
public class ScrollScriptView {
    private View view;
    private int scrollRate;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getScrollRate() {
        return scrollRate;
    }

    public void setScrollRate(int scrollRate) {
        this.scrollRate = scrollRate;
    }
}
