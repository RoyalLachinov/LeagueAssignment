package com.league.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.league.R;
import com.league.model.User;
import com.league.model.UserPostJoin;
import com.league.model.UserPosts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder> {

    private UserPostsView viewImpl;
    private List<UserPostJoin> userPosts;


    public AdapterPosts(UserPostsView viewImpl, List<UserPostJoin> userPosts) {

        this.viewImpl = viewImpl;
        this.userPosts = userPosts;
    }

    @Override
    public int getItemCount() {
        return userPosts.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder contactViewHolder, final int i) {

        Glide.with(contactViewHolder.imgUserProfilePic.getContext())
                .load(userPosts.get(i).getUserProfilePicture())
                .into(contactViewHolder.imgUserProfilePic);

        contactViewHolder.txtUserName.setText(userPosts.get(i).getName());
        contactViewHolder.txtPostTitle.setText(userPosts.get(i).getPostTitle());
        contactViewHolder.txtPostBody.setText(userPosts.get(i).getPostBody());

        contactViewHolder.txtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewImpl.showUserProfilePage(userPosts.get(i));

            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_posts, viewGroup, false);

        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgUserProfilePic)
        protected ImageView imgUserProfilePic;

        @BindView(R.id.txtUserName)
        protected TextView txtUserName;

        @BindView(R.id.txtPostTitle)
        protected TextView txtPostTitle;

        @BindView(R.id.txtPostBody)
        protected TextView txtPostBody;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}

