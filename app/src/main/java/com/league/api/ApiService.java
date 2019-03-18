package com.league.api;

import com.league.model.User;
import com.league.model.UserAlbums;
import com.league.model.UserPhotos;
import com.league.model.UserPosts;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

public interface ApiService {

    @GET("users")
    Observable<ArrayList<User>> getAllUsers(@Header("x-access-token") String accessToken);

    @GET("posts")
    Observable<ArrayList<UserPosts>> getAllUserPosts(@Header("x-access-token") String accessToken);

    @GET("albums")
    Observable<ArrayList<UserAlbums>> getAllUserAlbums(@Header("x-access-token") String accessToken);

    @GET("photos")
    Observable<ArrayList<UserPhotos>> getAllUserPhotos(@Header("x-access-token") String accessToken);

}
