package com.example.douyinmusic.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class NetworkChange extends BroadcastReceiver {

    public static NetworkChange instance;
    public NetworkChangeListener changeListener; // 多个回调
    public NetworkConnectState oldState = NetworkConnectState.NONE;

    // 单例 , 打包的时候不呢个使用 private 不知道为啥
    //private NetworkChange() {}

    public static NetworkChange getInstance() {
        if (instance == null) {
            instance = new NetworkChange();
        }
        return instance;
    }

    public interface NetworkChangeListener {
        void onChange(NetworkConnectState oldState, NetworkConnectState newState);
    }


    public void pushChangeListener(NetworkChangeListener listener) {
        this.changeListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (changeListener == null) { //未设置监听，return
            return;
        }
        changeListener.onChange(this.oldState, getConnectedType(context));
        oldState = getConnectedType(context);
    }

    public static NetworkConnectState getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                switch (mNetworkInfo.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        return NetworkConnectState.WIFI;
                    case ConnectivityManager.TYPE_MOBILE:
                        return NetworkConnectState.MOBILE;
                }
            }
        }
        return NetworkConnectState.NONE;
    }

}


