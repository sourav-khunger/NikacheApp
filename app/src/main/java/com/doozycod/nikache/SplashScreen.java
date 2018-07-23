package com.doozycod.nikache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    int _splashTime = 1000; // time to display the splash screen in ms
    public static boolean isLogin = false;
    public static float currentWalletAmt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        isLogin = SignUpPage.statusLogin;

//        currentWalletAmt = MyWallet.moneyOnSuccess;
//        Toast.makeText(this, "Splash: " + String.valueOf(MyWallet.moneyOnSuccess), Toast.LENGTH_SHORT).show();


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
