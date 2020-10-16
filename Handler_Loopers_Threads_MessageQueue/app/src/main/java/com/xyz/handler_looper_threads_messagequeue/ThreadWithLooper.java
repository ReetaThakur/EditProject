package com.xyz.handler_looper_threads_messagequeue;

import android.os.Handler;
import android.os.HandlerThread;

public class ThreadWithLooper extends HandlerThread {
    public Handler mHandler;

    public ThreadWithLooper(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
    }

    public void addTaskToQueue(Runnable task) {
        mHandler.post(task);
    }
}
