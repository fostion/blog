package cc.fs.sample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cc.fs.sample.utils.L;

/**
 * Log开启监听器
 * 0:开启
 * 1:关闭
 * adb shell am broadcast -a cc.fs.sample.receiver.openLog --es PACKAGE_NAME cc.fs.sample --ei num 1
 */
public class LogBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }

        int order = intent.getIntExtra("num", 0);
        switch (order) {
            case 0:
                //开启
                L.openLog();
                break;

            case 1:
                //关闭
                L.closeLog();
                break;
        }
    }
}
