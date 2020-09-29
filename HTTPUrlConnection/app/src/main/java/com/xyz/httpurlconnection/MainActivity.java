package com.xyz.httpurlconnection;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private Button mBtnGetRequest;
    private String API_URL = " ";
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                StringBuilder stringBuilder = new StringBuilder();
                while (data != -1) {
                    char responseChar = (char) data;
                    stringBuilder.append(responseChar);
                    data = inputStreamReader.read();
                }
                Log.d("Lloyd", stringBuilder.toString());

                buildResponseModel(stringBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTvTitle = findViewById(R.id.tvTitle);
        mBtnGetRequest = findViewById(R.id.btnGetRequest);
        mBtnGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API_URL ="https://jsonplaceholder.typicode.com/posts/4";
                startBackGroundThread();
            }
        });

    }

    private void startBackGroundThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void buildResponseModel(StringBuilder stringBuilder) {
        try {
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            int userId = jsonObject.getInt("userId");
            int id = jsonObject.getInt("id");
            final Response response = new Response(userId, id, title, body);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvTitle.setText(response.getTitle());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}