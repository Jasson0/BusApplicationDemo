package com.example.business;

import android.app.Application;
import android.content.Context;

import com.example.business.bundle.BaseLibsInit;

/**
 * Created by leon on 2018/5/15.
 */

public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BaseLibsInit.init(this);
    }
}
