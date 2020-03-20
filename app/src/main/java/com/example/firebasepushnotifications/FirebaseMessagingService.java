package com.example.firebasepushnotifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    //private static  int Notification_ID = 1;
    //public static final String CHANNEL_ID = "channel1";
    public static final String TAG = "MyService";
    public static final String ARG_TITLE = "title";
    public static final String ARG_BODY = "body";
    String body = "";
    String title = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        body = remoteMessage.getNotification().getBody();
        title = remoteMessage.getNotification().getBody();

        generateNotification(remoteMessage.getNotification().getBody(),
                remoteMessage.getNotification().getTitle());

    }

    private void generateNotification(String body, String title) {
//to open mainactivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.
                FLAG_ONE_SHOT);
        intent.putExtra(ARG_TITLE, title);
        intent.putExtra(ARG_BODY, body);

        //to create sound
        String channelId = getString(R.string.key1);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


//to create notifications
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(Notification_ID++,notificationBuilder.build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel created",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onNewToken(String token) {

        Log.d(TAG, "Refreshed token: " + token);


    }
}
