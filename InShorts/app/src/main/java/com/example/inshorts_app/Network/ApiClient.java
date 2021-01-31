package com.example.inshorts_app.Network;

import com.example.inshorts_app.Response.ResponseMenu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("/news")
    Call<ResponseMenu> getListOfNews(@Query("category") String category);
}
