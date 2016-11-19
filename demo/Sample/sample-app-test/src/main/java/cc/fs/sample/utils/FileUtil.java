package cc.fs.sample.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

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
    public static File getSDCardPath() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 在SD卡创建文件夹
     */
    public static File createSDDir(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return null;
        }
        File dirFile = new File(getSDCardPath(), dirPath);
        dirFile.mkdirs();
        if (dirFile.exists()) {
            return dirFile;
        } else {
            return null;
        }
    }
}
