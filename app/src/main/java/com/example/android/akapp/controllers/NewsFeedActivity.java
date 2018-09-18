package com.example.android.akapp.controllers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.android.akapp.R;
import com.example.android.akapp.models.entities.PostEntity;
import com.example.android.akapp.views.PostListViewAdapter;
import com.example.android.akapp.views.PostViewModel;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

@EActivity(R.layout.activity_news_feed)
public class NewsFeedActivity extends AppCompatActivity {

    @ViewById(R.id.navigation)
    public BottomNavigationView navigation;
    @ViewById(R.id.postListView)
    ListView postListView;
    LiveData<List<PostEntity>> postList;
    private PostViewModel postViewModel;


    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news_feed:
                    Intent intent = new Intent(NewsFeedActivity.this, NewsFeedActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_chat:
                    Intent intent1 = new Intent(NewsFeedActivity.this, ChatActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.navigation_calendar:
                    Intent intent2 = new Intent(NewsFeedActivity.this, Calendar.class);
                    startActivity(intent2);
                    break;
                case R.id.navigation_settings:
                    Intent intent3 = new Intent(NewsFeedActivity.this, SettingsActivity.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        postList = postViewModel.getAllMessages();

        final PostListViewAdapter postListViewAdapter = new PostListViewAdapter(this, postList);

        postViewModel.getAllMessages().observe(this, new Observer<List<PostEntity>>() {
            @Override
            public void onChanged(@Nullable List<PostEntity> postEntities) {
                postListView.setAdapter(postListViewAdapter);
            }
        });

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
