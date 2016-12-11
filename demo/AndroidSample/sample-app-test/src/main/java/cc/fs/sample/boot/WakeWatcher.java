package cc.fs.sample.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 监听休眠唤醒
 */
public class WakeWatcher {
    static final String TAG = "WakeWatcher";
    private Context mContext;
    private IntentFilter mFilter;
    private WakeReceiver mReceiver;
    private OnWakeListener mOnWakeListener;
    private final String ACTION_SLEEP = Intent.ACTION_SCREEN_OFF;//监听休眠
    private final String ACTION_WAKE = Intent.ACTION_SCREEN_ON;//监听唤醒

    public WakeWatcher(Context context, OnWakeListener onWakeListener) {
        this.mContext = context;
        this.mOnWakeListener = onWakeListener;
        this.mReceiver = new WakeReceiver();
        this.mFilter = new IntentFilter();
        this.mFilter.addAction(ACTION_WAKE);
        this.mFilter.addAction(ACTION_SLEEP);
    }

    //开始监控
    public void startWatch() {
        if (mContext != null && mReceiver != null && mFilter != null) {
            mContext.registerReceiver(mReceiver, mFilter);
        }
    }

    //停止监控
    public void stopWatch() {
        if (mContext != null && mReceiver != null) {
            mContext.unregisterReceiver(mReceiver);
        }
    }

    private class WakeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }

            String action = intent.getAction();


            if (mOnWakeListener != null) {
                if (action.equals(ACTION_SLEEP)) {
                    mOnWakeListener.sleep();
                } else if(action.equals(ACTION_WAKE)) {
                    mOnWakeListener.wake();
                }
            }
        }
    }

    public interface OnWakeListener {
        void sleep();

        void wake();
    }
}
