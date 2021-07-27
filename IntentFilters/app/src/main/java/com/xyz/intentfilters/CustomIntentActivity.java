package com.xyz.intentfilters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class CustomIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_intent);

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(myIntent);
    }
}