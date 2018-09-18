package com.example.android.akapp.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.akapp.models.entities.PostEntity;
import com.example.android.akapp.models.entities.UserEntity;

import java.util.List;

@Dao
public interface DaoAccess {

    @Query("SELECT password FROM users WHERE email = :userEmail" )
    String getUserPassword (String userEmail);
    @Query("SELECT * FROM messages ORDER BY creation_date DESC")
    LiveData<List<PostEntity>> getAllMessages ();
    @Insert
    void insertMessageToDataBase (PostEntity messageEntity);
    @Insert
    void insertUserToDataBase (UserEntity userEntity);
    @Query ("SELECT * FROM users WHERE email = :userEmail")
    UserEntity getUserByEmail (String userEmail);
    @Query ("SELECT * FROM users WHERE nick = :userNick")
    public UserEntity getUserByNick (String userNick);
    @Query ("SELECT * FROM users WHERE register_date = :userRegisterDate")
    UserEntity getUserByRegisterDate (String userRegisterDate);
    @Query("DELETE FROM messages")
    void deleteAll();
    @Update
    void updateUser (UserEntity userEntity);
    @Delete
    void deleteUser (UserEntity userEntity);
}
