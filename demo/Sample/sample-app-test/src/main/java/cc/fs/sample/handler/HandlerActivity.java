package cc.fs.sample.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import cc.fs.sample.R;
import cc.fs.sample.utils.FileUtil;
import cc.fs.sample.utils.ImageUtil;

/**
 * 图片处理，及log测试
 */
public class HandlerActivity extends AppCompatActivity {
    StringBuilder strBuilder;
    ImageView ivImage;
    TextView tvDetail;
    Button btGet, btSave, btCompress;
    Bitmap srcBitmap;
    String rootPath = FileUtil.createSDDir(FileUtil.getSDCardPath() + "ImageTest") + File.separator;
    String imageSavePath = rootPath + System.currentTimeMillis() + ".jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_handler);
        setupView();
        setupEvent();
    }

    private void setupView() {
        strBuilder = new StringBuilder();
        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvDetail = (TextView) findViewById(R.id.tvText);
        btGet = (Button) findViewById(R.id.btn1);
        btSave = (Button) findViewById(R.id.btn2);
        btCompress = (Button) findViewById(R.id.btn3);
    }

    private void setupEvent() {
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srcBitmap = ImageUtil.inputStream2Bitmap(FileUtil.getFileFromAssets(HandlerActivity.this, "ic_sence.jpg"));
                computeBitmapSize(srcBitmap);
                if (srcBitmap != null) {
                    ivImage.setImageBitmap(srcBitmap);
                    tvDetail.setText(strBuilder.toString());
                }
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(rootPath)) {
                    //保存文件
                    ImageUtil.saveBitmap(srcBitmap, imageSavePath, new ImageUtil.Callback<Boolean>() {
                        @Override
                        public void run(Boolean res) {
                            //保存成功
                            if (res) {
                                File file = new File(imageSavePath);
                                if (file.exists()) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(imageSavePath);
                                    strBuilder.append("--------------\n");
                                    strBuilder.append(" 高:" + bitmap.getHeight() + "  宽:" + bitmap.getWidth() + "\n");
                                    strBuilder.append("文件大小:" + FileUtil.getFileSizeString(file.getAbsolutePath()));
                                    tvDetail.setText(strBuilder.toString());
                                    strBuilder.append("--------------\n");
                                }
                            }
                        }
                    });
                }
            }
        });

        btCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtil.compressImage(srcBitmap, new ImageUtil.Callback<Bitmap>() {
                    @Override
                    public void run(Bitmap data) {
                        if (data != null) {
                            final String comPath = rootPath + "zoom.jpg";
                            ImageUtil.saveBitmap(data, comPath, new ImageUtil.Callback<Boolean>() {
                                @Override
                                public void run(Boolean res) {
                                    if (res) {
                                        getPathBitmap(comPath);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void getPathBitmap(String path) {
        Bitmap comBitmap = ImageUtil.decodeSampledBitmapFromFile(path);
        if (comBitmap != null) {
            File file = new File(path);
            strBuilder.append("--------------\n");
            strBuilder.append(" 高:" + comBitmap.getHeight() + "  宽:" + comBitmap.getWidth() + "\n");
            strBuilder.append("文件大小:" + FileUtil.getFileSizeString(file.getAbsolutePath()));
            tvDetail.setText(strBuilder.toString());
            strBuilder.append("--------------\n");
        }
    }

    private void computeBitmapSize(Bitmap bitmap) {
        if (bitmap == null) {
            strBuilder.append("图片为空不可测量\n");
            return;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        strBuilder.append("  高：" + height + "宽：" + width + "\n");
    }
}
