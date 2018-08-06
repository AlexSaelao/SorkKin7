package com.asus.sorkkin.sorkkin.splashscreen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.activity.MainActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#EF6C00"))
                .withLogo(R.drawable.logosplashscreen)
        ;

        View view = config.create();
        setContentView(view);

    }
}
