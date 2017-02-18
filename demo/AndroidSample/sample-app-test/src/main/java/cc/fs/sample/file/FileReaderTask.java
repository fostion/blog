package cc.fs.sample.file;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import cc.fs.sample.utils.L;

/**
 * 文件内容读取
 */
public class FileReaderTask extends AsyncTask<Void, Void, String> {

    private String filePath;
    private OnReaderResult readerResult;

    public void start(String filePath, OnReaderResult readerResult) {
        if (TextUtils.isEmpty(filePath) || readerResult == null) {
            return;
        }

        this.filePath = filePath;
        this.readerResult = readerResult;
        this.execute();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }

        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }

        BufferedReader reader = null;
        StringBuilder strBuilder = null;
        //读取内容
        try {
            reader = new BufferedReader(new FileReader(filePath));
            strBuilder = new StringBuilder();
            String tmpStr;
            int line = 1;
            while ((tmpStr = reader.readLine()) != null) {
                strBuilder.append(new String(tmpStr.getBytes(),"utf-8"));
                L.i("fileReader", "line: " + line + "   content: " + tmpStr);
            }
            reader.close();
        } catch (Exception e) {
            strBuilder = null;
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //读取错误处理
        if (strBuilder == null) {
            return null;
        }

        //读取成功处理
        return strBuilder.toString();
    }

    @Override
    protected void onPostExecute(String res) {
        if(readerResult == null){
            return;
        }

        //读取失败
        if(res == null){
            readerResult.onFailure();
            return;
        }

        //读取成功
        readerResult.onSuccess(res);
    }

    interface OnReaderResult {
        void onSuccess(String res);

        void onFailure();
    }

}
