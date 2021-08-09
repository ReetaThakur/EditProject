package com.masai.fixme_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartTask;
    private CircularProgressIndicator circularProgressIndicator;


    private Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int progress = (int) msg.obj;
            circularProgressIndicator.setProgress(progress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setContentView(R.layout.activity_main);
        Task task = new Task("async", mainThreadHandler);
        mBtnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.start();
            }
        });
    }

    private void initViews() {
        mBtnStartTask = findViewById(R.id.btnStartTask);
        circularProgressIndicator = findViewById(R.id.progress_circular);
    }
}