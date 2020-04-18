package com.example.douyinmusic.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicPlayerService extends Service {
    public MusicPlayerService() {
    }

    private static final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.i("提示", "这首歌放完了哦");
        }
    };


    @SuppressLint("NewApi")
    public void play(String path) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            Log.i(TAG, "开始播放音乐");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(completionListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void changeProgress(int progress){
        if (mediaPlayer == null) return;
        mediaPlayer.seekTo(progress);
    }

    public void pause() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) {
            Log.i(TAG, "播放暂停");
            mediaPlayer.pause(); //暂停播放
        } else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            Log.i(TAG, "停止播放");
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        } else {
            Toast.makeText(getApplicationContext(), "已停止", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取播放进度
     *
     * @return
     */
    public int getCurrentProgress() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    return mediaPlayer.getCurrentPosition();
                } else if (!mediaPlayer.isPlaying()) {
                    return mediaPlayer.getCurrentPosition();
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void setCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.completionListener = onCompletionListener;
    }


    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return new mBinder();
    }

    /**
     * Binder
     */
    public class mBinder extends Binder {
        public void plays(String path) {
            play(path);
        }
        public void pauses() {
            pause();
        }
        public void stops() {
            stop();
        }
        public int getCurrentPosition() {
            return getCurrentProgress();
        }

        public void setCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
            MusicPlayerService.this.setCompletionListener(onCompletionListener);
        }
        public void changeProgress(int progress){
            MusicPlayerService.this.changeProgress(progress);
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.e(this.toString(), "服务create");
    }
}
