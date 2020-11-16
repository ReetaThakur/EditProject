package com.xyz.assignmentone

import retrofit2.Call
import retrofit2.http.GET


interface ApiClient {


    @GET("/api/users/2")
    fun getUser(): Call<ResponseDTO>
}