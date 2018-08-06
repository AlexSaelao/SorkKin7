package com.asus.sorkkin.sorkkin;

import android.app.Application;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        com.asus.sorkkin.sorkkin.manager.Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
