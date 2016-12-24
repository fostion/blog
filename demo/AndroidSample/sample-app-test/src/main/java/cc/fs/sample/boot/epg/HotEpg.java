package cc.fs.sample.boot.epg;

import android.util.Log;

public class HotEpg implements BaseEpgItem {
    @Override
    public void onDownloadEvent() {
        Log.e("HotEpg","onDownloadEvent");
    }
}
