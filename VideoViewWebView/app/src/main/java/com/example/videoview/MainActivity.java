package com.example.videoview;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        setPlayer();
        openWeb();
    }

    private void setPlayer() {
        Uri uri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.show();
        
        videoView.start();

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(MainActivity.this, "Video Player Error", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void openWeb() {
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });
    }
}