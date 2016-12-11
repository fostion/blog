package cc.fs.sample.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码
 */
public class QRCodeUtil {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public static void createQRCodeBitmap(String content, CreateListener listener) {
        createQRCodeBitmap(content, (Bitmap) null, listener);
    }

    public static void createQRCodeBitmap(String content, String path, CreateListener listener) {
        if (TextUtils.isEmpty(path)) {
            createQRCodeBitmap(content, (Bitmap) null, listener);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; //图片缩小两倍
            options.inJustDecodeBounds = false;
            Bitmap logoBitmap = BitmapFactory.decodeFile(path, options);
            createQRCodeBitmap(content, logoBitmap, listener);
        }
    }

    public static void createQRCodeBitmap(String content, Bitmap logoBitmap, CreateListener listener) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        new QRCodeBitmapCreator(content, logoBitmap, listener).execute();
    }

    public static void getQRCodeContent(Bitmap bitmap, ScannerListener listener) {
        if (bitmap == null) {
            return;
        }
        new QRCodeBitmapScanner(bitmap, listener).execute();
    }

    //二维码结果
    private static class QRCodeBitmapScanner extends AsyncTask<Void, Void, String> {
        private Bitmap bitmap;
        private ScannerListener listener;

        QRCodeBitmapScanner(Bitmap bitmap, ScannerListener listener) {
            this.bitmap = bitmap;
            this.listener = listener;
        }

        @Override
        protected String doInBackground(Void... voids) {
            if (bitmap == null) return null;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            LuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> params = new HashMap<>();
            //设置解码参数
            params.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            Result result = null;
            try {
                result = new QRCodeReader().decode(binaryBitmap, params);
                return result.getText();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            listener.run(result);
        }
    }

    //生成二维码图片
    private static class QRCodeBitmapCreator extends AsyncTask<Void, Void, Bitmap> {
        private String content;
        private Bitmap logoBitmap;
        private CreateListener listener;

        QRCodeBitmapCreator(String content, Bitmap logoBitmap, CreateListener listener) {
            this.content = content;
            this.logoBitmap = logoBitmap;
            this.listener = listener;
        }

        @Override
        protected Bitmap doInBackground(Void... strings) {
            //图片长宽
            int width = WIDTH;
            int height = HEIGHT;
            Map<EncodeHintType, Object> params = new HashMap<>();
            //设置纠错 最高级别 但是会出现黑边和白色边框
            params.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
            //设置编码
            params.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置白色边框宽度
            params.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = null;
            try {
                bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, params);
                // 开始利用二维码数据创建Bitmap图片，分别设为黑白两色
                int w = bitMatrix.getWidth();
                int h = bitMatrix.getHeight();
                int[] data = new int[w * h];

                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (bitMatrix.get(x, y))
                            data[y * w + x] = 0xff000000;// 黑色
                        else
                            data[y * w + x] = -1;// -1 相当于0xffffffff 白色
                    }
                }

                // 创建一张bitmap图片，采用最高的效果显示
                Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                // 将上面的二维码颜色数组传入，生成图片颜色
                bitmap.setPixels(data, 0, w, 0, 0, w, h);
                //设置logo
                if (logoBitmap != null) return addLogo(bitmap, logoBitmap);

                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private Bitmap addLogo(Bitmap srcBitmap, Bitmap logoBitmap) {
            if (srcBitmap == null) {
                return null;
            }
            if (logoBitmap == null) {
                return srcBitmap;
            }

            //获取图片的宽高
            int srcWidth = srcBitmap.getWidth();
            int srcHeight = srcBitmap.getHeight();
            int logoWidth = logoBitmap.getWidth();
            int logoHeight = logoBitmap.getHeight();

            if (srcWidth == 0 || srcHeight == 0) {
                return null;
            }

            if (logoWidth == 0 || logoHeight == 0) {
                return srcBitmap;
            }

            //logo大小为二维码整体大小的1/4
            float scale = srcWidth * 1.0f / (4 * logoWidth);
            Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
            try {
                Canvas canvas = new Canvas(bitmap);
                canvas.drawBitmap(srcBitmap, 0, 0, null);
                canvas.scale(scale, scale, srcWidth / 2, srcHeight / 2);
                canvas.drawBitmap(logoBitmap, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

                canvas.save(Canvas.ALL_SAVE_FLAG);
                canvas.restore();
            } catch (Exception e) {
                bitmap = null;
                e.getStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            listener.run(bitmap);
        }
    }

    public interface CreateListener {
        void run(@Nullable Bitmap bitmap);
    }

    public interface ScannerListener {
        void run(@Nullable String content);
    }

}
