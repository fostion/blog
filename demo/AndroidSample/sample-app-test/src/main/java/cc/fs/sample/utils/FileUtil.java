package cc.fs.sample.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件处理
 */
public class FileUtil {

    /**
     * 获得系统缓存地址
     */
    public static String getCachePath(Context context) {
        if (context == null) {
            return "";
        }
        return context.getCacheDir().getPath();
    }

    /**
     * 获得SD卡位置
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }


    public static String createSDDir(String path) {
        File file = createSDFile(path);
        return file == null ? "" : file.getAbsolutePath();
    }

    /**
     * 在SD卡创建文件夹
     */
    public static File createSDFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File dirFile = new File(getSDCardPath(), path);
        dirFile.mkdirs();
        if (dirFile.exists()) {
            return dirFile;
        } else {
            return null;
        }
    }

    public static String getFileSizeString(String path) {
        return formatSize(getFileSizeLong(path));
    }

    public static long getFileSizeLong(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return 0;
        }

        //获取文件大小
        return file.length();
    }

    //计算大小
    public static String formatSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.2fGB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f >= 100 ? "%.0fMB" : "%.2fMB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f >= 100 ? "%.0fKB" : "%.2fKB", f);
        } else
            return String.format("%dB", size);
    }

    //从Assets获取文件
    public static InputStream getFileFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }
        try {
            return context.getResources().getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
