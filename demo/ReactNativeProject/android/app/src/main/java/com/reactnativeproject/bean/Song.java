package com.reactnativeproject.bean;

/**
 * Created by Administrator on 2017/8/13.
 */

public class Song {
    private String name;
    private String path;

    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
