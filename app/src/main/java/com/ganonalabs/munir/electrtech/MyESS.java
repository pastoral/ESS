package com.ganonalabs.munir.electrtech;

import android.app.Application;
import android.content.Context;

import com.ganonalabs.munir.electrtech.onesignal.MyNotificationOpenedHandler;
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
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static synchronized MyESS getmInstance(){
        return mInstance;
    }
}
