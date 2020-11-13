package com.xyz.evaluations_week16

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    /**
     * Companion object is like static object in java
     */
    companion object {

        private val httpLoggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        /**
         * This method will give the instance of retrofit and it can be directly accessed
         * from MainActivity like Network.getInstance()
         */
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                .build()
        }
    }
}