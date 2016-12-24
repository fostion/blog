package cc.fs.sample.boot;


import java.util.ArrayList;
import java.util.List;

import cc.fs.sample.boot.epg.BaseEpgItem;
import cc.fs.sample.boot.epg.BootEpg;
import cc.fs.sample.boot.epg.HotEpg;
import cc.fs.sample.boot.epg.RecommendEpg;

public class EpgApkManager {
    private static EpgApkManager instance;
    private List<BaseEpgItem> baseEpgItems;

    private EpgApkManager(){
        baseEpgItems = new ArrayList<>();
        baseEpgItems.add(new BootEpg());
        baseEpgItems.add(new HotEpg());
        baseEpgItems.add(new RecommendEpg());
    }

    public static synchronized EpgApkManager getInstance(){
        if(instance == null){
            instance = new EpgApkManager();
        }

        return instance;
    }

    void onDownloadEvent(){
        for (BaseEpgItem item : baseEpgItems){
            item.onDownloadEvent();
        }
    }

    void clear(){
        baseEpgItems.clear();
        baseEpgItems = null;
        instance = null;
    }
}
