package cc.fs.sample.anr_monitor.ui_monitor;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * UI ANR监视器
 * 介绍：
 * 资料：http://blog.zhaiyifan.cn/2016/01/16/BlockCanaryTransparentPerformanceMonitor/?utm_source=tuicool&utm_medium=referral
 * 资料： https://github.com/markzhai/AndroidPerformanceMonitor
 * 资料：https://gist.github.com/dodola/7677cdbd0759a9d603b4
 * 资料： https://github.com/SalomonBrys/ANR-WatchDog/blob/master/anr-watchdog/src/main/java/com/github/anrwatchdog/ANRWatchDog.java
 * 资料： https://github.com/fengcunhan/BlockCanary/blob/master/app/src/main/java/com/weidian/blockcanary/StackInfoCatcher.java
 * 原理：主线程更新ui需要调用looper，而且会在执行前后输入具体log,原理就是通过检测处理message后前后时间判断是否ANR
 *
 * 缺陷：只适合手机检测，交互方式，对于开发Android TV盒子，使用键盘、遥控作为输入设备无法检测
 */
public class UIHandlerMonitor extends Thread {
    private Handler mainUIHandler; //主线程ui
    private int lastPreTick; //上次计数
    private int lastNextTick; //本次计数
    private int anrTime = 3000;//默认ANR时间是5000毫秒
    private boolean isInterrupted = false; //是否停止检测

    public UIHandlerMonitor() {
        //绑定主线程UI
        mainUIHandler = new Handler(Looper.myLooper());
    }

    //上次计数器
    private Runnable lastPreRunnable = new Runnable() {
        @Override
        public void run() {
            lastPreTick = (lastPreTick + 1) % 10;

        }
    };

    //本次计数器
    private Runnable lastNextRunnable = new Runnable() {
        @Override
        public void run() {
            lastNextTick = (lastNextTick + 1) % 10;
        }
    };

    @Override
    public void run() {
        //判断是否ANR
        boolean mayANR = false;
        while (!isInterrupted) {
            //记录上次触发次数
            int lastPreTimeTick = lastPreTick;
            int lastNextTimeTick = lastNextTick;
            //向主线程发送消息
            mainUIHandler.post(lastPreRunnable);
            mainUIHandler.post(lastNextRunnable);
            try {
                //线程sleep待主线程触发lastPreRunnable
                Thread.sleep(50);

                //主线程未执行更新操作，可能ANR
                if (lastPreTimeTick == lastPreTick) {
                    mayANR = true;
                    //开始记录ANR出现位置
                    L("可能触发ANR");
                }

                if (mayANR) {
                    //重置
                    mayANR = false;
                    //判断在设定时间内是否有更新
                    Thread.sleep(anrTime);
                    //计数器不变，确认为ANR
                    if (lastNextTick == lastNextTimeTick) {
                        L("触发ANR,报错");
                        //结束记录ANR位置
                        printANRStackTrace(Looper.getMainLooper().getThread().getStackTrace());
                    } else {
                        L("在检测时间内，未触发ANR");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //打印ANR具体位置
    private void printANRStackTrace(StackTraceElement[] elements) {
        StringBuilder result = new StringBuilder();
        if (null != elements && elements.length > 0) {
            for (int i = 0; i < elements.length; i++) {
                result.append("\tat ");
                result.append(elements[i].toString());
                result.append("\n");
            }
        }
        Log.e(" -StackTrace- ", result.toString());
    }

    void L(String msg) {
        Log.e("--[UIHandlerMonitor]--", msg);
    }


    public void startMonitor() {
        //开启线程
        start();
    }

    public void stopMonitor() {
        isInterrupted = true;
    }
}
