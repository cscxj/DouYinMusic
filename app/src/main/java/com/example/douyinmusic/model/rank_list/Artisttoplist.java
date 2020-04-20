/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.rank_list;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2020-04-20 15:44:3
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Artisttoplist {

    @JsonProperty("coverUrl")
    private String coverurl;
    private String name;
    @JsonProperty("upateFrequency")
    private String upatefrequency;
    private int position;
    @JsonProperty("updateFrequency")
    private String updatefrequency;
    public void setCoverurl(String coverurl) {
         this.coverurl = coverurl;
     }
     public String getCoverurl() {
         return coverurl;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setUpatefrequency(String upatefrequency) {
         this.upatefrequency = upatefrequency;
     }
     public String getUpatefrequency() {
         return upatefrequency;
     }

    public void setPosition(int position) {
         this.position = position;
     }
     public int getPosition() {
         return position;
     }

    public void setUpdatefrequency(String updatefrequency) {
         this.updatefrequency = updatefrequency;
     }
     public String getUpdatefrequency() {
         return updatefrequency;
     }

}