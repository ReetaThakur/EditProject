package com.airtelx.app.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface DownloadAPI {

    @GET("files/Node-Android-Chat.zip")
    @Streaming
    suspend fun downloadFile(): ResponseBody

}