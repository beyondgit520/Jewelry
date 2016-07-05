package com.app.business.discover;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.business.discover.entity.DiscoverEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李 on 16-7-5.
 */
public class DiscoverAdapter extends BaseRecyclerViewAdapter<DiscoverEntity, DiscoverAdapter.DiscoverHolder> {


    public DiscoverAdapter(Context context) {
        super(context);
    }

    @Override
    public DiscoverAdapter.DiscoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discover, parent, false);
        DiscoverHolder holder = new DiscoverHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverAdapter.DiscoverHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class DiscoverHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.discover_iv) AppCompatImageView discoverIv;
        @BindView(R.id.discover_title) AppCompatTextView discoverTitle;
        @BindView(R.id.discover_content) TextView discoverContent;
        @BindView(R.id.discover_praise) AppCompatTextView discoverPraise;
        @BindView(R.id.discover_comment) AppCompatTextView discoverComment;
        @BindView(R.id.discover_detail) AppCompatTextView discoverDetail;

        public DiscoverHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
