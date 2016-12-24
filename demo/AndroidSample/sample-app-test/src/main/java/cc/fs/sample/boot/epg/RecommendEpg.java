package cc.fs.sample.boot.epg;

import android.util.Log;

public class RecommendEpg implements BaseEpgItem {
    @Override
    public void onDownloadEvent() {
        Log.e("RecommendEpg","onDownloadEvent");
    }
}
