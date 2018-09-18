package com.example.android.akapp.models.worker;

import com.example.android.akapp.models.database.DaoAccess;
import com.example.android.akapp.models.database.DataBase;
import com.example.android.akapp.models.entities.PostEntity;
import com.example.android.akapp.models.entities.UserEntity;
import com.example.android.akapp.models.services.AuthenticationService;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.util.Calendar;
import java.util.Date;
@EBean
public class Worker {

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    private UserEntity currentUser;
    private Date currentDate = Calendar.getInstance().getTime();


    @Background
    public void PupulateDbAsync(DataBase dataBase) {

        Date currentDate = Calendar.getInstance().getTime();

        final DaoAccess daoAccess;
        daoAccess = dataBase.daoAccess();

        daoAccess.deleteAll();
        daoAccess.insertUserToDataBase(new UserEntity("example@gmail.com", "password", "data", currentDate.toString(), false));
        daoAccess.insertMessageToDataBase(new PostEntity("treść", currentDate.toString(), 1, "tytuł"));
        daoAccess.insertMessageToDataBase(new PostEntity("treść 2", currentDate.toString(), 1, "tytuł2"));
        daoAccess.insertMessageToDataBase(new PostEntity("treść 3", currentDate.toString(), 1, "tytuł3"));
        daoAccess.insertMessageToDataBase(new PostEntity("treść 4", currentDate.toString(), 1, "tytuł4"));
        daoAccess.insertMessageToDataBase(new PostEntity("treść 5", currentDate.toString(), 1, "tytuł5"));
        daoAccess.insertMessageToDataBase(new PostEntity("treść 5", currentDate.toString(), 1, "tytuł5"));
    }

    @Background
    public void CheckIfUserIsRegistered(DataBase dataBase, String email, String password) {

        final DaoAccess daoAccess;
        daoAccess = dataBase.daoAccess();

        UserEntity userEntity = daoAccess.getUserByEmail(email);
        UserEntity userEntity2 = daoAccess.getUserByNick(password);
        if (userEntity != null || userEntity2 != null) {//TODO czy ten warunek jest dobry???

            AuthenticationService.setRegistered(true);
        }

    }

    @Background
    public void SaveUserToDataBase(DataBase dataBase, final String email, final String password, final String nick) {

        UserEntity userEntity = new UserEntity(email, password, currentDate.toString(), nick, false);
        dataBase.daoAccess().insertUserToDataBase(userEntity);


    }
    @Background
    public void getUserByEmail(DataBase dataBase, final String email){

        final DaoAccess daoAccess;
        daoAccess = dataBase.daoAccess();
        UserEntity userEntity = daoAccess.getUserByEmail(email);
        setCurrentUser(userEntity);


    }
}




