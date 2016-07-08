package com.app.business.shop.adapter;

import android.view.View;

import com.app.business.shop.entity.CategoryEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 李 on 16-7-8.
 */
public class CategoryAdapter extends BaseQuickAdapter<CategoryEntity> {


    public CategoryAdapter(int layoutResId, List<CategoryEntity> data) {
        super(layoutResId, data);
    }

    public CategoryAdapter(List<CategoryEntity> data) {
        super(data);
    }

    public CategoryAdapter(View contentView, List<CategoryEntity> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {

    }
}
