package com.example.firebasepushnotifications;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceTokenService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String string) {
        super.onNewToken(string);
        Log.d("device token:",string);

    }
}
