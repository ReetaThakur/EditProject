package com.xyz.networkcalls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    /*
    This method gives the retrofit instance
     */
    public static retrofit2.Retrofit getRetrofitInstance() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                //In most cases requests to a server, and the responses from the server, are not Java objects. They’re mapped to some language neutral format like JSON
                .addConverterFactory(GsonConverterFactory.create())
                //this client will help you to print the API call logs in the Logcat
                .client(new OkHttpClient.Builder().build());
        return builder.build();
    }
}
