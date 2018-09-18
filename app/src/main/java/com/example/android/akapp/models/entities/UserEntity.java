package com.example.android.akapp.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "users")
public class UserEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int userId;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "nick")
    private String nick;
    @ColumnInfo(name = "register_date")
    private String registerDate;
    @ColumnInfo(name = "is_admin")
    private boolean isAdmin;


    public UserEntity() {
    }

    @Ignore
    public UserEntity(String email, String password, String registerDate, String nick, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.nick = nick;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean IsAdmin(boolean isAdmin) {
        return isAdmin;
    }

    @NonNull
    public int getUserId() {
        return userId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                isAdmin == that.isAdmin &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nick, that.nick) &&
                Objects.equals(registerDate, that.registerDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, email, password, nick, registerDate, isAdmin);
    }
}