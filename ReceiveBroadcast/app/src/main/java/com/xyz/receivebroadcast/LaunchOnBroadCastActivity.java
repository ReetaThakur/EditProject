package com.xyz.receivebroadcast;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchOnBroadCastActivity extends AppCompatActivity {

    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_on_broad_cast);
        initViews();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            mTvData.setText(getIntent().getStringExtra("key"));
        }
    }

    private void initViews() {
        mTvData = findViewById(R.id.tvData);
    }
}