package com.learn.teleprompter.ui.listeners;

import android.view.View;

public interface IItemClickListener {
    public void onItemClick(View view, int position);

    public void onItemLongClick(View view, int position);
}
