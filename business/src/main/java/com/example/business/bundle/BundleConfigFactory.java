package com.example.business.bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by leon on 2018/5/15.
 */

public class BundleConfigFactory {
    private final static List<BundleConfigModel> BUNDLE_CONFIG_MODELS = new ArrayList<>();

    public static List<BundleConfigModel> getBundleConfigModels() {
        return BUNDLE_CONFIG_MODELS;
    }

    public static void configBundle(List<BundleConfigModel> configModels) {
        BUNDLE_CONFIG_MODELS.clear();
        BUNDLE_CONFIG_MODELS.addAll(configModels);
    }

    public static BundleConfigModel getBundleConfigModelByModuleName(String moduleName) {
        for (BundleConfigModel model :
                BUNDLE_CONFIG_MODELS) {
            if (model.moduleName.equalsIgnoreCase(moduleName)) {
                return model;
            }
        }
        return null;
    }
}
