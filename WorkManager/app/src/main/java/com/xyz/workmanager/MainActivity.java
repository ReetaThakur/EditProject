package com.xyz.workmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button btnOneTimeRequest;
    private Button periodicWorkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        periodicWorkBtn = findViewById(R.id.btnPeriodicWork);
        btnOneTimeRequest = findViewById(R.id.btnOneTime);
        btnOneTimeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED).build();
                Data data = new Data.Builder().putString("key", "lloyd").build();
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setConstraints(constraints).
                                setInputData(data)
                        .build();
                WorkManager.getInstance(MainActivity.this).enqueue(oneTimeWorkRequest);
            }
        });

        periodicWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraintsPer = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED).build();
                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.
                        Builder(MyPeriodicWorker.class, 15, TimeUnit.MINUTES)
                        .setInitialDelay(10,TimeUnit.MINUTES)
                        .setConstraints(constraintsPer)
                        .build();
                WorkManager.getInstance().enqueue(periodicWorkRequest);
            }
        });
    }
}