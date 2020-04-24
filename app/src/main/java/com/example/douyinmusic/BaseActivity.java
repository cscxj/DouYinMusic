package com.example.douyinmusic;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.douyinmusic.client.NetworkConnectState;

public class BaseActivity extends AppCompatActivity {
    MyApplication app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (MyApplication)getApplication();
        //
        app.getNetworkConnectState().observe(this, new Observer<NetworkConnectState>() {
            @Override
            public void onChanged(NetworkConnectState state) {
                if (state == NetworkConnectState.NONE){
                    Toast.makeText(BaseActivity.this, "网络断开", Toast.LENGTH_SHORT).show();
                    onNetworkClose();
                }   else {
                    Toast.makeText(BaseActivity.this, "网络已连接", Toast.LENGTH_SHORT).show();
                    onNetworkConnect();
                }
            }
        });
    }

    /**
     * 网络断开钩子
     */
    void onNetworkClose(){

    }

    /**
     * 网络连接钩子
     */
    void onNetworkConnect(){

    }
}
