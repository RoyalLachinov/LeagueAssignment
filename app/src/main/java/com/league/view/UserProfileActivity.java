package com.league.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.league.R;
import com.league.model.User;
import com.league.model.UserPhotos;
import com.league.model.UserPostJoin;
import com.league.viewmodel.RecyclerViewSpacesItemDecoration;
import com.league.viewmodel.UserPhotosViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity implements PhotosView {

    @BindView(R.id.imgUserProfilePic)
    CircleImageView imgUserProfilePic;
    @BindView(R.id.expanded_image)
    ImageView expandedImageView;
    @BindView(R.id.txtUserName)
    TextView txtUserName;
    @BindView(R.id.txtUserEmail)
    TextView txtUserEmail;
    @BindView(R.id.txtUserPhone)
    TextView txtUserPhone;
    @BindView(R.id.txtUserWebsite)
    TextView txtUserWebsite;
    @BindView(R.id.listPhotos)
    RecyclerView listPhotos;

    private UserPhotosViewModel userPhotosViewModel;
    private UserPostJoin user;
    private Thread thread;
    private List<UserPhotos> userPhotos;
    private AdapterPhotos adapterPhotos;
    private Animator currentAnimator;
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("selected_user");

        userPhotosViewModel = ViewModelProviders.of(UserProfileActivity.this).get(UserPhotosViewModel.class);
        userPhotosViewModel.insertUserPhotos();

        Glide.with(this)
                .load(user.getUserProfilePicture())
                .into(imgUserProfilePic);

        txtUserName.setText(user.getName());
        txtUserEmail.setText(user.getUserEmail());
        txtUserPhone.setText(user.getUserPhone());
        txtUserWebsite.setText(user.getUserWebSite());

        thread = new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    userPhotos = userPhotosViewModel.getPhotosByUserID(user.getUserID());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapterPhotos = new AdapterPhotos(UserProfileActivity.this, userPhotos);
                            listPhotos.setLayoutManager(new GridLayoutManager(UserProfileActivity.this, 3));
                            listPhotos.addItemDecoration(new RecyclerViewSpacesItemDecoration(UserProfileActivity.this));
                            listPhotos.setAdapter(adapterPhotos);
                        }
                    });

                }
            }

            ;
        };
        thread.start();

        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

    }

    @Override
    public void shwoImageInFullScreen(ImageView imageView, String picUrl) {
        zoomImageFromThumb(imageView, picUrl);
    }

    private void zoomImageFromThumb(final View thumbView, String imgUrl) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        //final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        //expandedImageView.setImageResource(imageResId);

        Glide.with(this)
                .load(imgUrl)
                .into(expandedImageView);


        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(shortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }


}
