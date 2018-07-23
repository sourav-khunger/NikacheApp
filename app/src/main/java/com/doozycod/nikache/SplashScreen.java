package com.doozycod.nikache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    int _splashTime = 1000; // time to display the splash screen in ms
    public static boolean isLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        isLogin = SignUpPage.statusLogin;


        Thread splashTread = new Thread() {

            public void run() {
                try {
                    sleep(_splashTime);
                } catch (Exception e) {

                } finally {

//                    startActivity(new Intent(SplashScreen.this,
//                            LoginActivity.class));

                    startActivity(new Intent(SplashScreen.this,
                            MainActivity.class));

                    finish();
                }
            }

        };
        splashTread.start();

    }
}
