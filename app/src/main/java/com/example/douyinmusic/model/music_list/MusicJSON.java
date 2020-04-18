/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.music_list;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Auto-generated: 2020-04-17 23:38:8
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class MusicJSON {

    private int code;
    @JsonProperty("relatedVideos")
    private String relatedvideos;
    private Playlist playlist;
    private String urls;
    private List<Privileges> privileges;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setRelatedvideos(String relatedvideos) {
         this.relatedvideos = relatedvideos;
     }
     public String getRelatedvideos() {
         return relatedvideos;
     }

    public void setPlaylist(Playlist playlist) {
         this.playlist = playlist;
     }
     public Playlist getPlaylist() {
         return playlist;
     }

    public void setUrls(String urls) {
         this.urls = urls;
     }
     public String getUrls() {
         return urls;
     }

    public void setPrivileges(List<Privileges> privileges) {
         this.privileges = privileges;
     }
     public List<Privileges> getPrivileges() {
         return privileges;
     }

}