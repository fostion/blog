package cc.fs.sample;

import android.app.Application;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.AndroidRefWatcherBuilder;
import com.squareup.leakcanary.LeakCanary;

import cc.fs.sample.leakcanary.LeakService;
import cc.fs.sample.uimonitor.anr_monitor.ui_monitor.UIHandlerMonitor;

public class App extends Application {

    UIHandlerMonitor uiMonitor;

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.refWatcher(this)
                .listenerServiceClass(LeakService.class)
                .excludedRefs(AndroidExcludedRefs.createAppDefaults().build())
                .buildAndInstall();


        //todo
//        uiMonitor = new UIHandlerMonitor();
//        uiMonitor.startMonitor();
    }
}
