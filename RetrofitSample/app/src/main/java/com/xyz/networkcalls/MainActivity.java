package com.xyz.networkcalls;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private GeneralAdapter generalAdapter;
    private List<PhotosResponse> responseList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        makeApiCall();
        setRecyclerAdapter();
    }

    private void makeApiCall() {
        /*
        Initialize the API service calls which has all the Api calls
         */
        ApiService apiService = Network.getRetrofitInstance().create(ApiService.class);

        /*
        Make an API call and save the response in call object
         */
        Call<List<PhotosResponse>> call = apiService.getPhotos();

        /*
         * This enqueue method will make an asynchronous call and fetches the response from the server
         */
        call.enqueue(new Callback<List<PhotosResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<PhotosResponse>> call, @NonNull retrofit2.Response<List<PhotosResponse>> response) {
                /*
                This method is invoked if the response is successful
                 */
                if (response.code() == 200) {
                    responseList = response.body();
                    /*
                    Since this Api call is made in the background thread, there is a possibility that the response comes late,
                    so once the response comes we need to update the list and notify the adapter about the changes
                     */
                    generalAdapter.updateData(responseList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PhotosResponse>> call, @NonNull Throwable t) {
                /*
                If the Api call is failed then an error is thrown and it can be obtained from the throwable object
                 */
                Toast.makeText(MainActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setRecyclerAdapter() {
        generalAdapter = new GeneralAdapter(responseList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalAdapter);
    }
}