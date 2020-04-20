/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.rank_list;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * Auto-generated: 2020-04-20 15:44:3
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class JSONRank {

    private int code;
    private List<RList> list;
    @JsonProperty("artistToplist")
    private Artisttoplist artisttoplist;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setList(List<RList> list) {
         this.list = list;
     }
     public List<RList> getList() {
         return list;
     }

    public void setArtisttoplist(Artisttoplist artisttoplist) {
         this.artisttoplist = artisttoplist;
     }
     public Artisttoplist getArtisttoplist() {
         return artisttoplist;
     }

}