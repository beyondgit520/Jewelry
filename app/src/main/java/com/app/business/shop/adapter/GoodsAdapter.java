package com.app.business.shop.adapter;

import android.content.Intent;
import android.view.View;

import com.app.R;
import com.app.business.shop.GoodsDetailActivity;
import com.app.business.shop.entity.GoodsEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 李 on 16-7-8.
 */
public class GoodsAdapter extends BaseQuickAdapter<GoodsEntity> {
    public GoodsAdapter(int layoutResId, List<GoodsEntity> data) {
        super(layoutResId, data);
    }

    public GoodsAdapter(List<GoodsEntity> data) {
        super(data);
    }

    public GoodsAdapter(View contentView, List<GoodsEntity> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsEntity goodsEntity) {
        baseViewHolder.setOnClickListener(R.id.item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
