package com.league.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class UserPosts {

    @PrimaryKey
    @SerializedName("id")
    private int postID;
    @SerializedName("userId")
    private int userID;
    @SerializedName("title")
    private String postTitle;
    @SerializedName("body")
    private String postBody;



    public UserPosts(int userID, int postID, String postTitle, String postBody) {
        this.userID = userID;
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
