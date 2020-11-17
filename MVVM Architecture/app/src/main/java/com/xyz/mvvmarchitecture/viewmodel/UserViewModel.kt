package com.xyz.mvvmarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xyz.mvvmarchitecture.model.DataModel
import com.xyz.mvvmarchitecture.model.ResponseModel
import com.xyz.mvvmarchitecture.model.UserUIModel
import com.xyz.mvvmarchitecture.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This is a VM layer in the `MVVM` architecture, where we are notifying the Activity/view about the
 * response changes via live data
 */
class UserViewModel : ViewModel(), Callback<ResponseModel> {

    private val repository = UserRepository(this)

    private val mutableLiveData = MutableLiveData<UserUIModel>()

    val liveData: LiveData<UserUIModel> = mutableLiveData

    /**
     * This method is called once the response is received from the API
     */
    override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
        response.body()?.let {
            mutableLiveData.value = UserUIModel.Success(it.data as List<DataModel>)
        }
    }

    /**
     * If the API response fails due to some reason this method gets invoked
     */
    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
        mutableLiveData.value = UserUIModel.Failure(t.message!!)
    }

    /**
     * This method makes an API call to the Repository class where actual API call is made
     */
    fun callAPI() {
        repository.getListOfModel()
    }

}