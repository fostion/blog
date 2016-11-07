package cc.fs.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cc.fs.sample.anr_monitor.choreographer.UIChoMonitor;

public class MainActivity extends AppCompatActivity {

    UIChoMonitor uiChoMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiChoMonitor = new UIChoMonitor();
        uiChoMonitor.start();

        Button anrBtn = (Button) findViewById(R.id.anrBtn);
        final ImageView img = (ImageView) findViewById(R.id.img);

        anrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                img.setImageResource(R.mipmap.ic_launcher);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        uiChoMonitor.stop();
    }
}
