package com.league.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.league.api.ApiClient;
import com.league.db.DatabaseConnection;
import com.league.model.UserPhotos;
import com.league.model.UserPostJoin;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class UserPhotosViewModel extends AndroidViewModel {


    private DatabaseConnection databaseConnection;

    public UserPhotosViewModel(Application application) {
        super(application);
        databaseConnection = DatabaseConnection.getDatabaseInstance(this.getApplication());
    }


    public void insertUserPhotos() {

        ApiClient.getApiService().getAllUserPhotos(ApiClient.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ArrayList<UserPhotos>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<UserPhotos> photoList) {
                        if (!photoList.isEmpty()) {
                            for (UserPhotos photo : photoList) {
                                databaseConnection.getPhotosDao().insertUserPhotos(new UserPhotos(photo.getAlbumID(), photo.getPhotoID(), photo.getPhotoTitle(), photo.getPhotoUrl(), photo.getThumbnailUrl()));
                            }
                        }
                    }
                });

    }


    public List<UserPhotos> getPhotosByUserID(int userID){
        return databaseConnection.getPhotosDao().getPhotosByUserID(userID);
    }

}
