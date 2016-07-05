package com.app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by 李 on 16-7-5.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> datas;
    protected Context context;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
