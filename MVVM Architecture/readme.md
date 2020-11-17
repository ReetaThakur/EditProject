MVVM Architecture 
------------------

This project demonstartes about the usage of MVVM Architecture. Its a simple project which is used to make an API call and populate the data in a recycler view.

There are 3 layers in this project

1. The `View` which is our `MainActivity`

```
package com.xyz.mvvmarchitecture.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xyz.mvvmarchitecture.R
import com.xyz.mvvmarchitecture.adapter.UserAdapter
import com.xyz.mvvmarchitecture.model.DataModel
import com.xyz.mvvmarchitecture.model.UserUIModel
import com.xyz.mvvmarchitecture.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This Activity makes an Api call and populates the result in a recycler view. This activity has
 * only UI related code
 */
class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private val dataModelList = emptyList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setRecyclerAdapter()
        observeLiveData()
        flProgressBar.visibility = View.VISIBLE
        userViewModel.callAPI()
    }

    /**
     * This method is used to observe the live data changes. i.e it gets called when the Api response is fetched
     */
    private fun observeLiveData() {
        userViewModel.liveData.observe(this, {
            when (it) {
                is UserUIModel.Success -> {
                    userAdapter.updateList(it.dataModelList)
                    flProgressBar.visibility = View.GONE
                }

                is UserUIModel.Failure -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Error message ${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                    flProgressBar.visibility = View.GONE
                }
            }
        })
    }

    /**
     * Sets the recycler view adapter
     */
    private fun setRecyclerAdapter() {
        userAdapter = UserAdapter(dataModelList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = userAdapter
        }
    }
}
```

2. The `VM` layer which is our `UserViewModel`

```
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
```

3. The `M` layer which is our `UserRepository`

```
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
```

Here we are writing only the UI related code in the `Activity`, The `Viewmodel` is just used to pass the data and the `Repository` layer gives us the data.

[![Watch this](https://drive.google.com/uc?export=view&id=1bCLAcc8akgi42-cigdtFta_7MbMoFbfN)](https://drive.google.com/uc?export=view&id=1bCLAcc8akgi42-cigdtFta_7MbMoFbfN)
