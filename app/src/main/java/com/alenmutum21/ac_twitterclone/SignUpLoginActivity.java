package com.alenmutum21.ac_twitterclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {
    private Button signUp,logIn;
    private EditText usernameSignup,pwdSignup,usernameLogin,pwdLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        signUp = findViewById(R.id.signUp);
        logIn = findViewById(R.id.login);

        usernameLogin = findViewById(R.id.usernameLogin);
        usernameSignup = findViewById(R.id.usernameSignup);
        pwdLogin = findViewById(R.id.pwdLogin);
        pwdSignup = findViewById(R.id.pwdSignup);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();

                appUser.setUsername(usernameSignup.getText().toString());
                appUser.setPassword(pwdSignup.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" succesfully signed up",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                        }else {
                            String error = e.getMessage();
                            FancyToast.makeText(SignUpLoginActivity.this,"error: "+ error,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }
                    }
                });

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(usernameLogin.getText().toString(), pwdLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,"succesfully Logged in",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                        }else {
                            FancyToast.makeText(SignUpLoginActivity.this,"error: "+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }
                    }
                });

            }
        });

    }
}
