package com.league.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserPostJoinDao {

   @Query("SELECT  UserPosts.postTitle, UserPosts.postBody, User.userID, User.userProfilePicture, User.name, User.userEmail, " +
           " User.userPhone, User.userWebSite From UserPosts JOIN User ON UserPosts.userID = User.userID")
    List<UserPostJoin> getPostByUserID();


}
