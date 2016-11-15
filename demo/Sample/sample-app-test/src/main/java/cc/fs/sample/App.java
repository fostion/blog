package cc.fs.sample;

import android.app.Application;

import cc.fs.sample.uimonitor.anr_monitor.ui_monitor.UIHandlerMonitor;

public class App  extends Application{

    UIHandlerMonitor uiMonitor;

    @Override
    public void onCreate() {
        super.onCreate();
        //todo
//        uiMonitor = new UIHandlerMonitor();
//        uiMonitor.startMonitor();
    }
}
