package com.reactnativeproject.helper;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.reactnativeproject.bean.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取系统音乐
 * Created by Administrator on 2017/8/13.
 */

public class MusicHelper {
    public static List<Song> getLoacalMusic(Context context){
        List<Song> songList = new ArrayList<>();
        if (context == null){
            Log.i("test_react","context为空 ");
            return songList;
        }
        Cursor cursor = null;
        try {
            //通过ContentResolver获取音乐列表
            String[] params = new String[]{MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA};
            cursor = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, params,
                    MediaStore.Audio.Media.MIME_TYPE + "=? or "
                            + MediaStore.Audio.Media.MIME_TYPE + "=?",
                    new String[] { "audio/mpeg", "audio/x-ms-wma" }, null);
            //查询到的结果重新封装
            if (cursor != null){
                for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    String name = cursor.getString(0);
                    String path = cursor.getString(1);
                    songList.add(new Song(name,path));
                }
            }
            return songList;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return songList;
    }

}
