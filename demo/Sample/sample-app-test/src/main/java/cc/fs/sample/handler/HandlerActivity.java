package cc.fs.sample.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cc.fs.sample.R;
import cc.fs.sample.utils.ImageUtil;

/**
 * 图片处理，及log测试
 */
public class HandlerActivity extends AppCompatActivity {
    StringBuilder strBuilder;
    ImageView ivImage;
    TextView tvDetail;
    Button btGet;
    Bitmap srcBitmap;

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
    }

    private void setupEvent() {
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_sence);
                computeBitmapSize(srcBitmap);
                if (srcBitmap != null) {
                    ivImage.setImageBitmap(srcBitmap);
                    tvDetail.setText(strBuilder.toString());
                }
            }
        });
    }

    private void computeBitmapSize(Bitmap bitmap) {
        if (bitmap == null) {
            strBuilder.append("图片为空不可测量\n");
            return;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        strBuilder.append("宽：" + width + "  高：" + height + "\n");
    }
}
