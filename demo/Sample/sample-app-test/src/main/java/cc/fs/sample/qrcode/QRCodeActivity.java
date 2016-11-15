package cc.fs.sample.qrcode;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.HashMap;
import java.util.Map;

import cc.fs.sample.R;

public class QRCodeActivity extends AppCompatActivity {

    ImageView image;
    TextView txt;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        image = (ImageView) findViewById(R.id.image);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        txt = (TextView) findViewById(R.id.txt);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createQRCode();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readQRCode();
            }
        });
    }

    private void readQRCode() {
        QRCodeUtil.getQRCodeContent(mBitmap, new QRCodeUtil.ScannerListener() {
            @Override
            public void run(@Nullable String content) {
                if (content != null) {
                    txt.setText("二维码读取出来结果："+content);
                }
            }
        });
    }

    private void createQRCode() {
        QRCodeUtil.createQRCodeBitmap("http://www.baidu.com", new QRCodeUtil.CreateListener() {
            @Override
            public void run(Bitmap bitmap) {
                if (bitmap != null) {
                    mBitmap = bitmap;
                    image.setImageBitmap(bitmap);
                }
            }
        });
    }
}
