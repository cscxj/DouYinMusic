package com.example.douyinmusic.client;

import android.util.Log;

import com.example.douyinmusic.api.Api;
import com.example.douyinmusic.model.Music;
import com.example.douyinmusic.model.music_list.MusicJSON;
import com.example.douyinmusic.model.music_list.Tracks;
import com.example.douyinmusic.model.music_url.MusicUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Client {
    private static OkHttpClient client;

    private Client() {

    }

    public static void start() {
        client = new OkHttpClient();
    }


    /*
     *  获取音乐列表的数据，直接返回JSON映射对象。
     * */
    public static void getMusicList(final TaskCompleteCallback<MusicJSON> callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Request request = new Request.Builder()
                        .url(Api.TOP_LIST)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    String jsonData = response.body().string();
                    //callback.completed(response.body().string());
                    Gson gson = new GsonBuilder()
                            .setLenient()// json宽松
                            .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                            //.serializeNulls() //智能null
                            .setPrettyPrinting()// 调教格式
                            .disableHtmlEscaping() //默认是GSON把HTML 转义的
                            .create();
                    MusicJSON musicJSON = gson.fromJson(jsonData, MusicJSON.class);
                    callback.completed(musicJSON);
                } catch (IOException e) {
                    callback.completed(new MusicJSON());
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void getMusic(final TaskCompleteCallback<MusicUrl> callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Request request = new Request.Builder()
                        .url(Api.MUSIC_URL)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    String jsonData = response.body().string();
                    //callback.completed(response.body().string());
                    Gson gson = new GsonBuilder()
                            .setLenient()// json宽松
                            .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                            .serializeNulls() //智能null
                            .setPrettyPrinting()// 调教格式
                            .disableHtmlEscaping() //默认是GSON把HTML 转义的
                            .create();
                    MusicUrl musicUrl = gson.fromJson(jsonData, MusicUrl.class);
                    callback.completed(musicUrl);
                } catch (IOException e) {
                    callback.completed(new MusicUrl());
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
