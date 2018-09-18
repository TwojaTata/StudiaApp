package com.example.android.akapp.models.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android.akapp.models.entities.PostEntity;
import com.example.android.akapp.models.entities.UserEntity;
import com.example.android.akapp.models.worker.Worker;

@Database(entities = {UserEntity.class, PostEntity.class}, version = 1, exportSchema = false)

public abstract class DataBase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    private static DataBase INSTANCE;


    public static void initDataBase(final Context context) {
        if (INSTANCE == null) {

            synchronized (DataBase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
    }
    public static DataBase getDataBaseInstance(){
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new Worker().PupulateDbAsync(INSTANCE);
                }
            };
}
