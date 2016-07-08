package com.app.business.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.base.BaseViewHolder;
import com.app.business.shop.entity.ColorEntity;
import com.app.utils.DensityUtil;
import com.app.utils.Logger;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ColorViewAdapter extends BaseRecyclerViewAdapter<ColorEntity, BaseViewHolder> {


    public ColorViewAdapter(Context context) {
        super(context);
    }

    @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_color, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
        Logger.d("colorview","position:"+position);
        ColorEntity colorEntity = datas.get(position);
        SimpleDraweeView imageView = holder.findView(R.id.color_imageview);
        imageView.getLayoutParams().width = BaseActivity.displayMetrics.widthPixels - DensityUtil.dip2px(mContext, 70) - DensityUtil.dip2px(mContext, 120);
        imageView.setImageURI(Uri.parse(colorEntity.image));

    }
}
