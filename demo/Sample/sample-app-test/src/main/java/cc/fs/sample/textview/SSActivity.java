package cc.fs.sample.textview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cc.fs.sample.R;

public class SSActivity extends Activity {

    private TextView tv;
    private static final int[] NUM_RES_ARRAY = {R.mipmap.ic_channel_num0, R.mipmap.ic_channel_num1, R.mipmap.ic_channel_num2, R.mipmap.ic_channel_num3, R.mipmap.ic_channel_num4, R.mipmap.ic_channel_num5, R.mipmap.ic_channel_num6, R.mipmap.ic_channel_num7, R.mipmap.ic_channel_num8,
            R.mipmap.ic_channel_num9,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss_activity);

        tv = (TextView) findViewById(R.id.txt);

        setNumText(24, 30);
    }

    void setNumText(int number, int testSize) {
        List<Integer> numList = new ArrayList<>();
        if (number < 9) {
            numList.add(0);
            numList.add(number);
        } else {
            while (number > 0) {
                numList.add(0, number % 10);
                number = number / 10;
            }
        }

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (Integer num : numList) {
            stringBuilder.append(SpanUtil.createDrawableSpan(ContextCompat.getDrawable(this, NUM_RES_ARRAY[num]), testSize));
        }
        tv.setText(stringBuilder);
    }
}
