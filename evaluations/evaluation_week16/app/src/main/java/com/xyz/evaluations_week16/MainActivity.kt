package com.xyz.evaluations_week16

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var dataModelList = listOf<DataModel>()

    private lateinit var resourceAdapter: ResourceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerAdapter()
        callAPI()
    }

    private fun setRecyclerAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        resourceAdapter = ResourceAdapter(dataModelList)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = resourceAdapter
        }
    }

    private fun callAPI() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getResources()
        call.enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                response.body()?.let { it ->
                    //Since the response is nullable here use null safety operator so it is no null
                    it.data?.let {
                        /*
                        since the it.data is List<DataModel?>? use a null safety operator and make it
                         non nullable i.e List<DataModel?>
                         */
                        dataModelList = it as List<DataModel> // casting is required here as dataModelList is of type List<DataModel> where as we are receiving the type List<DataModel?>
                        resourceAdapter.updateData(dataModelList)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                //Handle the response failure here
            }

        })

    }
}