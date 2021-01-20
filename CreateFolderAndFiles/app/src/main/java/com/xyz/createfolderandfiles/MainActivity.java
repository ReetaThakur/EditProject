package com.xyz.createfolderandfiles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSaveLogs;
    private Button mBtnGetLogs;

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
                createExceptionAndSaveLogs();
            }
        });
    }

    private void createExceptionAndSaveLogs() {
        int[] a = new int[5];

        try {
            a[5] = 4;
        } catch (Exception e) {
            saveLogsToFile(e.getLocalizedMessage());
        }

    }

    private void saveLogsToFile(String message) {
        File directory = new File(this.getFilesDir() + File.separator + "ExceptionFolder");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File newFile = new File(directory, "logs.txt");

        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fOut = new FileOutputStream(newFile,true);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fOut);
            outputWriter.append(message + "\n");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}