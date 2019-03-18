package com.league.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.league.api.ApiClient;
import com.league.db.DatabaseConnection;
import com.league.model.User;

import java.util.ArrayList;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel{

    private DatabaseConnection databaseConnection;

    public UserViewModel(Application application) {
        super(application);
        databaseConnection = DatabaseConnection.getDatabaseInstance(this.getApplication());
    }


    public void insertUsers() {

        ApiClient.getApiService().getAllUsers(ApiClient.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ArrayList<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("error happ", e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<User> userList) {
                        if (!userList.isEmpty()) {
                            for (User user : userList) {
                                databaseConnection.getUserDao().insertUser
                                        (new User(user.getUserID(), user.getUserImage(),user.getName(), user.getUserName(), user.getUserEmail(),
                                                user.getUserAddress(), user.getUserPhone(), user.getUserWebSite(), user.getUserCompany()));
                            }
                        }
                    }
                });

    }


    public User getUseryID(int userID){
        return databaseConnection.getUserDao().getUserByID(userID);
    }

}
