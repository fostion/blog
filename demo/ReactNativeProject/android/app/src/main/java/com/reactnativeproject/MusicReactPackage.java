package com.reactnativeproject;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.reactnativeproject.module.MusicSearchModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注册
 * 在Java这边要做的最后一件事就是注册这个模块。我们需要在应用的Package类的createNativeModules方法中添加这个模块。如果模块没有被注册，它也无法在JavaScript中被访问到。
 * Created by Administrator on 2017/8/13.
 */

public class MusicReactPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new MusicSearchModule(reactContext));
        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
