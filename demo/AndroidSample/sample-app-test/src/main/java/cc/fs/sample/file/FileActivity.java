package cc.fs.sample.file;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cc.fs.sample.R;

/**
 *  文件读取demo
 */

public class FileActivity extends Activity{

    Button btn;
    TextView txt;
    String filePath = "/storage/emulated/0/test_file/1487229179628_simp";
    FileReaderTask readerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        btn = (Button)findViewById(R.id.btn);
        txt = (TextView)findViewById(R.id.txt);

        readerTask = new FileReaderTask();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readerTask.start(filePath, new FileReaderTask.OnReaderResult() {
                    @Override
                    public void onSuccess(String res) {
                        if(TextUtils.isEmpty(res)){
                            return;
                        }
                        txt.setText(res);
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(FileActivity.this,"加载文件失败",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
