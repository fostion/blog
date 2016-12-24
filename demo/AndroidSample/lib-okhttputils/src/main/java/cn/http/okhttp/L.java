package cn.http.okhttp;

import android.text.TextUtils;
import android.util.Log;

/**
 * 打印类帮助,自动屏蔽上线打印
 * 支持接收广播后输出打印
 */
public class L {
    private static boolean isCanLog = BuildConfig.DEBUG;

    public static void openLog(){
        isCanLog = true;
    }

    public static void closeLog(){
        isCanLog = false;
    }

    public static void d(String tag, String content) {
        if (!isCanLog || TextUtils.isEmpty(tag) || TextUtils.isEmpty(content))
            return;

        Log.d(tag, content);
    }

    public static void i(String tag, String content) {
        if (!isCanLog || TextUtils.isEmpty(tag) || TextUtils.isEmpty(content))
            return;

        Log.i(tag, content);
    }

    public static void v(String tag, String content) {
        if (!isCanLog || TextUtils.isEmpty(tag) || TextUtils.isEmpty(content))
            return;

        Log.v(tag, content);
    }

    public static void w(String tag, String content) {
        if (!isCanLog || TextUtils.isEmpty(tag) || TextUtils.isEmpty(content))
            return;

        Log.w(tag, content);
    }

    public static void e(String tag, String content) {
        if (!isCanLog || TextUtils.isEmpty(tag) || TextUtils.isEmpty(content))
            return;

        Log.e(tag, content);
    }
}
