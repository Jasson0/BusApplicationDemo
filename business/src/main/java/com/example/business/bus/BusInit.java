package com.example.business.bus;

import android.text.TextUtils;
import android.util.Log;

import com.example.business.bundle.BundleConfigFactory;
import com.example.business.bundle.BundleConfigModel;

import org.w3c.dom.Text;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Created by leon on 2018/5/15.
 */

public class BusInit implements BusObjectProvider {
    private HashMap<String, BusObject> hashMap = new HashMap<>();

    @Override
    public boolean register(BusObject busObject) {
        if (busObject == null) {
            return false;
        }
        String host = busObject.getPrefixAndHost().toLowerCase();
        if (hashMap.containsKey(host)) {
            Log.e("BusManager", host + "已存在，请勿重复注册");
        }
        hashMap.put(host, busObject);
        return true;
    }

    public static BusObject registerBusObjectWithHost(String host) {
        if (TextUtils.isEmpty(host)) {
            return null;
        }
        BundleConfigModel bundleConfigModel = BundleConfigFactory.getBundleConfigModelByModuleName(host);
        if (null == bundleConfigModel) {
            return null;
        }
        BusObject retObj = null;
        try {
            String className = bundleConfigModel.busObjectName;
            Class<BusObject> clazz = (Class<BusObject>) Class.forName(className);
            Constructor<BusObject> constructor = clazz.getConstructor(String.class);
            BusObject temRetObj = constructor.newInstance(host);
            if (Bus.register(temRetObj)) {
                retObj = temRetObj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retObj;
    }

    @Override
    public BusObject findBusObject(String hostName) {
        if (TextUtils.isEmpty(hostName)) {
            return null;
        }
        BusObject object = hashMap.get(hostName.toLowerCase());
        if (object == null) {
            object = registerBusObjectWithHost(hostName);
        }
        return object;
    }
}
