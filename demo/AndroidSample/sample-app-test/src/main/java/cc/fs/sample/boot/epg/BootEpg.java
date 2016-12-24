package cc.fs.sample.boot.epg;

import android.util.Log;

public class BootEpg implements BaseEpgItem{
    @Override
    public void onDownloadEvent() {
        Log.e("BootEpg","onDownloadEvent");
    }
}
