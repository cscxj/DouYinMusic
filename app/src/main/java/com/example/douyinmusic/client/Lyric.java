package com.example.douyinmusic.client;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lyric {

    private final static String REG = "\\[(\\d{2}):(\\d{2})\\.(\\d{1,3})\\](.*?(?=\\s\\[))";
    private List<LyricLine> lyricLines;
    private long progress;
    private int currentLyric;
    private Observer observer;

    /**
     * @param lrc 歌词 lrc字符串
     */
    public Lyric(String lrc) {
        lyricLines = new ArrayList<>();
        Pattern pattern = Pattern.compile(REG);
        Matcher matcher = pattern.matcher(lrc);
        while (matcher.find()) {
            int m = Integer.parseInt(matcher.group(1));
            int s = Integer.parseInt(matcher.group(2));
            int mils = Integer.parseInt(matcher.group(3));
            long time = m * 60 * 1000 + s * 1000 + mils;
            lyricLines.add(
                    new LyricLine(
                            time,
                            matcher.group(4)
                    )
            );
        }
        for (LyricLine line : lyricLines
        ) {
            Log.e(line.startTime + "", line.text);
        }
    }

    public Lyric(String errInfo,int flag){

    }

    /**
     * 根据进度获取当前歌词
     *
     * @return
     */
    public String getCurrentLyric(long progress) {
        if (lyricLines.size() == 0)return "无歌词数据";
        for (int i = 0; i < lyricLines.size() - 1; i++) {
            if (progress >= lyricLines.get(i).startTime && progress < lyricLines.get(i + 1).startTime) {
                return lyricLines.get(i).text;
            }
        }
        if (progress > lyricLines.get(lyricLines.size() - 1).startTime)
            return lyricLines.get(lyricLines.size() - 1).text;
        return "";
    }

    public static class LyricLine {
        public long startTime;
        public String text;

        LyricLine() {
        }

        LyricLine(long startTime, String text) {
            this.startTime = startTime;
            this.text = text;
        }
    }

}
