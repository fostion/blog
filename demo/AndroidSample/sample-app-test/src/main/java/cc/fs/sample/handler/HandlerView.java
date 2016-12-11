package cc.fs.sample.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import cc.fs.sample.utils.L;

//通过 adb logcat -v time -s HandlerView 查看log情况
public class HandlerView extends View {
    private final String TAG = "HandlerView";
    private int keepRun = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != keepRun) {
                L.i(TAG,"结束消息");
                return;
            }

            if (tasks == null || tasks.isEmpty()) {
                L.i(TAG,"结束消息");
                return;
            }
            for (Task task : tasks) {
                runTask(task);
            }
            //每秒执行一次
            sendEmptyMessageDelayed(keepRun, 1000);
        }
    };

    public void runTask(Task task) {
        long curTime = System.currentTimeMillis() / 1000;

        //超过执行时间
        if (curTime < task.statrTime || curTime > task.endTime) {
            return;
        }

        //判断显示次数
        if (task.showedTime >= task.showTime) {
            L.i(TAG,"超过显示次数,移除："+task.id);
            tasks.remove(task);
            return;
        }

        //执行操作
        if(task.state == 1 && curTime - task.lastStartTime >= task.duration){
            task.state = 0;
            task.lastStopTime = curTime;
            L.i(TAG,"隐藏任务");
        }

        if(task.state == 0 && curTime - task.lastStopTime >= task.internal){
            task.state = 1;
            task.showedTime++;
            task.lastStartTime = curTime;
            L.i(TAG,"显示任务");
        }
    }

    public HandlerView(Context context) {
        this(context, null);
    }

    public HandlerView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HandlerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    public void release(){
        if(mHandler.hasMessages(keepRun)){
            mHandler.removeMessages(keepRun);
        }

        mHandler.sendEmptyMessage(-1);
    }

    private void init() {
        tasks.add(new Task("任务一", System.currentTimeMillis() / 1000 + 5, System.currentTimeMillis() / 1000 + 200, 5, 6, 100000));
//        tasks.add(new Task("任务二",System.currentTimeMillis()/1000 + 20,System.currentTimeMillis()/1000+200,20,40,10));
        mHandler.sendEmptyMessage(keepRun);
    }

    List<Task> tasks = new ArrayList<>();

    class Task {
        String id;
        int state; // 0 代表隐藏， 1 代表显示
        long lastStartTime; //上次开始时间s
        long lastStopTime;//上次结束时间s
        int showedTime; //已经显示次数

        long statrTime; //开始执行时间s
        long endTime;//结束执行时间s
        int duration;//执行过程s
        int internal;//重新执行间隔
        int showTime;//总显示次数

        public Task(String id, long startTime, long endTime, int duration, int internal, int showTime) {
            this.id = id;
            this.statrTime = startTime;
            this.endTime = endTime;
            this.duration = duration;
            this.internal = internal;
            this.showTime = showTime;
        }
    }
}
