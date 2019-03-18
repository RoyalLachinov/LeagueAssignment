package com.league.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.league.model.UserPhotos;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserPhotosDao {

    @Insert(onConflict = REPLACE)
    void insertUserPhotos(UserPhotos userPhotos);

    @Query("Select * from UserPhotos join UserAlbums on UserPhotos.albumID = UserAlbums.albumID and UserAlbums.userID = :userID")
    List<UserPhotos> getPhotosByUserID(int userID);
}
