package com.xyz.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartMusic;
    private Button mBtnStopMusic;
    private MusicService musicService;
    private boolean isServiceBound;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Lloyd", "onService connected");
            MusicService.ServiceBinder binder = (MusicService.ServiceBinder) service;
            musicService = binder.getMusicService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
            Log.d("Lloyd", "onService disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnStartMusic = findViewById(R.id.btnStartMusic);
        mBtnStopMusic = findViewById(R.id.btnStopMusic);
        mBtnStartMusic.setOnClickListener(this);
        mBtnStopMusic.setOnClickListener(this);
        startMusicService();
    }

    private void startMusicService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartMusic:
                if (isServiceBound) {
                    musicService.startMusic();
                }
                break;
            case R.id.btnStopMusic:
                if (isServiceBound) {
                    musicService.stopMusic();
                }
                break;
        }
    }
}