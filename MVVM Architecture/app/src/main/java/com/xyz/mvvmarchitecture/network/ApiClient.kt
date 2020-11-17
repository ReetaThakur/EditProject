package com.xyz.mvvmarchitecture.network

import com.xyz.mvvmarchitecture.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("/api/users?page=2")
    fun getUsers(): Call<ResponseModel>
}