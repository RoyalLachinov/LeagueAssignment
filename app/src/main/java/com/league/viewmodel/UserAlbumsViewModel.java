package com.league.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.league.api.ApiClient;
import com.league.db.DatabaseConnection;
import com.league.model.UserAlbums;

import java.util.ArrayList;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class UserAlbumsViewModel extends AndroidViewModel {

    private DatabaseConnection databaseConnection;

    public UserAlbumsViewModel(Application application) {
        super(application);
        databaseConnection = DatabaseConnection.getDatabaseInstance(this.getApplication());
    }

    public void insertUserAlbums() {

        ApiClient.getApiService().getAllUserAlbums(ApiClient.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ArrayList<UserAlbums>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<UserAlbums> albumList) {
                        if (!albumList.isEmpty()) {
                            for (UserAlbums userAlbum : albumList) {
                                databaseConnection.getUserAlbumDao().insertUserAlbums
                                        (new UserAlbums(userAlbum.getUserID(), userAlbum.getAlbumID(), userAlbum.getAlbumTitle()));
                            }
                        }
                    }
                });

    }


}
