package cc.fs.sample.boot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cc.fs.sample.R;

/**
 * android休眠/跳转到第三方应用后被杀死,重新启动实例
 */
public class BootActivity extends AppCompatActivity {

    HomeWatcher homeWatcher;
    WakeWatcher wakeWatcher;
    private final String Tag = "BootActivity";
    private Button btn,btn1;

    private void L(String msg) {
        Log.e(Tag, msg);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        L("onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        L("onSaveInstanceState");

        outState.putString("msg", "我被系统杀死了");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        L("onCreate");

        if (savedInstanceState != null) {
            //系统曾经被杀死过
            String msg = savedInstanceState.getString("msg");

            SharedPreferences sp = getSharedPreferences("home_key", Context.MODE_PRIVATE);
            boolean isKeyOnHome = sp.getBoolean("onKeyHome", false);
            if (isKeyOnHome) {
                //在桌面后被杀死
                L(msg + " 在桌面 ");
            } else {
                //在第三方情况下
                L(msg + " 在第三方应用下 ");
            }

        }

        homeWatcher = new HomeWatcher(this, new HomeWatcher.OnHomeKeyListener() {
            @Override
            public void run() {
                L("点击home键");
                SharedPreferences sp = getSharedPreferences("home_key", Context.MODE_PRIVATE);
                sp.edit().putBoolean("onKeyHome", true).commit();
            }
        });

        wakeWatcher = new WakeWatcher(this, new WakeWatcher.OnWakeListener() {
            @Override
            public void sleep() {
                L("系统睡眠");
            }

            @Override
            public void wake() {
                L("系统被唤醒");
            }
        });

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到第三方应用
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.qq.com");
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        btn1 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.linkin.tv.OPEN");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        L("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L("onResume");
        if (homeWatcher != null) {
            homeWatcher.startWatch();
        }

        if (wakeWatcher != null) {
            wakeWatcher.startWatch();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        L("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L("onDestroy");
        if (homeWatcher != null) {
            homeWatcher.stopWatch();
        }

        if (wakeWatcher != null) {
            wakeWatcher.stopWatch();
        }
    }
}
