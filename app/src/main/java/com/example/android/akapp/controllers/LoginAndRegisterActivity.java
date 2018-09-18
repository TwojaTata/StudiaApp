package com.example.android.akapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.akapp.R;
import com.example.android.akapp.models.entities.UserEntity;
import com.example.android.akapp.models.services.AuthenticationService;
import com.example.android.akapp.views.LoginFragment;
import com.example.android.akapp.views.RegisterFragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login_and_register)
public class LoginAndRegisterActivity extends AppCompatActivity {

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    UserEntity currentUser;
    @ViewById(R.id.signupEditTextEmail)
    EditText singupEditTextEmail;
    @ViewById(R.id.signupEditTextPassword)
    EditText singupEditTextPassword;
    @ViewById(R.id.signupEditTextPasswordAgain)
    EditText singupEditTextPasswprdAgain;
    @ViewById(R.id.signupEditTextNickname)
    EditText singupEditTextNickname;
    @ViewById(R.id.registerButton)
    Button registerButton;
    @ViewById(R.id.editTextEmail)
    EditText editTextEmail;
    @ViewById(R.id.editTextPassword)
    EditText editTextPassword;
    @ViewById(R.id.loginButton)
    Button loginButton;
    @ViewById(R.id.singupButton)
    Button signupButton;
    @ViewById(R.id.forgotPassButton)
    Button forgotPassButton;
    @Bean
    AuthenticationService authenticationService;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);

    }

    @Click(R.id.singupButton)
    @UiThread
    void goToRegisterButtonClick(View view) {

        Fragment fragment;

        if(view == findViewById(R.id.singupButton)){
            fragment = new RegisterFragment();
        }
        else{
            fragment = new LoginFragment();
        }

        fragmentTransaction.replace(R.id.login_fragment, fragment);
        fragmentTransaction.commit();
    }
    @Click(R.id.registerButton)
    void DoRegisterButtonClick(View view){
        if (authenticationService.registerUser(singupEditTextEmail.toString(), singupEditTextPassword.toString(), singupEditTextPasswprdAgain.toString(), singupEditTextNickname.toString())) {
            Toast.makeText(this, "rejestracja zakończona", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "rejestracja nie powiodła się, podane hasła nie są takie same, email albo nick są zajęte, spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();
        }
    }
    @Click(R.id.loginButton)
    void loginButtonClick(View view){

        UserEntity userEntity = authenticationService.GetCurrentUser(editTextEmail.toString(), editTextPassword.toString());
        if ( userEntity != null){
            setCurrentUser(userEntity);
            startNewsfeedActivity();
        }
        else {
            Toast.makeText(this, "Podane hasło lub email nie są prawidłowe", Toast.LENGTH_SHORT).show();
            editTextEmail.setText("");
            editTextPassword.setText("");
        }
    }
    private void startNewsfeedActivity (){

        Intent intent = new Intent(this, NewsFeedActivity.class);
        startActivity(intent);
    }
}
