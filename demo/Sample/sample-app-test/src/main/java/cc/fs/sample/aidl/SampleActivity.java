package cc.fs.sample.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cc.fs.sample.R;
import cc.fs.sample.Sample;

public class SampleActivity extends AppCompatActivity {

    Sample mService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = Sample.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbind();
    }

    void bind(){
//        Intent intent = new Intent(this,SampleService.class);
        Intent intent = new Intent("cc.fs.testService");
        intent.setPackage("cc.fs.sample");
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    void unbind(){
        unbindService(conn);
    }
}
