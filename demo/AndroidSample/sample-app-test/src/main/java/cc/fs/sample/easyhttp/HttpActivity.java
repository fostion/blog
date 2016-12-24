package cc.fs.sample.easyhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import cc.fs.sample.R;

/**
 * Http请求实践
 */
public class HttpActivity extends AppCompatActivity {

    final String TAG = "HttpActivity";

    Button btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useOkHttpGet();
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void useOkHttpGet() {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder().url("http://news-at.zhihu.com/api/4/version/android/2.3.0").build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                L.e(TAG, "http请求失败结果:\n" + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response == null) {
//                    return;
//                }
//
//                L.e(TAG, "http请求成功结果:\n" + response.toString());
//            }
//        });
    }

}
