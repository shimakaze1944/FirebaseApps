package com.example.firebaseapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private int loading_time = 1500;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mUser != null){
                    startActivity(new Intent(SplashScreen.this, StudentMainActivity.class));
                }else{
                    startActivity(new Intent(SplashScreen.this, Starter.class));
                }
                finish();
            }
        }, loading_time);
    }
}