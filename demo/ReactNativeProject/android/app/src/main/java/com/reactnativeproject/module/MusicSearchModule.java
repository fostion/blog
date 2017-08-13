package com.reactnativeproject.module;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;
import com.reactnativeproject.MainApplication;
import com.reactnativeproject.bean.Song;
import com.reactnativeproject.helper.MusicHelper;

import java.util.List;

/**
 * 需要在MusicReactPackage注册否则无法使用
 * 调用原生获取音乐列表
 * Created by Administrator on 2017/8/13.
 */

public class MusicSearchModule extends ReactContextBaseJavaModule {

    public MusicSearchModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MusicSearchModule";
    }


    //要导出一个方法给JavaScript使用，Java方法需要使用注解@ReactMethod。方法的返回类型必须为void。
    //React Native的跨语言访问是异步进行的，所以想要给JavaScript返回一个值的唯一办法是使用回调函数或者发送事件
    @ReactMethod
    public void log(){
        Log.i("test_react","test log ");
    }

    @ReactMethod
    public void getSongList(Callback callback){
        //获取本地音乐播放列表
        List<Song> songList = MusicHelper.getLoacalMusic(MainApplication.mInstance);
        String data = new Gson().toJson(songList);
        Log.i("test_react","size "+songList.size());
        Log.i("test_react","data "+data);
        //通过callback机制回调给js
        callback.invoke(data);
    }
}
