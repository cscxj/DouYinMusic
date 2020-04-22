package com.example.douyinmusic.ui;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.douyinmusic.MainActivity;
import com.example.douyinmusic.client.Lyric;
import com.example.douyinmusic.model.lyric.Tlyric;
import com.example.douyinmusic.model.music_list.Playlist;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Playlist> playlist = new MutableLiveData<Playlist>();
    private MutableLiveData<PlayMode> playMode = new MutableLiveData<PlayMode>();
    private MutableLiveData<PlayState> playState = new MutableLiveData<PlayState>();
    // 当前播放的音乐
    private MutableLiveData<Integer> currentPlayer = new MutableLiveData<Integer>();
    // 当前音乐的歌词
    private MutableLiveData<Lyric> currentLyric = new MutableLiveData<Lyric>();
    // 当前进度
    private MutableLiveData<Integer> currentProgress= new MutableLiveData<Integer>();


    public MainViewModel(){

    }

    public MutableLiveData<Lyric> getCurrentLyric() {
        return currentLyric;
    }

    public void setCurrentLyric(Lyric currentLyric) {
        this.currentLyric.setValue(currentLyric);
    }
}
enum PlayMode {
    LOOP_ONE,
    LOOP_LIST,
    RANDOM,
}

enum PlayState {
    OFF,
    PLAY,
    PAUSE
}
