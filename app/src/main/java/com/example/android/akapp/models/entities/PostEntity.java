package com.example.android.akapp.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = @Index("userId"),tableName = "messages", foreignKeys = @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE))
public class PostEntity {


    @ColumnInfo(name = "userId")
    private int userId;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    // TODO foregin key userEntity
    @ColumnInfo(name = "message_context")
    private String context;
    @ColumnInfo (name = "creation_date")
    private String creationDate;
    private String title;

    public PostEntity(){}
    @Ignore
    public PostEntity(String context, String creationDate, int userId, String title) {
        this.context = context;
        this.creationDate = creationDate;
        this.userId = userId;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
