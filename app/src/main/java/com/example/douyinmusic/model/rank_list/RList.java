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
public class RList {

    private List<String> subscribers;
    private String subscribed;
    private String creator;
    private String artists;
    private String tracks;
    @JsonProperty("updateFrequency")
    private String updatefrequency;
    @JsonProperty("backgroundCoverId")
    private int backgroundcoverid;
    @JsonProperty("backgroundCoverUrl")
    private String backgroundcoverurl;
    @JsonProperty("titleImage")
    private int titleimage;
    @JsonProperty("titleImageUrl")
    private String titleimageurl;
    @JsonProperty("englishTitle")
    private String englishtitle;
    @JsonProperty("opRecommend")
    private boolean oprecommend;
    @JsonProperty("recommendInfo")
    private String recommendinfo;
    private boolean ordered;
    private String description;
    @JsonProperty("adType")
    private int adtype;
    @JsonProperty("trackNumberUpdateTime")
    private int tracknumberupdatetime;
    @JsonProperty("cloudTrackCount")
    private int cloudtrackcount;
    @JsonProperty("subscribedCount")
    private int subscribedcount;
    private int status;
    private List<String> tags;
    @JsonProperty("createTime")
    private int createtime;
    @JsonProperty("highQuality")
    private boolean highquality;
    @JsonProperty("userId")
    private int userid;
    @JsonProperty("trackCount")
    private int trackcount;
    @JsonProperty("playCount")
    private int playcount;
    @JsonProperty("trackUpdateTime")
    private int trackupdatetime;
    @JsonProperty("coverImgId")
    private int coverimgid;
    @JsonProperty("newImported")
    private boolean newimported;
    private boolean anonimous;
    @JsonProperty("updateTime")
    private int updatetime;
    @JsonProperty("specialType")
    private int specialtype;
    @JsonProperty("commentThreadId")
    private String commentthreadid;
    private int privacy;
    @JsonProperty("coverImgUrl")
    private String coverImgUrl;
    @JsonProperty("totalDuration")
    private int totalduration;
    private String name;
    private long id;
    @JsonProperty("coverImgId_str")
    private String coverimgidStr;
    @JsonProperty("ToplistType")
    private String toplisttype;
    public void setSubscribers(List<String> subscribers) {
         this.subscribers = subscribers;
     }
     public List<String> getSubscribers() {
         return subscribers;
     }

    public void setSubscribed(String subscribed) {
         this.subscribed = subscribed;
     }
     public String getSubscribed() {
         return subscribed;
     }

    public void setCreator(String creator) {
         this.creator = creator;
     }
     public String getCreator() {
         return creator;
     }

    public void setArtists(String artists) {
         this.artists = artists;
     }
     public String getArtists() {
         return artists;
     }

    public void setTracks(String tracks) {
         this.tracks = tracks;
     }
     public String getTracks() {
         return tracks;
     }

    public void setUpdatefrequency(String updatefrequency) {
         this.updatefrequency = updatefrequency;
     }
     public String getUpdatefrequency() {
         return updatefrequency;
     }

    public void setBackgroundcoverid(int backgroundcoverid) {
         this.backgroundcoverid = backgroundcoverid;
     }
     public int getBackgroundcoverid() {
         return backgroundcoverid;
     }

    public void setBackgroundcoverurl(String backgroundcoverurl) {
         this.backgroundcoverurl = backgroundcoverurl;
     }
     public String getBackgroundcoverurl() {
         return backgroundcoverurl;
     }

    public void setTitleimage(int titleimage) {
         this.titleimage = titleimage;
     }
     public int getTitleimage() {
         return titleimage;
     }

    public void setTitleimageurl(String titleimageurl) {
         this.titleimageurl = titleimageurl;
     }
     public String getTitleimageurl() {
         return titleimageurl;
     }

    public void setEnglishtitle(String englishtitle) {
         this.englishtitle = englishtitle;
     }
     public String getEnglishtitle() {
         return englishtitle;
     }

    public void setOprecommend(boolean oprecommend) {
         this.oprecommend = oprecommend;
     }
     public boolean getOprecommend() {
         return oprecommend;
     }

    public void setRecommendinfo(String recommendinfo) {
         this.recommendinfo = recommendinfo;
     }
     public String getRecommendinfo() {
         return recommendinfo;
     }

    public void setOrdered(boolean ordered) {
         this.ordered = ordered;
     }
     public boolean getOrdered() {
         return ordered;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setAdtype(int adtype) {
         this.adtype = adtype;
     }
     public int getAdtype() {
         return adtype;
     }

    public void setTracknumberupdatetime(int tracknumberupdatetime) {
         this.tracknumberupdatetime = tracknumberupdatetime;
     }
     public int getTracknumberupdatetime() {
         return tracknumberupdatetime;
     }

    public void setCloudtrackcount(int cloudtrackcount) {
         this.cloudtrackcount = cloudtrackcount;
     }
     public int getCloudtrackcount() {
         return cloudtrackcount;
     }

    public void setSubscribedcount(int subscribedcount) {
         this.subscribedcount = subscribedcount;
     }
     public int getSubscribedcount() {
         return subscribedcount;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setTags(List<String> tags) {
         this.tags = tags;
     }
     public List<String> getTags() {
         return tags;
     }

    public void setCreatetime(int createtime) {
         this.createtime = createtime;
     }
     public int getCreatetime() {
         return createtime;
     }

    public void setHighquality(boolean highquality) {
         this.highquality = highquality;
     }
     public boolean getHighquality() {
         return highquality;
     }

    public void setUserid(int userid) {
         this.userid = userid;
     }
     public int getUserid() {
         return userid;
     }

    public void setTrackcount(int trackcount) {
         this.trackcount = trackcount;
     }
     public int getTrackcount() {
         return trackcount;
     }

    public void setPlaycount(int playcount) {
         this.playcount = playcount;
     }
     public int getPlaycount() {
         return playcount;
     }

    public void setTrackupdatetime(int trackupdatetime) {
         this.trackupdatetime = trackupdatetime;
     }
     public int getTrackupdatetime() {
         return trackupdatetime;
     }

    public void setCoverimgid(int coverimgid) {
         this.coverimgid = coverimgid;
     }
     public int getCoverimgid() {
         return coverimgid;
     }

    public void setNewimported(boolean newimported) {
         this.newimported = newimported;
     }
     public boolean getNewimported() {
         return newimported;
     }

    public void setAnonimous(boolean anonimous) {
         this.anonimous = anonimous;
     }
     public boolean getAnonimous() {
         return anonimous;
     }

    public void setUpdatetime(int updatetime) {
         this.updatetime = updatetime;
     }
     public int getUpdatetime() {
         return updatetime;
     }

    public void setSpecialtype(int specialtype) {
         this.specialtype = specialtype;
     }
     public int getSpecialtype() {
         return specialtype;
     }

    public void setCommentthreadid(String commentthreadid) {
         this.commentthreadid = commentthreadid;
     }
     public String getCommentthreadid() {
         return commentthreadid;
     }

    public void setPrivacy(int privacy) {
         this.privacy = privacy;
     }
     public int getPrivacy() {
         return privacy;
     }

    public void setCoverimgurl(String coverimgurl) {
         this.coverImgUrl = coverimgurl;
     }
     public String getCoverimgurl() {
         return coverImgUrl;
     }

    public void setTotalduration(int totalduration) {
         this.totalduration = totalduration;
     }
     public int getTotalduration() {
         return totalduration;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setCoverimgidStr(String coverimgidStr) {
         this.coverimgidStr = coverimgidStr;
     }
     public String getCoverimgidStr() {
         return coverimgidStr;
     }

    public void setToplisttype(String toplisttype) {
         this.toplisttype = toplisttype;
     }
     public String getToplisttype() {
         return toplisttype;
     }

}