package com.xyz.apiwe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mBtnCallApi;
    private RecyclerView recyclerView;
    private List<ResponseClass> responseClassList = new ArrayList<>();
    private PostAdapter postAdapter;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            URL url = null;

            try {
                url = new URL("https://jsonplaceholder.typicode.com/posts");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                StringBuffer stringBuffer = new StringBuffer();

                while (data != -1) {
                    char ch = (char) data;
                    stringBuffer.append(ch);
                    data = inputStreamReader.read();
                }
                buildResponseData(stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void buildResponseData(StringBuffer stringBuffer) {
        String json = stringBuffer.toString();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachJsonObject = jsonArray.getJSONObject(i);
                int id = eachJsonObject.getInt("id");
                int userId = eachJsonObject.getInt("userId");
                String title = eachJsonObject.getString("title");
                String body = eachJsonObject.getString("body");
                ResponseClass responseClass = new ResponseClass(id, userId, title, body);
                responseClassList.add(responseClass);
            }
            updateAdapter();



            Log.d("Lloyd", "Response built");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                postAdapter.updateRecyclerViewList(responseClassList);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        setRecyclerAdapter();
    }

    private void initViewsAndListeners() {
        mBtnCallApi = findViewById(R.id.btnCallApi);
        recyclerView = findViewById(R.id.recyclerView);
        mBtnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundThread();
            }
        });
    }

    private void setRecyclerAdapter() {
        postAdapter = new PostAdapter(responseClassList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
    }

    private void startBackgroundThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}