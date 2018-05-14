package com.ganonalabs.munir.electrtech;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class MyESS extends Application {
    private static MyESS mInstance;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        context = getApplicationContext();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static synchronized MyESS getmInstance(){
        return mInstance;
    }
}
