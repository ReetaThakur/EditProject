package com.xyz.boundservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Lloyd", "onBind");
        mediaPlayer = MediaPlayer.create(this, R.raw.shape_of_you);
        return new ServiceBinder();
    }

    public void startMusic() {
        mediaPlayer.start();
    }

    public void stopMusic() {
        mediaPlayer.pause();
    }

    public class ServiceBinder extends Binder {

        public MusicService getMusicService() {
            return MusicService.this;
        }
    }
}