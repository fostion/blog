package cc.fs.sample.netspeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import cc.fs.sample.R;

public class NetSpeedActivity extends AppCompatActivity {


    Button btn;
    TextView txt;
    NetSpeedTask speedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_speed);

        btn = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);

        speedTask = new NetSpeedTask();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speedTask.start("http://downali.game.uc.cn/s/0/8/201507301829420aecf2_000002_UC.apk?x-oss-process=udf/uc-apk,ESDDjEgTKBUX9777bceffa40631f", new NetSpeedTask.OnNetSpeedTestResult() {
                    @Override
                    public void onUpdate(NetSpeedTask.NetInfo info) {
                        if (info == null) {
                            return;
                        }

                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append("下载总大小:" + comSize(info.totalSize) + "\n");
                        strBuilder.append("已经下载大小:" + comSize(info.curSize) + "\n");
                        strBuilder.append("当前下载速度:" + comSpeed(info.curSpeed) + "\n");
                        strBuilder.append("平均下载速度:" + comSpeed(info.avSpeend) + "\n");

                        txt.setText(strBuilder.toString());
                    }
                });
            }
        });

    }

    public String comSize(long size) {
        long kbSize = 1024;
        long mbSize = 1024 * kbSize;
        long gbSize = 1024 * mbSize;

        float res = 1.0f;
        if(size >= gbSize){
            res = Math.round(1.0f * size / gbSize * 100) / 100;
            return res+" GB";
        } else if(size >= mbSize){
            res =  Math.round(1.0f * size / mbSize * 100) / 100;
            return res+" MB";
        } else if(size >= kbSize){
            res =   Math.round(1.0f * size / kbSize * 100) / 100;
            return res+" KB";
        } else {
            res = size;
            return res+" B";
        }
    }

    public String comSpeed(long size) {
        long kbSize = 1024;
        long mbSize = 1024 * kbSize;
        long gbSize = 1024 * mbSize;

        float res = 1.0f;
        if(size >= gbSize){
            res = 1.0f * size / gbSize;
            return new DecimalFormat(".00").format(res) + " GB/s";
        } else if(size >= mbSize){
            res = 1.0f * size / mbSize;
            return new DecimalFormat(".00").format(res) + " MB/s";
        } else if(size >= kbSize){
            res = 1.0f * size / kbSize;
            return new DecimalFormat(".00").format(res) + " KB/s";
        } else {
            res = size;
            return res+" B/s";
        }
    }
}
