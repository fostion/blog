package cc.fs.sample.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 应用在隐藏后监听home键点击
 */
public class HomeWatcher {
    static final String TAG = "HomeWatcher";
    private Context mContext;
    private IntentFilter mFilter;
    private HomeReceiver mReceiver;
    private OnHomeKeyListener mOnHomeKeyListener;

    public HomeWatcher(Context context,OnHomeKeyListener onHomeKeyListener){
        this.mContext = context;
        this.mOnHomeKeyListener = onHomeKeyListener;
        this.mReceiver = new HomeReceiver();
        //监听
        this.mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    //开始监控
    public void startWatch(){
        if(mContext != null && mReceiver != null && mFilter != null){
            mContext.registerReceiver(mReceiver,mFilter);
        }
    }

    //停止监控
    public void stopWatch(){
        if(mContext != null && mReceiver != null){
            mContext.unregisterReceiver(mReceiver);
        }
    }

    private class HomeReceiver extends BroadcastReceiver{
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";//长按home键
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";//短按home键

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null){
                return;
            }
            String action = intent.getAction();
            if(action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra("reason");
                if(reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)){
                    //短按home键
                    if(mOnHomeKeyListener != null){
                        mOnHomeKeyListener.run();
                    }
                } else if(reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)){
                    //长按home键
                    if(mOnHomeKeyListener != null){
                        mOnHomeKeyListener.run();
                    }
                }
            }
        }
    }

    public interface OnHomeKeyListener{
        void run();
    }
}
