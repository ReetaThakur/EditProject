package com.xyz.createfolderandfiles;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button mBtnSaveLogs;
    private Button mBtnGetLogs;

    /**
     * Runnable for save the data to the file
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int[] a = new int[5];
            try {
                a[5] = 10;

            } catch (Exception e) {
                saveLogsToFile(e.getLocalizedMessage());
            }

        }
    };

    /**
     * Runnable for reading the data from file
     */
    private Runnable readRunnable = new Runnable() {
        @Override
        public void run() {
            readFile();
        }
    };

    /**
     * This method is used to save the file to the internal shared storage
     *
     * @param message exception message that needs to be saved to the file
     */
    private void saveLogsToFile(String message) {
        //Create a folder
        try {
            /*
            getFilesDir() creates a file in the location data/data/<app-package> -> internal shared storage (user cannot access)
            getExternalFilesDir(null) creates a file in sdcard/Android/<app-package> -> external storage (user can access)
            Environment.getExternalStorageDirectory() creates a file in external SD card
             */
            File directory = new File(getFilesDir() + File.separator + "logsFolder");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Create file inside the directory
            File file = new File(directory, "logs.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.append(message + "\n");
            writer.close();
            updateUI();
        } catch (Exception e) {
            Log.d("Lloyd", e.getLocalizedMessage());
        }
    }

    /**
     * Once the data is saved in the file then display a Toast in the UI thread.
     */
    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "File successfully created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnSaveLogs = findViewById(R.id.btnSaveLogs);
        mBtnGetLogs = findViewById(R.id.btnGetLogs);
        mBtnSaveLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundThread();
            }
        });

        mBtnGetLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(readRunnable);
                thread.start();
            }
        });
    }

    private void startBackgroundThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * This method is used to read the file in the background thread
     */
    private void readFile() {
        try {
            File directory = new File(getFilesDir() + File.separator + "logsFolder");

            // Create file inside the directory
            File file = new File(directory, "logs.txt");
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            StringBuilder stringBuilder = new StringBuilder();
            while (data != -1) {
                char ch = (char) data;
                stringBuilder.append(ch);
                data = inputStreamReader.read();
            }
            UpdateReadData(stringBuilder);
        } catch (Exception e) {

        }
    }

    /**
     * Once the data is read from the file display a Toast in the UI thread
     *
     * @param builder data read from the file
     */
    private void UpdateReadData(StringBuilder builder) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Data read successfully", Toast.LENGTH_SHORT).show();
                Log.d("Lloyd", builder.toString());
            }
        });
    }
}