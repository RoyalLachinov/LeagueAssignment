package com.league.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserPostJoin implements Parcelable{

    private int userID;
    private String userProfilePicture;
    private String name;
    private String postTitle;
    private String postBody;
    private String userWebSite;
    private String userPhone;
    private String userEmail;


    public UserPostJoin(int userID, String userProfilePicture, String name, String postTitle, String postBody, String userWebSite, String userPhone, String userEmail) {
        this.userID = userID;
        this.userProfilePicture = userProfilePicture;
        this.name = name;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.userWebSite = userWebSite;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }


    protected UserPostJoin(Parcel in) {
        userID = in.readInt();
        userProfilePicture = in.readString();
        name = in.readString();
        postTitle = in.readString();
        postBody = in.readString();
        userWebSite = in.readString();
        userPhone = in.readString();
        userEmail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(userProfilePicture);
        dest.writeString(name);
        dest.writeString(postTitle);
        dest.writeString(postBody);
        dest.writeString(userWebSite);
        dest.writeString(userPhone);
        dest.writeString(userEmail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserPostJoin> CREATOR = new Creator<UserPostJoin>() {
        @Override
        public UserPostJoin createFromParcel(Parcel in) {
            return new UserPostJoin(in);
        }

        @Override
        public UserPostJoin[] newArray(int size) {
            return new UserPostJoin[size];
        }
    };

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserWebSite() {
        return userWebSite;
    }

    public void setUserWebSite(String userWebSite) {
        this.userWebSite = userWebSite;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
