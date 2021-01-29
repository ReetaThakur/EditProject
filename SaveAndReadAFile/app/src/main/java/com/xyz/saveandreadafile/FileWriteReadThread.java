package com.xyz.saveandreadafile;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class FileWriteReadThread extends HandlerThread {

    private LooperReadyListener looperReadyListener;
    private Handler handler;

    public FileWriteReadThread(String name, LooperReadyListener listener) {
        super(name);
        looperReadyListener = listener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(Looper.myLooper());
        looperReadyListener.onLooperReady();
    }

    public void addTaskToQueue(Runnable task) {
        handler.post(task);
    }
}
