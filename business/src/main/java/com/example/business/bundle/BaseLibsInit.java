package com.example.business.bundle;

import android.app.Application;
import android.content.Context;

import com.example.business.bus.BusInit;
import com.example.business.bus.BusManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 2018/5/15.
 */

public class BaseLibsInit {
    public static void init(Application application) {
        initBusAndBundle(application);
    }

    private static void initBusAndBundle(Application application) {
        BusInit busInit = new BusInit();

        //Bus manager init
        BusManager.init(busInit);

        //all bundles setted
        configBundles(application, busInit);
    }

    private static void configBundles(Context context, BusInit busInit) {
        List<BundleConfigModel> bundleConfigModels = new ArrayList<>();

        //Module1
        bundleConfigModels.add(new BundleConfigModel(
                "leonmodule1",
                "com.example.leonmodule1",
                "com.example.leonmodule1.bus.ModuleOneBusObject",
                BundleConfigModel.BundleLoadType.AutoLoad
        ));
        //Module2
        bundleConfigModels.add(new BundleConfigModel(
                "leonmodule2",
                "com.example.leonmodule2",
                "com.example.leonmodule2.bus.ModuleTwoBusObject",
                BundleConfigModel.BundleLoadType.AutoLoad
        ));

        BundleConfigFactory.configBundle(bundleConfigModels);
    }
}
