package com.example.douyinmusic;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.douyinmusic.client.Client;
import com.example.douyinmusic.client.Lyric;
import com.example.douyinmusic.client.TaskCompleteCallback;
import com.example.douyinmusic.model.lyric.JSONLyric;
import com.example.douyinmusic.model.music_list.MusicJSON;
import com.example.douyinmusic.model.music_list.Playlist;
import com.example.douyinmusic.model.rank_list.JSONRank;

public class MainActivityViewModel extends ViewModel {
    private final int MSG_RANK_DATA_READY = 0x01;
    private final int MSG_LIST_DATA_READY = 0x02;
    private final int MSG_LYRIC_DATA_READY = 0x03;

    private final MutableLiveData<JSONRank> rankData = new MutableLiveData<>(); // 榜单列表数据
    private final MutableLiveData<Integer> currRank = new MutableLiveData<>(); // 当前列表 索引
    private final MutableLiveData<Playlist> playlistData = new MutableLiveData<>(); // 当前播放的列表数据
    //private final MutableLiveData<Playlist> playlistDisplay = new MutableLiveData<>(); // 当前界面显示的播放列表
    private final MutableLiveData<Integer> currMusic = new MutableLiveData<>(); // 当前播放的音乐 索引
    private final MutableLiveData<PlayMode> playMode = new MutableLiveData<>(PlayMode.LOOP_LIST); // 播放模式
    private final MutableLiveData<PlayState> playState = new MutableLiveData<>(PlayState.OFF); // 播放状态
    private final MutableLiveData<Integer> playProgress = new MutableLiveData<>(0); // 播放进度
    private final MutableLiveData<Lyric> lyricData = new MutableLiveData<>(); // 歌词数据
    private final MutableLiveData<String> currLyric = new MutableLiveData<>(); // 当前歌词

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_RANK_DATA_READY:
                    rankData.setValue((JSONRank) msg.obj);
                    currRank.setValue(0);
                    break;
                case MSG_LIST_DATA_READY:
                    playlistData.setValue((Playlist) msg.obj);
                    break;
                case MSG_LYRIC_DATA_READY:
                    lyricData.setValue((Lyric) msg.obj);
                    break;
            }
        }
    };

    public MainActivityViewModel() {
        super();
        //init(); 改成了从activity中控制加载
    }

    public void init() {
        // 启动客户端，获取数据
        Client.start();
        Client.getRankList(new RankDataComplete());
        Client.getMusicList(
                "19723756",
                new ListDataComplete()
        );
    }

    public MutableLiveData<JSONRank> getRankData() {
        return this.rankData;
    }

    public void setCurrentRank(Integer rankIndex) {
        this.currRank.setValue(rankIndex);
    }

    public MutableLiveData<Integer> getCurrentRank() {
        return currRank;
    }

    /**
     * 更新音乐列表数据
     */
    public void updateMusicListData() {
        String id = this.rankData.getValue().getList().get(this.getCurrRank().getValue()).getId();
        Client.getMusicList(id, new ListDataComplete());
    }

    /**
     * 更新歌词数据
     */
    public void updateLyricData() {
        Client.getLyric(this.playlistData.getValue().getTracks().get(this.currMusic.getValue()).getId(), new LyricDataComplete());
    }

    /**
     * 更新当前歌词
     */
    public void updateCurrLyric() {
        String lyric = lyricData.getValue().getCurrentLyric(playProgress.getValue());
        if (!lyric.equals(currLyric.getValue())) { // 歌词变化了
            this.currLyric.setValue(lyric);
        }
    }

    /**
     * 获取列表数据完成
     */
    class RankDataComplete implements TaskCompleteCallback<JSONRank> {
        @Override
        public void completed(JSONRank res) {
            Message message = new Message();
            message.what = MSG_RANK_DATA_READY;
            message.obj = res;
            handler.sendMessage(message);
        }
    }

    /**
     * 获取音乐数据完成
     */
    private class ListDataComplete implements TaskCompleteCallback<MusicJSON> {
        @Override
        public void completed(final MusicJSON res) {
            Message message = new Message();
            message.what = MSG_LIST_DATA_READY;
            message.obj = res.getPlaylist();
            handler.sendMessage(message);
        }
    }


    /**
     * 获取歌词数据完成
     */
    private class LyricDataComplete implements TaskCompleteCallback<JSONLyric> {
        @Override
        public void completed(final JSONLyric res) {
            Message message = new Message();
            message.what = MSG_LYRIC_DATA_READY;
            if (null != res.getLrc()){
                message.obj = new Lyric(res.getLrc().getLyric());

            }else {
                message.obj = new Lyric("");
            }
            handler.sendMessage(message);
        }
    }

    public enum PlayMode {
        LOOP_ONE,
        LOOP_LIST,
        RANDOM,
    }

    public enum PlayState {
        OFF,
        PLAY,
        PAUSE
    }


    public MutableLiveData<Playlist> getPlaylistData() {
        return playlistData;
    }

    public MutableLiveData<Integer> getCurrMusic() {
        return currMusic;
    }

    public MutableLiveData<PlayState> getPlayState() {
        return playState;
    }

    public MutableLiveData<PlayMode> getPlayMode() {
        return playMode;
    }

    public MutableLiveData<Integer> getCurrRank() {
        return currRank;
    }

    public MutableLiveData<Integer> getPlayProgress() {
        return playProgress;
    }

    public MutableLiveData<Lyric> getLyricData() {
        return lyricData;
    }

    public MutableLiveData<String> getCurrLyric() {
        return currLyric;
    }
}
