package com.xyz.basicservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lloyd Dcosta
 * This Activity is used to start a service
 */
public class MainActivity extends AppCompatActivity {

    private DownloadBroadCastReceiver broadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadCastReceiver = new DownloadBroadCastReceiver();
        registerReceiver(broadCastReceiver, new IntentFilter("com.xyz.download"
        ));
        Intent intent = new Intent(this, DownloadFileService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastReceiver);
    }
}