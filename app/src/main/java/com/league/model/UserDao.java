package com.league.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.league.model.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    //@Query("select User.userProfilePicture, User.name, User.userEmail, User.userPhone, User.userWebSite From User Where User.userID = :id")
    @Query("select * From user  Where User.userID = :id")
    User getUserByID(int id);

    @Query("Select * From user")
     List<User> getAllUsers();
}
