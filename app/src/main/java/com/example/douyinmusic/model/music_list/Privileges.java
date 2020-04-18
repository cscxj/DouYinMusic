/**
  * Copyright 2020 jb51.net 
  */
package com.example.douyinmusic.model.music_list;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2020-04-17 23:38:8
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Privileges {

    private int id;
    private int fee;
    private int payed;
    private int st;
    private int pl;
    private int dl;
    private int sp;
    private int cp;
    private int subp;
    private boolean cs;
    private int maxbr;
    private int fl;
    private boolean toast;
    private int flag;
    @JsonProperty("preSell")
    private boolean presell;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setFee(int fee) {
         this.fee = fee;
     }
     public int getFee() {
         return fee;
     }

    public void setPayed(int payed) {
         this.payed = payed;
     }
     public int getPayed() {
         return payed;
     }

    public void setSt(int st) {
         this.st = st;
     }
     public int getSt() {
         return st;
     }

    public void setPl(int pl) {
         this.pl = pl;
     }
     public int getPl() {
         return pl;
     }

    public void setDl(int dl) {
         this.dl = dl;
     }
     public int getDl() {
         return dl;
     }

    public void setSp(int sp) {
         this.sp = sp;
     }
     public int getSp() {
         return sp;
     }

    public void setCp(int cp) {
         this.cp = cp;
     }
     public int getCp() {
         return cp;
     }

    public void setSubp(int subp) {
         this.subp = subp;
     }
     public int getSubp() {
         return subp;
     }

    public void setCs(boolean cs) {
         this.cs = cs;
     }
     public boolean getCs() {
         return cs;
     }

    public void setMaxbr(int maxbr) {
         this.maxbr = maxbr;
     }
     public int getMaxbr() {
         return maxbr;
     }

    public void setFl(int fl) {
         this.fl = fl;
     }
     public int getFl() {
         return fl;
     }

    public void setToast(boolean toast) {
         this.toast = toast;
     }
     public boolean getToast() {
         return toast;
     }

    public void setFlag(int flag) {
         this.flag = flag;
     }
     public int getFlag() {
         return flag;
     }

    public void setPresell(boolean presell) {
         this.presell = presell;
     }
     public boolean getPresell() {
         return presell;
     }

}