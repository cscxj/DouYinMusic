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
public class Tracks {

    private String name;
    private int id;
    private int pst;
    private int t;
    private List<Ar> ar;
    private List<String> alia;
    private int pop;
    private int st;
    private String rt;
    private int fee;
    private int v;
    private String crbt;
    private String cf;
    @JsonProperty("al")
    private Al al;
    private int dt;
    private H h;
    private M m;
    private L l;
    private String a;
    private String cd;
    private int no;
    @JsonProperty("rtUrl")
    private String rturl;
    private int ftype;
    @JsonProperty("rtUrls")
    private List<String> rturls;
    @JsonProperty("djId")
    private int djid;
    private int copyright;
    @JsonProperty("s_id")
    private int sId;
    private int mark;
    private String rurl;
    private int mst;
    private int cp;
    private int mv;
    private int rtype;
    @JsonProperty("publishTime")
    private int publishtime;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setPst(int pst) {
         this.pst = pst;
     }
     public int getPst() {
         return pst;
     }

    public void setT(int t) {
         this.t = t;
     }
     public int getT() {
         return t;
     }

    public void setAr(List<Ar> ar) {
         this.ar = ar;
     }
     public List<Ar> getAr() {
         return ar;
     }

    public void setAlia(List<String> alia) {
         this.alia = alia;
     }
     public List<String> getAlia() {
         return alia;
     }

    public void setPop(int pop) {
         this.pop = pop;
     }
     public int getPop() {
         return pop;
     }

    public void setSt(int st) {
         this.st = st;
     }
     public int getSt() {
         return st;
     }

    public void setRt(String rt) {
         this.rt = rt;
     }
     public String getRt() {
         return rt;
     }

    public void setFee(int fee) {
         this.fee = fee;
     }
     public int getFee() {
         return fee;
     }

    public void setV(int v) {
         this.v = v;
     }
     public int getV() {
         return v;
     }

    public void setCrbt(String crbt) {
         this.crbt = crbt;
     }
     public String getCrbt() {
         return crbt;
     }

    public void setCf(String cf) {
         this.cf = cf;
     }
     public String getCf() {
         return cf;
     }

    public void setAl(Al al) {
         this.al = al;
     }
     public Al getAl() {
         return al;
     }

    public void setDt(int dt) {
         this.dt = dt;
     }
     public int getDt() {
         return dt;
     }

    public void setH(H h) {
         this.h = h;
     }
     public H getH() {
         return h;
     }

    public void setM(M m) {
         this.m = m;
     }
     public M getM() {
         return m;
     }

    public void setL(L l) {
         this.l = l;
     }
     public L getL() {
         return l;
     }

    public void setA(String a) {
         this.a = a;
     }
     public String getA() {
         return a;
     }

    public void setCd(String cd) {
         this.cd = cd;
     }
     public String getCd() {
         return cd;
     }

    public void setNo(int no) {
         this.no = no;
     }
     public int getNo() {
         return no;
     }

    public void setRturl(String rturl) {
         this.rturl = rturl;
     }
     public String getRturl() {
         return rturl;
     }

    public void setFtype(int ftype) {
         this.ftype = ftype;
     }
     public int getFtype() {
         return ftype;
     }

    public void setRturls(List<String> rturls) {
         this.rturls = rturls;
     }
     public List<String> getRturls() {
         return rturls;
     }

    public void setDjid(int djid) {
         this.djid = djid;
     }
     public int getDjid() {
         return djid;
     }

    public void setCopyright(int copyright) {
         this.copyright = copyright;
     }
     public int getCopyright() {
         return copyright;
     }

    public void setSId(int sId) {
         this.sId = sId;
     }
     public int getSId() {
         return sId;
     }

    public void setMark(int mark) {
         this.mark = mark;
     }
     public int getMark() {
         return mark;
     }

    public void setRurl(String rurl) {
         this.rurl = rurl;
     }
     public String getRurl() {
         return rurl;
     }

    public void setMst(int mst) {
         this.mst = mst;
     }
     public int getMst() {
         return mst;
     }

    public void setCp(int cp) {
         this.cp = cp;
     }
     public int getCp() {
         return cp;
     }

    public void setMv(int mv) {
         this.mv = mv;
     }
     public int getMv() {
         return mv;
     }

    public void setRtype(int rtype) {
         this.rtype = rtype;
     }
     public int getRtype() {
         return rtype;
     }

    public void setPublishtime(int publishtime) {
         this.publishtime = publishtime;
     }
     public int getPublishtime() {
         return publishtime;
     }

}