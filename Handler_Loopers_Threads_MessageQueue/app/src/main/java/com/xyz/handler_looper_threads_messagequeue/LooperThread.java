package com.xyz.handler_looper_threads_messagequeue;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class LooperThread extends Thread {

    private static final String TAG = LooperThread.class.getSimpleName();
    public Handler mHandler;
    public Looper looper;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        looper = Looper.myLooper();
        mHandler = new Handler(looper);

        Looper.loop();
        Log.d(TAG, "Outside Loop");
    }

    public void addTaskToQueue(Runnable task){
        mHandler.post(task);
    }
}
