package com.league.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserAlbums {

    @PrimaryKey
    @SerializedName("id")
    private int albumID;
    @SerializedName("userId")
    private int userID;
    @SerializedName("title")
    private String albumTitle;


    public UserAlbums(int userID, int albumID, String albumTitle) {
        this.userID = userID;
        this.albumID = albumID;
        this.albumTitle = albumTitle;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }
}
