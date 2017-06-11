package cn.demo.so;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringFromJNI();

        generateKey("需要加密的字符串");

    }



    //调用
    public native String stringFromJNI();

    public native String generateKey(String msg);

    //导入库
    static {
        System.loadLibrary("native-log");
    }
}
