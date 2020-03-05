package com.alenmutum21.ac_twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {
    private TextView textView;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView = findViewById(R.id.textView);
        signOut = findViewById(R.id.signout);


        String username = ParseUser.getCurrentUser().getUsername();

        textView.setText(username);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                startActivity(new Intent(WelcomeActivity.this,SignUpLoginActivity.class));
                FancyToast.makeText(WelcomeActivity.this,"Logout",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();
            }
        });
    }
}
