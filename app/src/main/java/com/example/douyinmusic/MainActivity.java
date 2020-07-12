package com.example.douyinmusic;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
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
import com.example.douyinmusic.client.NetworkConnectState;
import com.example.douyinmusic.model.music_list.Playlist;
import com.example.douyinmusic.model.music_list.Tracks;
import com.example.douyinmusic.model.rank_list.RList;
import com.example.douyinmusic.service.MusicPlayerService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.douyinmusic.MainActivityViewModel.PlayMode.*;
import static com.example.douyinmusic.MainActivityViewModel.PlayState.*;

public class MainActivity extends BaseActivity {
    // view
    private Toolbar actionBar;
    private RecyclerView musicListView;
    private FloatingActionButton playControlBtn;
    private SeekBar seekBar;
    private ImageView coverImage;
    private TextView textName;
    private TextView textLyric;
    private TextView textSinger;
    private View switchModeBtn;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerRightView;
    // view model
    private MainActivityViewModel model;
    // drawable
    private AnimatedVectorDrawable playerAnimIcon;
    private AnimatedVectorDrawable pauseAnimIcon;
    private Drawable playerIcon;
    private Drawable pauseIcon;
    // adapter
    private MusicListAdapter listAdapter;
    // binder
    private MusicPlayerService.mBinder binder;

    private void initUi() {
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        coverImage = (ImageView) findViewById(R.id.img_cover);
        textName = (TextView) findViewById(R.id.text_main_name);
        textSinger = (TextView) findViewById(R.id.text_main_singer);
        textLyric = (TextView) findViewById(R.id.text_lyric);
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
        playControlBtn = (FloatingActionButton) findViewById(R.id.btn_player_control);
        if (Build.VERSION.SDK_INT >= 25) {
            playerAnimIcon = (AnimatedVectorDrawable) AppCompatResources.getDrawable(this, R.drawable.ic_play_animatable);
            pauseAnimIcon = (AnimatedVectorDrawable) AppCompatResources.getDrawable(this, R.drawable.ic_pause_animatable);
            playControlBtn.setImageDrawable(playerAnimIcon);
        }else {
            playerIcon = AppCompatResources.getDrawable(this,R.drawable.ic_pause_black_24dp);
            pauseIcon = AppCompatResources.getDrawable(this,R.drawable.ic_play_arrow_black_24dp);
            playControlBtn.setImageDrawable(pauseIcon);
        }
        playControlBtn.setOnClickListener(new ClickFloatBtn());
        switchModeBtn.setOnClickListener(new ClickSwitchModeListener());
        seekBar.setOnSeekBarChangeListener(new SeekBarDragListener());
        textName.setSelected(true);
        textLyric.setSelected(true);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBar.inflateMenu(R.menu.menu);
        actionBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openRightLayout(drawerRightView);
                return true;
            }
        });
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        model.getCurrentRank().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                MainActivity.this.drawerLayout.closeDrawers();
            }
        });

        model.getPlaylistData().observe(this, new Observer<Playlist>() {
            @Override
            public void onChanged(Playlist playlist) {
                listAdapter = new MusicListAdapter(playlist);
                listAdapter.setSlectMusicListener(new SelectMusicListener());
                musicListView.setAdapter(listAdapter);
            }
        });

        model.getCurrRank().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                RList rankInfo = model.getRankData().getValue().getList().get(integer);
                actionBar.setTitle(rankInfo.getName());
                textName.setText(rankInfo.getName());
                textSinger.setText(rankInfo.getUpdatefrequency());
                RequestOptions options = new RequestOptions()
                        .error(R.drawable.ic_launcher_foreground);
                Glide.with(MainActivity.this).load(rankInfo.getCoverimgurl()).apply(options).into(coverImage);
            }
        });

        model.getCurrMusic().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                listAdapter.switchTo(integer);
                play();
                model.updateLyricData();
                Tracks data = model.getPlaylistData().getValue().getTracks().get(model.getCurrMusic().getValue());
                textName.setText(data.getName());
                textSinger.setText(data.getAr().get(0).getName());
                RequestOptions options = new RequestOptions()
                        .error(R.drawable.ic_launcher_foreground);
                Glide.with(MainActivity.this).load(data.getAl().getPicurl()).apply(options).into(coverImage);
            }
        });

        model.getPlayProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                seekBar.setProgress(integer);
                if (model.getLyricData().getValue() != null) { // 有歌词数据就开始更新当前歌词
                    model.updateCurrLyric();
                }
            }
        });

        model.getCurrLyric().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textLyric.setText(s);
            }
        });

        model.getPlayState().observe(this, new Observer<MainActivityViewModel.PlayState>() {
            @Override
            public void onChanged(MainActivityViewModel.PlayState playState) {
                switch (playState) {
                    case PAUSE:
                        binder.pauses();
                        if (Build.VERSION.SDK_INT >= 25) {
                            playControlBtn.setImageDrawable(pauseAnimIcon);
                            pauseAnimIcon.start();
                        }else {
                            playControlBtn.setImageDrawable(pauseIcon);
                        }
                        break;
                    case PLAY:
                        binder.pauses();
                        if (Build.VERSION.SDK_INT >= 25) {
                            playControlBtn.setImageDrawable(playerAnimIcon);
                            playerAnimIcon.start();
                        } else {
                            playControlBtn.setImageDrawable(playerIcon);
                        }
                        break;
                }
            }
        });

        model.getPlayMode().observe(this, new Observer<MainActivityViewModel.PlayMode>() {
            @Override
            public void onChanged(MainActivityViewModel.PlayMode playMode) {
                switch (playMode) {
                    case RANDOM:
                        switchModeBtn.setBackgroundResource(R.drawable.ic_shuffle_white_24dp);
                        Toast.makeText(MainActivity.this, "随机播放", Toast.LENGTH_SHORT).show();
                        break;
                    case LOOP_ONE:
                        switchModeBtn.setBackgroundResource(R.drawable.ic_repeat_one_black_24dp);
                        Toast.makeText(MainActivity.this, "单曲循环", Toast.LENGTH_SHORT).show();
                        break;
                    case LOOP_LIST:
                        switchModeBtn.setBackgroundResource(R.drawable.ic_repeat_black_24dp);
                        Toast.makeText(MainActivity.this, "列表循环", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //绑定服务
        bindService(
                new Intent(MainActivity.this, MusicPlayerService.class),
                new mServiceConnection(),
                BIND_AUTO_CREATE
        );
        //binder.setCompletionListener(new MusicPlayComplete());
        // 初始化ui
        initUi();
        bindData();
        listenerProgress();


    }

    private void listenerProgress() {
        new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    if (model.getPlayState().getValue() == PLAY) {
                        model.getPlayProgress().postValue(binder.getCurrentPosition()); //postValue() 在主线程执行
                    }
                }
            }
        }.start();
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        } else {
            drawerLayout.openDrawer(view);
        }
    }

    /**
     * 返回键回调
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerRightView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    void play() {
        // 获取数据
        final Tracks currentMusic = model.getPlaylistData().getValue().getTracks().get(model.getCurrMusic().getValue());
        seekBar.setMax(currentMusic.getDt());
        binder.plays(Api.MUSIC_MP3 + currentMusic.getId());
        model.getPlayState().setValue(PLAY);
    }

    /**
     * 选择音乐的回调
     */
    private class SelectMusicListener implements MusicListAdapter.OnSelect {
        @Override
        public void select(final int index) {
            // 暂时先这样写吧
            if (((MyApplication) getApplication()).getNetworkConnectState().getValue() == NetworkConnectState.NONE) {
                Toast.makeText(app, "网络错误", Toast.LENGTH_SHORT).show();
            } else {
                model.getCurrMusic().setValue(index);
            }

        }
    }

    /**
     * 浮动按钮点击事件
     */
    private class ClickFloatBtn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (model.getPlayState().getValue()) {
                case OFF:
                    Toast.makeText(MainActivity.this, "请选择音乐", Toast.LENGTH_SHORT).show();
                    break;
                case PLAY:
                    model.getPlayState().setValue(PAUSE);
                    break;
                case PAUSE:
                    model.getPlayState().setValue(PLAY);
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
            switch (model.getPlayMode().getValue()) {
                case LOOP_ONE:
                    model.getPlayMode().setValue(LOOP_LIST);
                    break;
                case LOOP_LIST:
                    model.getPlayMode().setValue(RANDOM);
                    break;
                case RANDOM:
                    model.getPlayMode().setValue(LOOP_ONE);
                    break;
            }
        }
    }

    /**
     * 播放完成回调
     */
    private class MusicPlayComplete implements MediaPlayer.OnCompletionListener {
        private int getNext(int current) {
            int next = 0;
            switch (model.getPlayMode().getValue()) {
                case LOOP_LIST:
                    next = (current + 1) % model.getPlaylistData().getValue().getTracks().size();
                    break;
                case LOOP_ONE:
                    next = current;
                    break;
                case RANDOM:
                    next = (int) (Math.random() * model.getPlaylistData().getValue().getTracks().size());
                    break;
            }
            if (MainActivity.this.model.getPlaylistData().getValue().getTracks().get(next).getFee() == 4) { // 跳过付费歌曲
                return getNext(next);
            } else {
                return next;
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            int next = getNext(model.getCurrMusic().getValue());
            model.getCurrMusic().setValue(next);
        }
    }

    /**
     * 绑定服务
     */
    private class mServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MainActivity.this.binder = (MusicPlayerService.mBinder) service;
            binder.setCompletionListener(new MusicPlayComplete());
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

    @Override
    void onNetworkClose() {
        findViewById(R.id.error_view).setVisibility(View.VISIBLE);
    }

    @Override
    void onNetworkConnect() {
        findViewById(R.id.error_view).setVisibility(View.GONE);
        model.init();
    }
}