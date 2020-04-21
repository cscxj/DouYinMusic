package com.example.douyinmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.douyinmusic.adapters.MusicListAdapter;
import com.example.douyinmusic.api.Api;
import com.example.douyinmusic.client.Client;
import com.example.douyinmusic.client.TaskCompleteCallback;
import com.example.douyinmusic.model.music_list.MusicJSON;
import com.example.douyinmusic.model.music_list.Playlist;
import com.example.douyinmusic.model.music_list.Tracks;
import com.example.douyinmusic.service.MusicPlayerService;
import com.example.douyinmusic.ui.RankListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView musicListView;
    private FloatingActionButton playControlBtn;
    private SeekBar seekBar;
    private ImageView coverImage;
    private TextView textName;
    private TextView textSinger;
    private View switchModeBtn;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerRightView;

    private RankListViewModel rankListViewModel;

    private Playlist playlist = new Playlist();
    private AnimatedVectorDrawable playerAnimIcon;
    private AnimatedVectorDrawable pauseAnimIcon;

    private MusicListAdapter listAdapter;
    private MusicPlayerService.mBinder binder;
    /**
     * 播放模式
     */
    private PlayMode playMode = PlayMode.LOOP_LIST;
    private PlayState playState = PlayState.OFF;
    // 当前播放的音乐
    private int currentPlayer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            seekBar.setProgress((int) msg.obj);
        }
    };

    private void initUi() {
        coverImage = (ImageView) findViewById(R.id.img_cover);
        textName = (TextView) findViewById(R.id.text_main_name);
        textSinger = (TextView) findViewById(R.id.text_main_singer);
        switchModeBtn = findViewById(R.id.btn_change_mode);
        //SeekBar
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerRightView = (LinearLayout) findViewById(R.id.drawer_right_view);
        // 音乐列表
        musicListView = (RecyclerView) findViewById(R.id.music_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        musicListView.setLayoutManager(linearLayoutManager);
        // 浮动按钮
        playerAnimIcon = (AnimatedVectorDrawable) AppCompatResources.getDrawable(this, R.drawable.ic_play_animatable);
        pauseAnimIcon = (AnimatedVectorDrawable) AppCompatResources.getDrawable(this, R.drawable.ic_pause_animatable);
        playControlBtn = (FloatingActionButton) findViewById(R.id.btn_player_control);
        playControlBtn.setImageDrawable(playerAnimIcon);
        playControlBtn.setOnClickListener(new ClickFloatBtn());
        switchModeBtn.setOnClickListener(new ClickSwitchModeListener());
        seekBar.setOnSeekBarChangeListener(new SeekBarDragListener());
        textName.setSelected(true);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void updateProgress() {
        new Thread() {
            @Override
            public void run() {
                while (!interrupted()) {
                    int progress = binder.getCurrentPosition();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = progress;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 监听 fragment中的选择
        this.rankListViewModel = new ViewModelProvider(this).get(RankListViewModel.class);
        rankListViewModel.getCurrentRank().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                MainActivity.this.drawerLayout.closeDrawers();
                Client.getMusicList(index, new MusicDataComplete());
            }
        });
        // 启动Client
        Client.start();
        Client.getMusicList(26, new MusicDataComplete());
        //绑定服务
        bindService(
                new Intent(MainActivity.this, MusicPlayerService.class),
                new mServiceConnection(),
                BIND_AUTO_CREATE
        );
        // 初始化ui
        initUi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        } else {
            drawerLayout.openDrawer(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu:
                openRightLayout(drawerRightView);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerRightView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 线程消息站
     */

    void play(int index) {
        // 获取数据
        Tracks currentMusic = playlist.getTracks().get(index);
        // 更新显示
        textName.setText(currentMusic.getName());
        textSinger.setText(currentMusic.getAr().get(0).getName());
        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_launcher_foreground);
        Glide.with(MainActivity.this).load(currentMusic.getAl().getPicurl()).apply(options).into(coverImage);
        seekBar.setMax(currentMusic.getDt());
        binder.plays(Api.MUSIC_MP3 + currentMusic.getId());
        currentPlayer = index;
        playState = PlayState.PLAY;
    }

    /**
     * 选择音乐的回调
     */
    private class SelectMusicListener implements MusicListAdapter.OnSelect {
        @Override
        public void select(final int index) {
            play(index);
            binder.setCompletionListener(new MusicPlayComplete());
            updateProgress();
            setState(PlayState.PLAY);
        }
    }

    private void setState(PlayState state) {
        switch (state) {
            case PAUSE:
                playControlBtn.setImageDrawable(pauseAnimIcon);
                pauseAnimIcon.start();
                binder.pauses();
                playState = PlayState.PAUSE;
                break;
            case PLAY:
                playControlBtn.setImageDrawable(playerAnimIcon);
                playerAnimIcon.start();
                binder.pauses();
                playState = PlayState.PLAY;
                break;
            case OFF:
                playState = PlayState.PLAY;
                break;
        }
    }

    /**
     * 浮动按钮点击事件
     */
    private class ClickFloatBtn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (playState) {
                case OFF:
                    break;
                case PLAY:
                    setState(PlayState.PAUSE);
                    break;
                case PAUSE:
                    setState(PlayState.PLAY);
                    break;
            }

        }
    }

    /**
     * 切换模式事件
     */
    private class ClickSwitchModeListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (playMode) {
                case LOOP_ONE:
                    playMode = PlayMode.LOOP_LIST;
                    switchModeBtn.setBackgroundResource(R.drawable.ic_repeat_black_24dp);
                    Toast.makeText(MainActivity.this, "列表循环播放", Toast.LENGTH_SHORT).show();
                    break;
                case LOOP_LIST:
                    playMode = PlayMode.RANDOM;
                    switchModeBtn.setBackgroundResource(R.drawable.ic_shuffle_white_24dp);
                    Toast.makeText(MainActivity.this, "列表随机播放", Toast.LENGTH_SHORT).show();
                    break;
                case RANDOM:
                    playMode = PlayMode.LOOP_ONE;
                    switchModeBtn.setBackgroundResource(R.drawable.ic_repeat_one_black_24dp);
                    Toast.makeText(MainActivity.this, "单曲循环播放", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /**
     * 获取音乐数据完成
     */
    private class MusicDataComplete implements TaskCompleteCallback<MusicJSON> {
        @Override
        public void completed(final MusicJSON res) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() { // 渲染
                    playlist = res.getPlaylist();
                    listAdapter = new MusicListAdapter(res.getPlaylist());
                    listAdapter.setSlectMusicListener(new SelectMusicListener());
                    musicListView.setAdapter(listAdapter);
                }
            });
        }
    }

    /**
     * 播放完成回调
     */
    private class MusicPlayComplete implements MediaPlayer.OnCompletionListener {
        private int getNext(int current) {
            int next = 0;
            switch (MainActivity.this.playMode) {
                case LOOP_LIST:
                    next = (current + 1) % playlist.getTracks().size();
                    break;
                case LOOP_ONE:
                    next = current;
                    break;
                case RANDOM:
                    next = (int) (Math.random() * playlist.getTracks().size());
                    break;
            }
            if (MainActivity.this.playlist.getTracks().get(next).getFee() == 4) { // 跳过付费歌曲
                return getNext(next);
            } else {
                return next;
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            int next = getNext(MainActivity.this.currentPlayer);
            play(next);
            listAdapter.switchTo(next);
        }
    }

    /**
     * 绑定服务
     */
    private class mServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MainActivity.this.binder = (MusicPlayerService.mBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    /**
     * 拖拽进度条
     */
    class SeekBarDragListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            binder.changeProgress(seekBar.getProgress());
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
}
