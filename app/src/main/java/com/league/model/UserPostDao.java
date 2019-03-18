package com.league.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface UserPostDao {

    @Insert(onConflict = REPLACE)
    void insertUserPosts(UserPosts userPosts);

    @Query("Select * from UserPosts where userID = :id")
    UserPosts getUserPostByID(int id);

    @Query("Select * From userposts")
    List<UserPosts> getAllUserPosts();


}
