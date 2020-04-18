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
public class Playlist {

    private List<String> subscribers;
    private boolean subscribed;
    private Creator creator;
    private List<Tracks> tracks;
    @JsonProperty("trackIds")
    private List<Trackids> trackids;
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
    private String description;
    private boolean ordered;
    private int status;
    @JsonProperty("adType")
    private int adtype;
    @JsonProperty("trackNumberUpdateTime")
    private int tracknumberupdatetime;
    @JsonProperty("subscribedCount")
    private int subscribedcount;
    @JsonProperty("cloudTrackCount")
    private int cloudtrackcount;
    @JsonProperty("commentThreadId")
    private String commentthreadid;
    @JsonProperty("updateTime")
    private int updatetime;
    @JsonProperty("trackCount")
    private int trackcount;
    @JsonProperty("specialType")
    private int specialtype;
    @JsonProperty("newImported")
    private boolean newimported;
    @JsonProperty("createTime")
    private int createtime;
    @JsonProperty("highQuality")
    private boolean highquality;
    private int privacy;
    @JsonProperty("trackUpdateTime")
    private int trackupdatetime;
    @JsonProperty("playCount")
    private int playcount;
    @JsonProperty("coverImgUrl")
    private String coverimgurl;
    @JsonProperty("coverImgId")
    private int coverimgid;
    @JsonProperty("userId")
    private int userid;
    private String name;
    private long id;
    private List<String> tags;
    @JsonProperty("shareCount")
    private int sharecount;
    @JsonProperty("coverImgId_str")
    private String coverimgidStr;
    @JsonProperty("commentCount")
    private int commentcount;
    public void setSubscribers(List<String> subscribers) {
         this.subscribers = subscribers;
     }
     public List<String> getSubscribers() {
         return subscribers;
     }

    public void setSubscribed(boolean subscribed) {
         this.subscribed = subscribed;
     }
     public boolean getSubscribed() {
         return subscribed;
     }

    public void setCreator(Creator creator) {
         this.creator = creator;
     }
     public Creator getCreator() {
         return creator;
     }

    public void setTracks(List<Tracks> tracks) {
         this.tracks = tracks;
     }
     public List<Tracks> getTracks() {
         return tracks;
     }

    public void setTrackids(List<Trackids> trackids) {
         this.trackids = trackids;
     }
     public List<Trackids> getTrackids() {
         return trackids;
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

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setOrdered(boolean ordered) {
         this.ordered = ordered;
     }
     public boolean getOrdered() {
         return ordered;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
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

    public void setSubscribedcount(int subscribedcount) {
         this.subscribedcount = subscribedcount;
     }
     public int getSubscribedcount() {
         return subscribedcount;
     }

    public void setCloudtrackcount(int cloudtrackcount) {
         this.cloudtrackcount = cloudtrackcount;
     }
     public int getCloudtrackcount() {
         return cloudtrackcount;
     }

    public void setCommentthreadid(String commentthreadid) {
         this.commentthreadid = commentthreadid;
     }
     public String getCommentthreadid() {
         return commentthreadid;
     }

    public void setUpdatetime(int updatetime) {
         this.updatetime = updatetime;
     }
     public int getUpdatetime() {
         return updatetime;
     }

    public void setTrackcount(int trackcount) {
         this.trackcount = trackcount;
     }
     public int getTrackcount() {
         return trackcount;
     }

    public void setSpecialtype(int specialtype) {
         this.specialtype = specialtype;
     }
     public int getSpecialtype() {
         return specialtype;
     }

    public void setNewimported(boolean newimported) {
         this.newimported = newimported;
     }
     public boolean getNewimported() {
         return newimported;
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

    public void setPrivacy(int privacy) {
         this.privacy = privacy;
     }
     public int getPrivacy() {
         return privacy;
     }

    public void setTrackupdatetime(int trackupdatetime) {
         this.trackupdatetime = trackupdatetime;
     }
     public int getTrackupdatetime() {
         return trackupdatetime;
     }

    public void setPlaycount(int playcount) {
         this.playcount = playcount;
     }
     public int getPlaycount() {
         return playcount;
     }

    public void setCoverimgurl(String coverimgurl) {
         this.coverimgurl = coverimgurl;
     }
     public String getCoverimgurl() {
         return coverimgurl;
     }

    public void setCoverimgid(int coverimgid) {
         this.coverimgid = coverimgid;
     }
     public int getCoverimgid() {
         return coverimgid;
     }

    public void setUserid(int userid) {
         this.userid = userid;
     }
     public int getUserid() {
         return userid;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setId(int id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setTags(List<String> tags) {
         this.tags = tags;
     }
     public List<String> getTags() {
         return tags;
     }

    public void setSharecount(int sharecount) {
         this.sharecount = sharecount;
     }
     public int getSharecount() {
         return sharecount;
     }

    public void setCoverimgidStr(String coverimgidStr) {
         this.coverimgidStr = coverimgidStr;
     }
     public String getCoverimgidStr() {
         return coverimgidStr;
     }

    public void setCommentcount(int commentcount) {
         this.commentcount = commentcount;
     }
     public int getCommentcount() {
         return commentcount;
     }

}