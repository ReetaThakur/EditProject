package com.xyz.backgroundtask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtnClick;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("LLoyd", "Thread name 2 " + Thread.currentThread().getName());
            for (int i = 0; i < 1000000000; i++) {
                for (int j = 0; j < 2; j++) {

                }
            }
            Log.d("Lloyd","For loop executed");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {

        mBtnClick = findViewById(R.id.btnClick);

        Log.d("LLoyd", "Thread name 1" + Thread.currentThread().getName());
        Thread thread = new Thread(runnable);
        thread.start();


        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button clicked ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}