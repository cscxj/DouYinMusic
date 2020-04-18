/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.music_url;
import java.util.List;

/**
 * Auto-generated: 2020-04-18 16:21:22
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class MusicUrl {

    private List<Data> data;
    private int code;
    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

}