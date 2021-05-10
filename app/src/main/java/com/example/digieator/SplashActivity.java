package com.example.digieator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        getSupportActionBar().hide();



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(com.example.digieator.SplashActivity.this, com.example.digieator.MainActivity.class);
                com.example.digieator.SplashActivity.this.startActivity(mainIntent);
                com.example.digieator.SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}//end of SplashActivity class
    




