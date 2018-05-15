package com.example.leonmodule2.bus;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.business.bus.BusObject;
import com.example.leonmodule2.MainActivity;


/**
 * Created by leon on 2018/5/15.
 */

public class ModuleTwoBusObject extends BusObject {
    public ModuleTwoBusObject(String host) {
        super(host);
    }

    @Override
    public Object doDataJob(Context context, String bizName, Object... param) {
        if (TextUtils.equals(bizName, "leonmodule2/test")) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("displayParam",param[0].toString());
            context.startActivity(intent);
        }
        return null;
    }
}
