package com.example.douyinmusic.model;

public class Music {
    private String name;
    private String singer;
    private String duration;

    public Music(){}
    public Music(String name,String singer,String duration){
        this.name = name;
        this.singer = singer;
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getSinger() {
        return singer;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
