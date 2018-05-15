package com.example.business.bus;

import android.content.Context;

/**
 * Created by leon on 2018/5/15.
 */

public class Bus {
    public synchronized static boolean register(BusObject busObject) {
        return BusManager.register(busObject);
    }

    public static Object callData(Context context, String bizName, Object... param) {
        BusObject busObject = BusManager.findBusObject(BusManager.parseBizNameHost(bizName));
        try {
            if (busObject != null) {
                return busObject.doDataJob(context, bizName, param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
