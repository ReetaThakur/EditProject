package com.xyz.basicservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author This service runs indefinetly in background and downloads a file and writes it to internal storage
 */
public class DownloadFileService extends Service implements HandlerReadyListener {
    private BackgroundTask backgroundTask;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lloyd", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundTask = new BackgroundTask("Service Thread", this);
        backgroundTask.start();
        return START_REDELIVER_INTENT;
    }

    /**
     * This method is used to download a file in Background.
     *
     * @return runnable
     */
    private Runnable createTask() {
        return new
                Runnable() {
                    @Override
                    public void run() {
                        Log.d("Lloyd", Thread.currentThread().getName());
                        /*
                        getFilesDir will create a file in the internal storage
                         */
                        File directory = new File(getFilesDir() + File.separator + "My Folder1");
                        /*
                        Check if the directory exists else create one
                         */
                        if (!directory.exists()) {
                            directory.mkdir();
                        }

                        /*
                        Create new file in the created directory and give a name to the file
                         */
                        File newFile = new File(directory, "lloyd.html");

                        if (!newFile.exists()) {
                            try {
                                newFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        InputStream stream = null;
                        FileOutputStream fos = null;
                        try {

                            URL url = new URL("https://www.vogella.com/index.html");
                            stream = url.openConnection().getInputStream();
                            InputStreamReader reader = new InputStreamReader(stream);
                            fos = new FileOutputStream(newFile);
                            int next = -1;
                            while ((next = reader.read()) != -1) {
                                fos.write(next);
                            }
                            // successfully finished and send a broadcast to the activity and show a toast

                            Intent intent = new Intent("com.xyz.download");
                            sendBroadcast(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (stream != null) {
                                try {
                                    stream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            /*
                             * Once the file is downloaded stop the service
                             */
                            stopSelf();
                        }
                    }
                };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lloyd", "onDestroy");

    }

    /**
     * This method is called as soon as the handlers looper is ready. Once the looper is ready
     * add the task to the message queue and run in the background
     */
    @Override
    public void onHandlerPrepared() {
        backgroundTask.addTaskToMessageQueue(createTask());
    }
}
