package cc.fs.sample.uimonitor.anr_monitor.choreographer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Choreographer;

import java.util.concurrent.TimeUnit;

/**
 * Android 4.1后UI更新消息统一调度
 * 资料：http://blog.csdn.net/yangwen123/article/details/39518923
 * fps资料：http://blog.csdn.net/u012165769/article/details/52860101
 * fps计算：https://github.com/wasabeef/Takt
 */
public class UIChoMonitor implements Choreographer.FrameCallback {

    private long frameStartTime = 0;//每一帧开始时间
    private long framesRendered = 0;//某段时间内帧数
    private int interval = 500;//每500毫秒计算一次fps
    private Choreographer choreographer;
    private Handler handler;
    private int minFPS = 45;

    public UIChoMonitor() {
        choreographer = Choreographer.getInstance();
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    //todo 暂时不准 需要继承重写
                    printANRStackTrace(Looper.getMainLooper().getThread().getStackTrace());
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
        };
    }

    public void start() {
        //开始监听下一帧
        choreographer.postFrameCallback(this);
    }

    public void stop() {
        frameStartTime = 0;
        framesRendered = 0;
        choreographer.removeFrameCallback(this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        //页面刷新消息传递
        long currentFrameTime = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos);
        if (frameStartTime > 0) {
            long timeSpan = currentFrameTime - frameStartTime;
            //记录刷新的帧数
            framesRendered++;

            if (timeSpan > interval) {
                //fps计算方式 某个时间段刷新帧/某个时间段
                final double fps = framesRendered * 1000 / (double) timeSpan;
                frameStartTime = currentFrameTime;
                framesRendered = 0;
                //maybe ANR
                if (fps < minFPS) {
                    Log.e("---", "界面可能ANR");
                    handler.sendEmptyMessage(0);
                }
            }
        } else {
            frameStartTime = currentFrameTime;
        }
        //执行下一帧
        choreographer.postFrameCallback(this);
    }


}
