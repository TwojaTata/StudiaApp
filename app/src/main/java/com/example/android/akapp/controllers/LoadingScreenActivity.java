package com.example.android.akapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.android.akapp.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_loading_screen)
public class LoadingScreenActivity extends AppCompatActivity {

    @ViewById(R.id.loadingScreenImage)
    ImageView loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        getSupportActionBar().hide();
        loadingScreen = findViewById(R.id.loadingScreenImage);
        startLoadingAnimation();


    }
    @UiThread
    public void startLoadingAnimation(){

        Animation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(5000);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animation animation) {
                                                    startLoginActivity();

                                                }

                                                @Override
                                                public void onAnimationRepeat(Animation animation) {

                                                }
                                            });
                loadingScreen.startAnimation(alphaAnimation);
    }
    @UiThread
    public void startLoginActivity(){

        Intent intent = new Intent(this, LoginAndRegisterActivity_.class);
        startActivity(intent);
    }
}

