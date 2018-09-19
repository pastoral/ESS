package com.ganonalabs.munir.electrtech.onesignal;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.ganonalabs.munir.electrtech.MyESS;
import com.ganonalabs.munir.electrtech.ui.NewsActivity;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;




/**
 * Created by munirul.hoque on 1/12/2017.
 */

public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    String bigPicture, bigIcon;
    String customKey;
    String action_url;
    String apkname = "";

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        bigPicture = result.notification.payload.bigPicture;
        bigIcon = result.notification.payload.largeIcon;
        JSONObject data = result.notification.payload.additionalData;
        String link = result.notification.payload.launchURL;
        String activityToBeOpened;
        String packageName;

        //While sending a Push notification from OneSignal dashboard
        // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
        //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
        //on the notification, AnotherActivity will be opened.
        //Else, if we have not set any additional data MainActivity is opened.


//            activityToBeOpened = data.optString("activityToBeOpened", null);
//            action_url = data.optString("action_url", null);
//            apkname = data.optString("apkname", null);
            // String title = data.optString("t", null);
            // String body = data.optString("b", null);
            String str1 = result.notification.payload.title;
            String str2 = result.notification.payload.body;
            if (link == null || link.length() < 4) {
                Log.i("OneSignalExample", "customkey set with value: " +"" ); //activityToBeOpened
                Intent intent = new Intent(MyESS.getContext(), NewsActivity.class);
                intent.putExtra("SYSTRAY", "systray");
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);

                // intent.putExtra("title", title);
                //intent.putExtra("body", body);
                intent.putExtra("title", str1);
                intent.putExtra("description", str2);
                if (bigPicture != null) {
                    intent.putExtra("imageurl", bigPicture);
                } else {
                    intent.putExtra("imageurl", bigIcon);
                }
                MyESS.getContext().startActivity(intent);
            } else {
                // Intent intent = new Intent(MyESS.getContext(), NewsWebActivity.class);
                Intent intent = new Intent(Intent.ACTION_VIEW);


                Log.d("URLPush", link);
                packageName = data.optString("packageName", null);

                if (link.contains("https://play.google.com/store/")) {
                    Log.d("URLPushDetect", "Detected");
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + packageName));
                    MyESS.getContext().startActivity(intent);
                } else {
                    Log.d("URLPushDetect", "Normal");
                    // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(link));
                    intent.putExtra("targetUrl", link);
                    intent.putExtra("SYSTRAY", "systray");


                    MyESS.getContext().startActivity(intent);
                }

            }


        }

}