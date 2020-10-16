package com.xyz.handler_looper_threads_messagequeue;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnTaskOne;
    private Button mBtnTaskTwo;
    private Button mBtnHandlerThread;
    private Button mBtnStopHandlerThread;
    private LooperThread looperThread;
    private ThreadWithLooper threadWithLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnStart = findViewById(R.id.btnStart);
        mBtnStop = findViewById(R.id.btnStop);
        mBtnTaskOne = findViewById(R.id.btnTaskOne);
        mBtnTaskTwo = findViewById(R.id.btnTaskTwo);
        mBtnHandlerThread = findViewById(R.id.btnStartThreadWithLooper);
        mBtnStopHandlerThread = findViewById(R.id.btnStopThreadWithLooper);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnTaskOne.setOnClickListener(this);
        mBtnTaskTwo.setOnClickListener(this);
        looperThread = new LooperThread();
        threadWithLooper = new ThreadWithLooper("Lloyd");
        mBtnHandlerThread.setOnClickListener(this);
        mBtnStopHandlerThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnStart:
                looperThread.start();
                break;
            case R.id.btnStop:
                looperThread.looper.quit();
                break;
            case R.id.btnStartThreadWithLooper:
                threadWithLooper.start();
                break;
            case R.id.btnStopThreadWithLooper:
                threadWithLooper.getLooper().quit();
                break;
            case R.id.btnTaskOne:
                looperThread.addTaskToQueue(addTaskToQueue());
                break;
            case R.id.btnTaskTwo:
                threadWithLooper.addTaskToQueue(addTaskToQueue());
                break;
        }
    }

    private Runnable addTaskToQueue() {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name " + Thread.currentThread().getName());
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, i + "");
                    SystemClock.sleep(2000);
                }
            }
        };
    }
}