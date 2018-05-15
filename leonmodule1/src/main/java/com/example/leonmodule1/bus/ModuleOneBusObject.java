package com.example.leonmodule1.bus;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.business.bus.BusObject;
import com.example.leonmodule1.MainActivity;

/**
 * Created by leon on 2018/5/15.
 */

public class ModuleOneBusObject extends BusObject {
    public ModuleOneBusObject(String host) {
        super(host);
    }

    @Override
    public Object doDataJob(Context context, String bizName, Object... var3) {
        if (TextUtils.equals(bizName, "leonmodule1/test")) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        return null;
    }
}
