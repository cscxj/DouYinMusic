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
public class Al {

    private int id;
    private String name;
    @JsonProperty("picUrl")
    private String picUrl;
    private List<String> tns;
    @JsonProperty("pic_str")
    private String picStr;
    private long pic;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPicurl(String picurl) {
         this.picUrl = picurl;
     }
     public String getPicurl() {
         return picUrl;
     }

    public void setTns(List<String> tns) {
         this.tns = tns;
     }
     public List<String> getTns() {
         return tns;
     }

    public void setPicStr(String picStr) {
         this.picStr = picStr;
     }
     public String getPicStr() {
         return picStr;
     }

    public void setPic(int pic) {
         this.pic = pic;
     }
     public long getPic() {
         return pic;
     }

}