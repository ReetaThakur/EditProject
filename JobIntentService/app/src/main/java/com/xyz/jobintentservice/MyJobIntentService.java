package com.xyz.jobintentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyJobIntentService extends Service {
    public MyJobIntentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}