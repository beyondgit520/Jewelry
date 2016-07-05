package com.app.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 李 on 16-7-5.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    private SparseArrayCompat<View> itemViews = new SparseArrayCompat();

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public <T extends View> T findView(int viewId) {
        View view = itemViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
        }
        return (T) view;
    }
}
