/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.music_url;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2020-04-18 16:21:22
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Data {

    private int id;
    private String url;
    private int br;
    private int size;
    private String md5;
    private int code;
    private int expi;
    private String type;
    private int gain;
    private int fee;
    private String uf;
    private int payed;
    private int flag;
    @JsonProperty("canExtend")
    private boolean canextend;
    @JsonProperty("freeTrialInfo")
    private String freetrialinfo;
    private String level;
    @JsonProperty("encodeType")
    private String encodetype;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setBr(int br) {
         this.br = br;
     }
     public int getBr() {
         return br;
     }

    public void setSize(int size) {
         this.size = size;
     }
     public int getSize() {
         return size;
     }

    public void setMd5(String md5) {
         this.md5 = md5;
     }
     public String getMd5() {
         return md5;
     }

    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setExpi(int expi) {
         this.expi = expi;
     }
     public int getExpi() {
         return expi;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setGain(int gain) {
         this.gain = gain;
     }
     public int getGain() {
         return gain;
     }

    public void setFee(int fee) {
         this.fee = fee;
     }
     public int getFee() {
         return fee;
     }

    public void setUf(String uf) {
         this.uf = uf;
     }
     public String getUf() {
         return uf;
     }

    public void setPayed(int payed) {
         this.payed = payed;
     }
     public int getPayed() {
         return payed;
     }

    public void setFlag(int flag) {
         this.flag = flag;
     }
     public int getFlag() {
         return flag;
     }

    public void setCanextend(boolean canextend) {
         this.canextend = canextend;
     }
     public boolean getCanextend() {
         return canextend;
     }

    public void setFreetrialinfo(String freetrialinfo) {
         this.freetrialinfo = freetrialinfo;
     }
     public String getFreetrialinfo() {
         return freetrialinfo;
     }

    public void setLevel(String level) {
         this.level = level;
     }
     public String getLevel() {
         return level;
     }

    public void setEncodetype(String encodetype) {
         this.encodetype = encodetype;
     }
     public String getEncodetype() {
         return encodetype;
     }

}