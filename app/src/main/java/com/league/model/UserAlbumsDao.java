package com.league.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.league.model.UserAlbums;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserAlbumsDao {

    @Insert(onConflict = REPLACE)
     void insertUserAlbums(UserAlbums userAlbums);
}
