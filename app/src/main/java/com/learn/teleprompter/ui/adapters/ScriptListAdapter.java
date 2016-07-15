package com.learn.teleprompter.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.learn.teleprompter.R;
import com.learn.teleprompter.dto.Script;
import com.learn.teleprompter.ui.listeners.IItemClickListener;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by E01090 on 7/13/2016.
 */
public class ScriptListAdapter extends RecyclerView.Adapter<ScriptListAdapter.ViewHolder> {

    private Context mContext;
    private List<Script> mData;
    private IItemClickListener mItemClickListener;

    public ScriptListAdapter(Context ctx, List<Script> data, IItemClickListener listener) {
        this.mContext = ctx;
        this.mData = data;
        mItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.script_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Script scriptObj = mData.get(position);
        holder.title.setText(scriptObj.title);
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String dateStr = dateFormat.format(scriptObj.date);
        holder.date.setText(dateStr);
    }

    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public RelativeLayout cardView;
        public TextView title;
        public TextView date;
        public ImageView editBtn;
        public ViewHolder(View view) {
            super(view);
            cardView = (RelativeLayout) view.findViewById(R.id.scriptItem);
            title = (TextView) view.findViewById(R.id.scriptTitle);
            date = (TextView) view.findViewById(R.id.scriptDate);
            editBtn = (ImageView)view.findViewById(R.id.btn_edit);
            editBtn.setOnClickListener(this);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                switch (v.getId()) {
                    case R.id.btn_edit:
                        mItemClickListener.onEditIconClick(v, getLayoutPosition());
                        break;
                    default:
                        mItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemLongClick(view, getLayoutPosition());
            }
            return true;
        }
    }
}
