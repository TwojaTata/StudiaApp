package com.example.android.akapp.models.services;

import com.example.android.akapp.models.database.DataBase;
import com.example.android.akapp.models.entities.UserEntity;
import com.example.android.akapp.models.worker.Worker;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean
public class AuthenticationService {
    @Bean
    Worker worker;

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    private UserEntity currentUser;


    private static boolean isRegistered() {
        return isRegistered;
    }

    public static void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    private static boolean isRegistered = false;

    public boolean registerUser(String email, String password, String passwordAgain, String nick) {

        if (tryToRegister(email, password, passwordAgain, nick)) {
            worker.SaveUserToDataBase(DataBase.getDataBaseInstance(),email, password, nick);

            return true;
        }
        return false;
    }

    private boolean checkIfPasswordsMatches(String password, String passwordAgain) {

        return password.equals(passwordAgain);
    }

    private boolean tryToRegister(String email, String password, String passwordAgain, String nick) {


        if (!checkIfPasswordsMatches(password, passwordAgain)) {
//            Toast.makeText(this, "podane hasłą nie są takie same", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkIfUserIsAlreadySignedUp(email, nick)) {
//            Toast.makeText(this, "podany email lub nick jest już zajęty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkIfUserIsAlreadySignedUp(final String email, final String nick) {
        worker.CheckIfUserIsRegistered(DataBase.getDataBaseInstance(),email, nick);
        return isRegistered();
    }
    private boolean tryToLogin(String email, String password){

        UserEntity userEntity = worker.getCurrentUser();

        return userEntity.getEmail().equals(email) && userEntity.getPassword().equals(password);

    }
    public UserEntity GetCurrentUser (String email, String password){
        if(tryToLogin(email, password)){
            return worker.getCurrentUser();
        }
        return null;
    }

}
