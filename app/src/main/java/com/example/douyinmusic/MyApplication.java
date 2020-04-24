package com.example.douyinmusic;

import android.app.Application;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.example.douyinmusic.client.NetworkChange;
import com.example.douyinmusic.client.NetworkConnectState;
import static com.example.douyinmusic.client.NetworkChange.getConnectedType;

public class MyApplication extends Application {

    MutableLiveData<NetworkConnectState> networkConnectState = new MutableLiveData<>(); // 网络连接状态

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化网络状态
        //networkConnectState.setValue(getConnectedType(this));
        // 注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkChange networkChange = NetworkChange.getInstance();
        networkChange.pushChangeListener(new NetworkChange.NetworkChangeListener() {
            @Override
            public void onChange(NetworkConnectState oldState, NetworkConnectState newState) {
                MyApplication.this.networkConnectState.setValue(newState);
            }
        });
        registerReceiver(NetworkChange.getInstance(), intentFilter);
    }

    public MutableLiveData<NetworkConnectState> getNetworkConnectState() {
        return networkConnectState;
    }
}
