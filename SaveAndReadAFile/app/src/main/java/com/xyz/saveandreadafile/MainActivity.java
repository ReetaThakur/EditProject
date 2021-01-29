package com.xyz.saveandreadafile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtnFetchAndRead;
    private TextView mTvData;
    private EditText mEtData;
    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        networkReceiver = new NetworkReceiver();
        IntentFilter intentFilter = new IntentFilter("com.lloyd.fileFetch");
        registerReceiver(networkReceiver, intentFilter);
    }

    private void initViewsAndListeners() {
        mBtnFetchAndRead = findViewById(R.id.btnFetchAndSave);
        mTvData = findViewById(R.id.tvData);
        mEtData = findViewById(R.id.etEnterData);
        mBtnFetchAndRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FetchAndSaveService.class);
                intent.putExtra("data", mEtData.getText().toString());
                startService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String data = intent.getStringExtra("data");
                mTvData.setText(data);
            }
        }
    }
}