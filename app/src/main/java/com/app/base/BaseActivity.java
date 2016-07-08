package com.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/7/3.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected String tag;
    public static DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        tag = this.getClass().getSimpleName();
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
