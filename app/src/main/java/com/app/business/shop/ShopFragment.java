package com.app.business.shop;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;
import com.app.Test;
import com.app.base.BaseActivity;
import com.app.base.BaseFragment;
import com.app.business.shop.adapter.CategoryAdapter;
import com.app.business.shop.adapter.ColorViewAdapter;
import com.app.business.shop.adapter.GoodsAdapter;
import com.app.business.shop.entity.CategoryEntity;
import com.app.business.shop.entity.ColorEntity;
import com.app.business.shop.entity.GoodsEntity;
import com.app.utils.DensityUtil;
import com.app.utils.Logger;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private List<ColorEntity> colorEntities = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private GoodsAdapter goodsAdapter;
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
        for (int i = 0; i < 10; i++) {
            categoryEntities.add(new CategoryEntity());
            goodsEntities.add(new GoodsEntity());
            if (i < 3) {
                ColorEntity colorEntity = new ColorEntity();
                colorEntity.image = Test.images[i];
                colorEntities.add(colorEntity);
            }

        }
        init();
        return view;
    }

    @OnClick({R.id.k_yellow, R.id.gold_weight, R.id.lenth}) void click(View view) {
        switch (view.getId()) {
            case R.id.k_yellow: {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                Menu menu = popup.getMenu();
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.color_filter_menu, menu);

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(mContext, item.getItemId() + "", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
            break;
            case R.id.gold_weight: {

            }
            break;
            case R.id.lenth: {

            }
            break;


        }
    }

    private void init() {
        topBar.post(new Runnable() {
            @Override
            public void run() {
                root.getLayoutParams().height = BaseActivity.displayMetrics.heightPixels + (DensityUtil.dip2px
                        (mContext, 200) - topBar.getHeight());
            }
        });
        initColorView();
        floatAnimator = new ObjectAnimator();//ObjectAnimator.ofFloat(styleFloatView, "translationY");
        floatAnimator.setTarget(root);
        floatAnimator.setPropertyName("translationY");
        floatAnimator.setDuration(600);

        categoryAdapter = new CategoryAdapter(R.layout.item_category, categoryEntities);
        goodsAdapter = new GoodsAdapter(R.layout.item_goods, goodsEntities);
        categrayView.setHasFixedSize(true);
        categrayView.setLayoutManager(new LinearLayoutManager(mContext));
        categrayView.setAdapter(categoryAdapter);
        categrayView.addOnScrollListener(onScrollListener);
        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        goodsRecyclerView.setAdapter(goodsAdapter);
        goodsRecyclerView.addOnScrollListener(onScrollListener);


    }

    private void initPopMenu() {

    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Logger.d(tag, "newState:" + newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                if (!hasConsume) {
                    if (!floatAnimator.isRunning() && root.getTranslationY() == 0
                            && ((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findLastVisibleItemPosition() == goodsEntities.size() - 1) {
                        floatAnimator.setFloatValues(0, -DensityUtil.dip2px(mContext, 200) + topBar.getHeight());
                        floatAnimator.start();
                    } else if (!floatAnimator.isRunning() && root.getTranslationY() < 0
                            && ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
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
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
                floatAnimator.setFloatValues(root.getTranslationY(), 0);
                floatAnimator.start();
                hasConsume = true;
            }
        }
    };

    private void initColorView() {
        ColorViewAdapter colorViewAdapter = new ColorViewAdapter(mContext);
        colorViewAdapter.setData(colorEntities);
        LinearLayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        colorRecyclerView.setTriggerOffset(0.50f);
        colorRecyclerView.setFlingFactor(0.25f);
        colorRecyclerView.setSinglePageFling(true);
        colorRecyclerView.setLayoutManager(layout);
        colorRecyclerView.setAdapter(colorViewAdapter);
        colorRecyclerView.setHasFixedSize(true);
        colorRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override public void OnPageChanged(int i, int i1) {
                Toast.makeText(mContext, "i:" + i % 3 + "   i1:" + i1 % 3, Toast.LENGTH_SHORT).show();
                switch (i1 % 3) {
                    case 0:
                        colorIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
        colorRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int childCount = colorRecyclerView.getChildCount();
                int width = colorRecyclerView.getChildAt(0).getWidth();
                int padding = (colorRecyclerView.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        colorRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (colorRecyclerView.getChildCount() < 3) {
                    if (colorRecyclerView.getChildAt(1) != null) {
                        View v1 = colorRecyclerView.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (colorRecyclerView.getChildAt(0) != null) {
                        View v0 = colorRecyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (colorRecyclerView.getChildAt(2) != null) {
                        View v2 = colorRecyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }

            }
        });
    }

}
