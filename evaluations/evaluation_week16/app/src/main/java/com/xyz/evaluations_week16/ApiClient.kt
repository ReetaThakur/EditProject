package com.xyz.evaluations_week16

import retrofit2.Call
import retrofit2.http.GET

/**
 * This is an interface where all the Api calls are written
 */
interface ApiClient {

    @GET("/api/unknown")
    fun getResources() : Call<ResponseModel>
}