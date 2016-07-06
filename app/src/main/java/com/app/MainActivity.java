package com.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.base.BaseActivity;
import com.app.business.discover.DiscoverFragment;
import com.app.business.me.MeFragment;
import com.app.business.shop.ShopFragment;
import com.app.business.support.SupportFragment;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private final int[] tabTitles = {R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name};
    private final int[] tabIconNormal = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap
            .ic_launcher};
    private final int[] tabIconSelect = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap
            .ic_launcher};
    private ShopFragment shopFragment;
    private SupportFragment supportFragment;
    private DiscoverFragment discoverFragment;
    private MeFragment meFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 4; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_main_tab, tabLayout, false);
            AppCompatTextView tv = (AppCompatTextView) view.findViewById(R.id.tab_tv);
            AppCompatImageView iv = (AppCompatImageView) view.findViewById(R.id.tab_icon);
            iv.setImageResource(i == 0 ? tabIconSelect[0] : tabIconNormal[0]);
            tv.setText(tabTitles[i]);
            tv.setTextColor(Color.WHITE);
            tv.setVisibility(i == 0 ? View.GONE : View.VISIBLE);
            tabLayout.addTab(tabLayout.newTab().setCustomView(view), i == 0);
        }
        tabLayout.addOnTabSelectedListener(this);

        shopFragment = new ShopFragment();
        getFragmentManager().beginTransaction().add(R.id.container, shopFragment, "shopFragment").commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null) {
            AppCompatTextView tv = (AppCompatTextView) view.findViewById(R.id.tab_tv);
            tv.setVisibility(View.GONE);
            AppCompatImageView iv = (AppCompatImageView) view.findViewById(R.id.tab_icon);
            iv.setImageResource(tabIconSelect[tab.getPosition()]);
        }

        switch (tab.getPosition()) {
            case 0:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    fragmentTransaction.add(R.id.container, shopFragment, "shopFragment");
                } else {
                    fragmentTransaction.show(shopFragment);
                }
                break;
            case 1:
                if (supportFragment == null) {
                    supportFragment = new SupportFragment();
                    fragmentTransaction.add(R.id.container, supportFragment, "supportFragment");
                } else {
                    fragmentTransaction.show(supportFragment);
                }
                break;
            case 2:
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                    fragmentTransaction.add(R.id.container, discoverFragment, "discoverFragment");
                } else {
                    fragmentTransaction.show(discoverFragment);
                }
                break;
            case 3:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.container, meFragment, "meFragment");
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        View view = tab.getCustomView();
        if (view != null) {
            AppCompatTextView tv = (AppCompatTextView) view.findViewById(R.id.tab_tv);
            tv.setVisibility(View.VISIBLE);
            AppCompatImageView iv = (AppCompatImageView) view.findViewById(R.id.tab_icon);
            iv.setImageResource(tabIconNormal[tab.getPosition()]);
        }

        switch (tab.getPosition()) {
            case 0:
                if (shopFragment == null)
                    shopFragment = (ShopFragment) fragmentManager.findFragmentByTag("shopFragment");
                if (shopFragment != null)
                    fragmentTransaction.hide(shopFragment);
                break;
            case 1:
                if (supportFragment == null)
                    supportFragment = (SupportFragment) fragmentManager.findFragmentByTag("supportFragment");
                if (supportFragment != null)
                    fragmentTransaction.hide(supportFragment);
                break;
            case 2:
                if (discoverFragment == null)
                    discoverFragment = (DiscoverFragment) fragmentManager.findFragmentByTag("discoverFragment");
                if (discoverFragment != null)
                    fragmentTransaction.hide(discoverFragment);
                break;
            case 3:
                if (meFragment == null)
                    meFragment = (MeFragment) fragmentManager.findFragmentByTag("meFragment");
                if (meFragment != null)
                    fragmentTransaction.hide(meFragment);
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
