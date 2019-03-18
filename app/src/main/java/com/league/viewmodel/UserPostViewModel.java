package com.league.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.league.api.ApiClient;
import com.league.db.DatabaseConnection;
import com.league.model.User;
import com.league.model.UserAlbums;
import com.league.model.UserPhotos;
import com.league.model.UserPostDao;
import com.league.model.UserPostJoin;
import com.league.model.UserPosts;
import com.league.view.UserPostsActivity;
import com.league.view.UserPostsView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class UserPostViewModel extends AndroidViewModel {

    private DatabaseConnection databaseConnection;

    public UserPostViewModel(Application application) {
        super(application);
        databaseConnection = DatabaseConnection.getDatabaseInstance(this.getApplication());
    }

    public void insertUserPosts() {
        ApiClient.getApiService().getAllUserPosts(ApiClient.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ArrayList<UserPosts>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<UserPosts> userPosts) {
                        if (!userPosts.isEmpty()) {
                            for (UserPosts userPost : userPosts) {
                                databaseConnection.getPostDao().insertUserPosts
                                        (new UserPosts(userPost.getUserID(), userPost.getPostID(), userPost.getPostTitle(), userPost.getPostBody()));
                            }
                        }
                    }
                });
    }


    public UserPosts getUserPostByID(int id) {
        return databaseConnection.getPostDao().getUserPostByID(id);
    }

    public List<UserPosts> getAllUserPosts() {
        return databaseConnection.getPostDao().getAllUserPosts();
    }

    public List<UserPostJoin>getUserPostsByUserID(){
        return databaseConnection.getPostByUserIDDao().getPostByUserID();
    }
}
