package com.league.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.league.R;
import com.league.api.ApiClient;
import com.league.db.DatabaseConnection;
import com.league.model.User;
import com.league.model.UserPhotos;
import com.league.model.UserPostJoin;
import com.league.model.UserPosts;
import com.league.viewmodel.UserAlbumsViewModel;
import com.league.viewmodel.UserPhotosViewModel;
import com.league.viewmodel.UserPostViewModel;
import com.league.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPostsActivity extends AppCompatActivity implements UserPostsView {

    @BindView(R.id.listViewPosts)
    RecyclerView listViewPosts;

    private Thread thread;
    private List<UserPosts> postsList;
    private List<UserPostJoin> userPosts;
    private UserPostViewModel userPostViewModel;
    private UserViewModel userViewModel;
    private UserAlbumsViewModel userAlbumsViewModel;
    private UserPhotosViewModel userPhotosViewModel;
    private User selectedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userPostViewModel = ViewModelProviders.of(UserPostsActivity.this).get(UserPostViewModel.class);
        userPostViewModel.insertUserPosts();

        userViewModel = ViewModelProviders.of(UserPostsActivity.this).get(UserViewModel.class);
        userViewModel.insertUsers();

        userAlbumsViewModel = ViewModelProviders.of(UserPostsActivity.this).get(UserAlbumsViewModel.class);
        userAlbumsViewModel.insertUserAlbums();

        userPhotosViewModel = ViewModelProviders.of(UserPostsActivity.this).get(UserPhotosViewModel.class);
        userPhotosViewModel.insertUserPhotos();

        getAllPosts();
    }

    @Override
    public void showUserProfilePage(UserPostJoin user) {

        Intent intent = new Intent(UserPostsActivity.this, UserProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_user", (Parcelable) user);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void getAllPosts() {

        thread = new Thread() {
            @Override
            public void run() {
                synchronized (this) {

                    postsList = userPostViewModel.getAllUserPosts();
                    userPosts = userPostViewModel.getUserPostsByUserID();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            listViewPosts.setLayoutManager(new LinearLayoutManager(UserPostsActivity.this, LinearLayoutManager.VERTICAL, false));
                            listViewPosts.setAdapter(new AdapterPosts(UserPostsActivity.this, userPosts));
                        }
                    });

                }
            }

            ;
        };
        thread.start();


    }
}
