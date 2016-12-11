package cc.fs.sample.leakcanary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cc.fs.sample.R;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        new MyThread().start();

    }

    //故意造成内存泄漏
    class MyThread extends Thread{
        @Override
        public void run() {
            try{
                Thread.sleep(10*60*1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
