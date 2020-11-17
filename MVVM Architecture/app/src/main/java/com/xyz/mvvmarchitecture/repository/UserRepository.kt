package com.xyz.mvvmarchitecture.repository

import com.xyz.mvvmarchitecture.model.ResponseModel
import com.xyz.mvvmarchitecture.network.ApiClient
import com.xyz.mvvmarchitecture.network.Network
import retrofit2.Callback

/**
 * This is a `M` layer in the `MVVM` architecture which gives us the data from the API
 */
class UserRepository(private val callback: Callback<ResponseModel>) {

    fun getListOfModel() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getUsers()
        /*
        Once the response is fetched, navigate the user back to view model as this callback is being implemented
        in the Viewmodel class
         */
        call.enqueue(callback)
    }
}