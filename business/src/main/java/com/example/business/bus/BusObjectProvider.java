package com.example.business.bus;

/**
 * Created by leon on 2018/5/15.
 */

public interface BusObjectProvider {
    boolean register(BusObject var1);

    BusObject findBusObject(String var1);
}
