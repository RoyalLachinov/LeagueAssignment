package com.league.view;

import com.league.model.User;
import com.league.model.UserPostJoin;
import com.league.model.UserPosts;

import java.util.ArrayList;
import java.util.List;

public interface UserPostsView {

    void showUserProfilePage(UserPostJoin user);

    void getAllPosts();
}
