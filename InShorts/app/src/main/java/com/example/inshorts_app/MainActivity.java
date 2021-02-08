package com.example.inshorts_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.inshorts_app.Adapter.NewsAdapter;
import com.example.inshorts_app.Network.ApiClient;
import com.example.inshorts_app.Network.NetworkClass;
import com.example.inshorts_app.Response.DataItem;
import com.example.inshorts_app.Response.ResponseMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;
    private NewsAdapter newsAdapter;
    private List<DataItem> dataItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerContainer);
        editText = findViewById(R.id.searchEditText);
        button = findViewById(R.id.searchButton);
        setAdapter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString()!=null){
                    callApi(editText.getText().toString());
                }
            }
        });


    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(dataItemsList);
        recyclerView.setAdapter(newsAdapter);

    }

    private void callApi(String str) {
        ApiClient apiClient = NetworkClass.getRetrofitInstance(MainActivity.this).create(ApiClient.class);
        apiClient.getListOfNews(str).enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                if (response.body()!=null)
                newsAdapter.updateData(response.body().getData());

            }

            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {

            }
        });

    }
}