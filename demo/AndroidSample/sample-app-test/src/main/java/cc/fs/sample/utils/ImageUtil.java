package cc.fs.sample.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.telecom.Call;
import android.text.TextUtils;

import com.google.zxing.common.StringUtils;

/**
 * 图片处理
 */
public class ImageUtil {

    // 将Bitmap转换成InputStream
    public static InputStream bitmap2InputStream(Bitmap bm) {
        if (bm == null) {
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    // 将Bitmap转换成InputStream
    public static InputStream bitmap2InputStream(Bitmap bm, int quality) {
        if (bm == null) {
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    // 将InputStream转换成Bitmap
    public static Bitmap inputStream2Bitmap(InputStream is) {
        if (is == null) {
            return null;
        }

        return BitmapFactory.decodeStream(is);
    }

    // Drawable转换成InputStream
    public static InputStream drawable2InputStream(Drawable d) {
        if (d == null) {
            return null;
        }

        Bitmap bitmap = drawable2Bitmap(d);
        return bitmap2InputStream(bitmap);
    }

    // InputStream转换成Drawable
    public static Drawable inputStream2Drawable(InputStream is) {
        if (is == null) {
            return null;
        }

        Bitmap bitmap = inputStream2Bitmap(is);
        return bitmap2Drawable(bitmap);
    }

    // Drawable转换成byte[]
    public static byte[] drawable2Bytes(Drawable d) {
        if (d == null) {
            return null;
        }

        Bitmap bitmap = drawable2Bitmap(d);
        return bitmap2Bytes(bitmap);
    }

    // byte[]转换成Drawable
    public static Drawable bytes2Drawable(byte[] b) {
        if (b == null) {
            return null;
        }

        Bitmap bitmap = bytes2Bitmap(b);
        return bitmap2Drawable(bitmap);
    }

    // Bitmap转换成byte[]
    public static byte[] bitmap2Bytes(Bitmap bm) {
        if (bm == null) {
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    // byte[]转换成Bitmap
    public static Bitmap bytes2Bitmap(byte[] data) {
        if (data != null && data.length != 0) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        }

        return null;
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        return inSampleSize;
    }

    public static void saveBitmap(final Bitmap bitmap, final String savePath, final Callback<Boolean> callback) {
        if (bitmap == null || TextUtils.isEmpty(savePath)) {
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    File file = new File(savePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean res) {
                if (callback != null) {
                    callback.run(res);
                }
            }
        }.execute();
    }

    public static Bitmap decodeSampledBitmapFromFile(String filePath){
        if(TextUtils.isEmpty(filePath)){
            return null;
        }

        return BitmapFactory.decodeFile(filePath);
    }

    public static Bitmap decodeSampledBitmapFromFile(String filePath,
                                                     int reqWidth, int reqHeight) {
        if (TextUtils.isEmpty(filePath) || reqWidth == 0 || reqHeight == 0) {
            return null;
        }

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    // Drawable转换成Bitmap
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                                : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        if (bitmap == null || w == 0 || h == 0) {
            return null;
        }

        Bitmap newbmp = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                true);
        return newbmp;
    }

    // Bitmap转换成Drawable
    @SuppressWarnings("deprecation")
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        BitmapDrawable bd = new BitmapDrawable(bitmap);
        Drawable drawable = (Drawable) bd;
        return drawable;
    }

    public static Bitmap getCircleBitmap(Bitmap bmp) {
        if (bmp == null) {
            return null;
        }
        return getRoundedBitmap(bmp, 0);
    }

    public static Bitmap getRoundedBitmap(Bitmap bmp, int radius) {
        if (bmp == null) {
            return null;
        }

        Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());

        if (radius == 0) {
            radius = (int) (bmp.getWidth() / 2 + 0.1f);
        }

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(bmp.getWidth() / 2 + 0.7f,
                bmp.getHeight() / 2 + 0.7f, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);

        return output;
    }

    public static void compressImage(final Bitmap bitmap, final Callback<Bitmap> callback) {
        if (bitmap == null) {
            if (callback != null) callback.run(null);
            return;
        }

        //压缩图片
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                //分辨率压缩
                int bWidth = bitmap.getWidth();
                int bHeight = bitmap.getHeight();
                int targetSize = 480;
                float scale = 1.0f;
                if (bWidth > bHeight) {
                    scale = 1.0f * targetSize / bWidth;
                } else {
                    scale = 1.0f * targetSize / bHeight;
                }
                int targetWidth = (int) (bWidth * scale);
                int targetHeight = (int) (bHeight * scale);
                Bitmap zBitmap = zoomBitmap(bitmap, targetWidth, targetHeight);
                //转成jpeg压缩 到100k 以下
                zBitmap = compressImage(zBitmap, 100);
                return zBitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (callback != null) {
                    callback.run(bitmap);
                }
            }
        }.execute();
    }

    //转成jpeg压缩目标大小 单位kb
    public static Bitmap compressImage(Bitmap bitmap, int tarSize) {
        if (bitmap == null) {
            return null;
        }

        int quality = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] data = baos.toByteArray();
        int curSize = data.length / 1024;
        while (curSize <= tarSize) {
            quality -= 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            data = baos.toByteArray();
            curSize = data.length / 1024;
        }

        return bytes2Bitmap(data);
    }

    public static void compressToJpg(final Bitmap bitmap, final Callback<byte[]> callback) {
        if (bitmap == null) {
            return;
        }

        new AsyncTask<Object, Object, byte[]>() {
            @Override
            protected byte[] doInBackground(Object... params) {
                try {
                    ByteArrayOutputStream outByte = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outByte);

                    return outByte.toByteArray();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(byte[] result) {
                if (callback != null) {
                    callback.run(result);
                }
            }
        }.execute();
    }

    public interface Callback<T> {
        void run(T data);
    }
}
