package com.example.business.bus;

import android.content.Context;

/**
 * Created by leon on 2018/5/15.
 */

public abstract class BusObject {
    private String prefixAndHost;

    public BusObject(String host) {
        this.prefixAndHost = host;
    }

    public String getPrefixAndHost() {
        return this.prefixAndHost;
    }

    public abstract Object doDataJob(Context var1, String var2, Object... var3);
}
