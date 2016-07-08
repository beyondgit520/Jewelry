package com.app.business.shop;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseFragment;
import com.app.business.shop.adapter.CategoryAdapter;
import com.app.business.shop.adapter.GoodsAdapter;
import com.app.business.shop.entity.CategoryEntity;
import com.app.business.shop.entity.GoodsEntity;
import com.app.utils.DensityUtil;
import com.app.utils.Logger;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends BaseFragment {

    @BindView(R.id.root) LinearLayout root;
    @BindView(R.id.ad_viewpager) ViewPager adViewpager;
    @BindView(R.id.today_gold_price) AppCompatTextView todayGoldPrice;
    @BindView(R.id.k_yellow) AppCompatTextView kYellow;
    @BindView(R.id.gold_weight) AppCompatTextView goldWeight;
    @BindView(R.id.lenth) AppCompatTextView lenth;
    @BindView(R.id.categray_view) RecyclerView categrayView;
    @BindView(R.id.color_recyclerView) LoopRecyclerViewPager colorRecyclerView;
    @BindView(R.id.color_indicator) View colorIndicator;
    @BindView(R.id.goods_recyclerView) RecyclerView goodsRecyclerView;
    @BindView(R.id.share_iv) ImageView shareIv;
    @BindView(R.id.location_tv) TextView locationTv;
    @BindView(R.id.topBar) RelativeLayout topBar;


    private ObjectAnimator floatAnimator;
    private List<CategoryEntity> categoryEntities = new ArrayList<>();
    private List<GoodsEntity> goodsEntities = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private int dy;
    private boolean hasConsume = false;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        for (int i = 0; i < 3; i++) {
            categoryEntities.add(new CategoryEntity());
            goodsEntities.add(new GoodsEntity());
        }
        init();
        return view;
    }

    private void init() {
        topBar.post(new Runnable() {
            @Override
            public void run() {
                root.getLayoutParams().height = BaseActivity.displayMetrics.heightPixels + (DensityUtil.dip2px
                        (mContext, 200) - topBar.getHeight());
            }
        });
        floatAnimator = new ObjectAnimator();//ObjectAnimator.ofFloat(styleFloatView, "translationY");
        floatAnimator.setTarget(root);
        floatAnimator.setPropertyName("translationY");
        floatAnimator.setDuration(600);

        CategoryAdapter categoryAdapter = new CategoryAdapter(R.layout.item_category, categoryEntities);
        GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_goods, goodsEntities);
        categrayView.setHasFixedSize(true);
        categrayView.setLayoutManager(new LinearLayoutManager(mContext));
        categrayView.setAdapter(categoryAdapter);

        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        goodsRecyclerView.setAdapter(goodsAdapter);
        goodsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Logger.d(tag, "newState:" + newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (!hasConsume) {
                        if (!floatAnimator.isRunning() && root.getTranslationY() == 0
                                && ((LinearLayoutManager) goodsRecyclerView.getLayoutManager())
                                .findLastVisibleItemPosition() == goodsEntities.size() - 1) {
                            floatAnimator.setFloatValues(0, -DensityUtil.dip2px(mContext, 200) + topBar.getHeight());
                            floatAnimator.start();
                        } else if (!floatAnimator.isRunning() && root.getTranslationY() < 0
                                && ((LinearLayoutManager) goodsRecyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
                            floatAnimator.setFloatValues(root.getTranslationY(), 0);
                            floatAnimator.start();
                        }
                    }
                    hasConsume = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ShopFragment.this.dy = dy;
                Logger.d(tag, "dy:" + dy);

                if (dy > 0 && !floatAnimator.isRunning() && root.getTranslationY() == 0) {
                    floatAnimator.setFloatValues(0, -DensityUtil.dip2px(mContext, 200) + topBar.getHeight());
                    floatAnimator.start();
                    hasConsume = true;
                } else if (dy < 0 && !floatAnimator.isRunning() && root.getTranslationY() < 0 &&
                        ((LinearLayoutManager) goodsRecyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
                    floatAnimator.setFloatValues(root.getTranslationY(), 0);
                    floatAnimator.start();
                    hasConsume = true;
                }
            }
        });
    }


}
