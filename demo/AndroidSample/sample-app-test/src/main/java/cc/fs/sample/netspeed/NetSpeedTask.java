package cc.fs.sample.netspeed;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import cc.fs.sample.utils.L;

/**
 * 网络测试工具
 */

public class NetSpeedTask extends AsyncTask<NetSpeedTask.NetInfo, NetSpeedTask.NetInfo, NetSpeedTask.NetInfo> {
    private final String TAG = "NetSpeedTask";
    private final int BUFFER_SIZE = 3 * 1024;//3KB
    private final int TIME_INTERVAL = 1000;//1s计算一次当前网速

    private String resUrl;
    private NetInfo info;
    private OnNetSpeedTestResult netSpeedTestResult;

    public void start(String url,OnNetSpeedTestResult netSpeedTestResult) {
        this.resUrl = url;
        this.info = new NetInfo();
        this.netSpeedTestResult = netSpeedTestResult;
        this.execute();
    }

    @Override
    protected NetInfo doInBackground(NetInfo... params) {
        if (TextUtils.isEmpty(resUrl)) {
            return null;
        }

        URL url;
        URLConnection conn;
        InputStream itStream = null;
        try {
            url = new URL(resUrl);
            conn = url.openConnection();
            L.i(TAG, "total size1: " + (conn.getContentLength()));
            byte[] buff = new byte[BUFFER_SIZE];
            itStream = conn.getInputStream();
            int receiveSize = 0;//当前获取B
            info.startTime = System.currentTimeMillis();//记录开始时间
            info.totalSize = conn.getContentLength();//记录总大小
            int lastReceiveSize = 0;//上次1s,获取大小,用于计算当前速度
            long lastStartTime = info.startTime;
            long curTime = info.startTime;
            while ((receiveSize = itStream.read(buff)) != -1) {
                //获得数据
                info.curSize += receiveSize;
                lastReceiveSize += receiveSize;
                curTime = System.currentTimeMillis();
                //计算平均时间,单位1s
                if (curTime != info.startTime) {
                    info.avSpeend = info.curSize / (curTime - info.startTime) * 1000;
                }
                //每秒计算一次平均时间
                if (curTime - lastStartTime >= TIME_INTERVAL) {
                    //大于1s 计算平均时间
                    info.curSpeed = lastReceiveSize;
                    lastReceiveSize = 0; //重置
                    lastStartTime = curTime;//记录上次计算时间
                    publishProgress(info);
                }
            }

            info.endTime = System.currentTimeMillis();
            //计算平均时间,单位1s
            if (info.endTime != info.startTime) {
                info.avSpeend = info.totalSize / (info.endTime - info.startTime) * 1000;
            }
            buff = null;
            itStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (itStream != null) {
                    itStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    @Override
    protected void onProgressUpdate(NetInfo... values) {
        //通知界面更新
        if(netSpeedTestResult != null && values[0] != null){
            netSpeedTestResult.onUpdate(values[0]);
        }
    }

    @Override
    protected void onPostExecute(NetSpeedTask.NetInfo netInfo) {
        if(netSpeedTestResult != null && netInfo != null){
            netSpeedTestResult.onUpdate(netInfo);
        }
    }

    interface OnNetSpeedTestResult{
        void onUpdate(NetInfo info);
    }

    class NetInfo {
        long curSpeed; //当前速度
        long avSpeend; //平均速度
        long startTime; //开始时间
        long endTime; //结束时间
        long curSize; //文件当前大小
        long totalSize;//文件总大小
    }
}
