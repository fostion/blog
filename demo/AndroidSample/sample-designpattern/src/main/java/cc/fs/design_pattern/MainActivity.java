package cc.fs.design_pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cc.fs.design_pattern.design.create.prototype.Prototype;
import cc.fs.design_pattern.design.create.prototype.PrototypeRun;
import cc.fs.design_pattern.design.structure.Proxy.ProxyRun;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PrototypeRun run = new PrototypeRun();
        final ProxyRun proxyRun = new ProxyRun();


        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proxyRun.runDynamicProxy();
            }
        });
    }
}
