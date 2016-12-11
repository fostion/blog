package cc.fs.sample.leakcanary;

import android.util.Log;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

/**
 * Created by Administrator on 2016/11/30.
 */

public class LeakService extends DisplayLeakService {

    @Override
    protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
        super.afterDefaultHandling(heapDump, result, leakInfo);

        if(heapDump == null || result == null){
            return;
        }


        Log.e("LeakService",heapDump.heapDumpFile.getAbsolutePath());
    }
}
