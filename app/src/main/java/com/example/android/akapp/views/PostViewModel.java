package com.example.android.akapp.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.akapp.models.entities.PostEntity;
import com.example.android.akapp.models.repositiories.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository postRepository;

    private LiveData<List<PostEntity>> allMessages;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        allMessages = postRepository.getAllMessages();
    }

    public LiveData<List<PostEntity>> getAllMessages() {
        return allMessages;
    }
    public void insert(PostEntity postEntity){
        postRepository.insert(postEntity);
    }
}
