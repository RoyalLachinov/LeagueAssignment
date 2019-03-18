package com.league.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.league.R;
import com.league.model.UserPhotos;
import com.league.model.UserPostJoin;
import com.league.model.UserPosts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewHolder> {

    private PhotosView viewImpl;
    private List<UserPhotos> userPhotos;


    public AdapterPhotos(PhotosView viewImpl, List<UserPhotos> userPhotos) {

        this.viewImpl = viewImpl;
        this.userPhotos = userPhotos;
    }

    @Override
    public int getItemCount() {
        return userPhotos.size();
    }

    @Override
    public void onBindViewHolder(final AdapterPhotos.ViewHolder contactViewHolder, final int i) {

        Glide.with(contactViewHolder.imgUserProfilePic.getContext())
                .load(userPhotos.get(i).getPhotoUrl())
                .into(contactViewHolder.imgUserProfilePic);


        contactViewHolder.imgUserProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewImpl.shwoImageInFullScreen(contactViewHolder.imgUserProfilePic, userPhotos.get(i).getPhotoUrl());

            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public AdapterPhotos.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photos, viewGroup, false);

        return new AdapterPhotos.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgUserProfilePic)
        protected ImageView imgUserProfilePic;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
