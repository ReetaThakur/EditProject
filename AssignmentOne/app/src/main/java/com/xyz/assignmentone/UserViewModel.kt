package com.xyz.assignmentone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var mutableLiveData = MutableLiveData<UserUI>()

    fun callAPI() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)

        val call = apiClient.getUser()

        call.enqueue(object : Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                response.body()?.let {
                    mutableLiveData.value = UserUI.Success(it)
                }
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                mutableLiveData.value = UserUI.Failure(t)
            }

        })
    }
}