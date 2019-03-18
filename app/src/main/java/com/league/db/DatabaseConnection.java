package com.league.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.league.model.User;
import com.league.model.UserAlbums;
import com.league.model.UserAlbumsDao;
import com.league.model.UserDao;
import com.league.model.UserPhotos;
import com.league.model.UserPhotosDao;
import com.league.model.UserPostDao;
import com.league.model.UserPostJoinDao;
import com.league.model.UserPosts;

@Database(entities = {User.class, UserPosts.class, UserAlbums.class, UserPhotos.class}, version = 1, exportSchema = false)
public abstract class DatabaseConnection extends RoomDatabase {

    private static final String DB_NAME = "league.db";
    private static volatile DatabaseConnection databaseConnectionInstance;

    public static synchronized DatabaseConnection getDatabaseInstance(Context context) {
        if (databaseConnectionInstance == null) {
            databaseConnectionInstance = createDatabase(context);
        }

        return databaseConnectionInstance;
    }

    private static DatabaseConnection createDatabase(final Context context) {
        return Room.databaseBuilder(context, DatabaseConnection.class, DB_NAME).build();
    }


    public abstract UserDao getUserDao();

    public abstract UserPostDao getPostDao();

    public abstract UserAlbumsDao getUserAlbumDao();

    public abstract UserPhotosDao getPhotosDao();

    public abstract UserPostJoinDao getPostByUserIDDao();
}

