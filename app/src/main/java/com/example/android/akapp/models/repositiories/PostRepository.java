package com.example.android.akapp.models.repositiories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.akapp.models.database.DaoAccess;
import com.example.android.akapp.models.database.DataBase;
import com.example.android.akapp.models.entities.PostEntity;

import java.util.List;

public class PostRepository {
    private DaoAccess daoAccess;
    private LiveData<List<PostEntity>> allMessages;

    public PostRepository(Application application){
        DataBase dataBase = DataBase.getDataBaseInstance();
        daoAccess = dataBase.daoAccess();
        allMessages = daoAccess.getAllMessages();

    }

    public LiveData<List<PostEntity>> getAllMessages(){
        return allMessages;
    }

    public void insert(PostEntity postEntity){

        new insertAsyncTask(daoAccess).execute(postEntity);
    }

    private static class insertAsyncTask extends AsyncTask<PostEntity, Void, Void> {

        private DaoAccess asyncTaskDao;

        insertAsyncTask(DaoAccess dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PostEntity... params) {
            asyncTaskDao.insertMessageToDataBase(params[0]);
            return null;
        }
    }
}