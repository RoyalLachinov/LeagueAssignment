package com.league.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserPhotos {

    @PrimaryKey
    @SerializedName("id")
    private int photoID;
    @SerializedName("albumId")
    private int albumID;
    @SerializedName("title")
    private String photoTitle;
    @SerializedName("url")
    private String photoUrl;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;


    public UserPhotos(int albumID, int photoID, String photoTitle, String photoUrl, String thumbnailUrl) {
        this.albumID = albumID;
        this.photoID = photoID;
        this.photoTitle = photoTitle;
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
