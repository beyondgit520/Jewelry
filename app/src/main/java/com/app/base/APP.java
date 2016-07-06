package com.app.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/7/6.
 */
public class APP extends Application {
    @Override public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
