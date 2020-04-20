package com.example.douyinmusic.ui;

import android.content.ClipData;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.douyinmusic.adapters.RankListAdapter;
import com.example.douyinmusic.client.Client;
import com.example.douyinmusic.client.TaskCompleteCallback;
import com.example.douyinmusic.model.music_list.MusicJSON;
import com.example.douyinmusic.model.rank_list.JSONRank;
import com.example.douyinmusic.model.rank_list.RList;

public class RankListViewModel extends ViewModel {
    private final int MESSAGE_DATA_LOADING = 0x00001;

    private final MutableLiveData<JSONRank> rankData = new MutableLiveData<JSONRank>();
    private final MutableLiveData<Integer> currentRank = new MutableLiveData<Integer>(); //初始为空

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_DATA_LOADING:
                    rankData.setValue((JSONRank) msg.obj);
            }
        }
    };

    public RankListViewModel() {
        super();
    }
    public void init(){
        // 启动客户端，获取数据
        Client.start();
        Client.getRankList(new GetDataComplete());
    }

    public LiveData<JSONRank> getRankData(){
        return this.rankData;
    }
    public void setCurrentRank(Integer rankIndex){
        this.currentRank.setValue(rankIndex);
    }
    public MutableLiveData<Integer> getCurrentRank() {
        return currentRank;
    }

    class GetDataComplete implements TaskCompleteCallback<JSONRank> {
        @Override
        public void completed(JSONRank res) {
            Message message = new Message();
            message.what = MESSAGE_DATA_LOADING;
            message.obj = res;
            handler.sendMessage(message);
        }
    }
}
