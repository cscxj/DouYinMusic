//package com.example.douyinmusic.ui;
//
//import android.os.Handler;
//import android.os.Message;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.douyinmusic.MainActivity;
//import com.example.douyinmusic.model.music_list.Playlist;
//
//public class MainViewModel extends ViewModel {
//
//    private MutableLiveData<Playlist> playlist = new MutableLiveData<Playlist>();
//    private MutableLiveData<PlayMode> playMode = new MutableLiveData<PlayMode>();
//    private MutableLiveData<PlayState> playState = new MutableLiveData<PlayState>();
//    // 当前播放的音乐
//    private MutableLiveData<Integer> currentPlayer = new MutableLiveData<Integer>();
//    // 当前进度
//    private MutableLiveData<Integer> currentProgress= new MutableLiveData<Integer>();
//
//
//    public MainViewModel(){
//        setPlayMode(PlayMode.LOOP_LIST);
//        setPlayState(PlayState.OFF);
//    }
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//
//        }
//    };
//
//
//    public MutableLiveData<Integer> getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public MutableLiveData<Integer> getCurrentProgress() {
//        return currentProgress;
//    }
//
//    public MutableLiveData<Playlist> getPlaylist() {
//        return playlist;
//    }
//
//    public MutableLiveData<PlayMode> getPlayMode() {
//        return playMode;
//    }
//
//    public MutableLiveData<PlayState> getPlayState() {
//        return playState;
//    }
//
//    public void setCurrentPlayer(Integer currentPlayer) {
//        this.currentPlayer.setValue(currentPlayer);
//    }
//
//    public void setCurrentProgress(Integer currentProgress) {
//        this.currentProgress.setValue(currentProgress);
//    }
//
//    public void setPlaylist(Playlist playlist) {
//        this.playlist.setValue(playlist);
//    }
//
//    public void setPlayMode(PlayMode playMode) {
//        this.playMode.setValue(playMode);
//    }
//
//    public void setPlayState(PlayState playState) {
//        this.playState.setValue(playState);
//    }
//}
//enum PlayMode {
//    LOOP_ONE,
//    LOOP_LIST,
//    RANDOM,
//}
//
//enum PlayState {
//    OFF,
//    PLAY,
//    PAUSE
//}
