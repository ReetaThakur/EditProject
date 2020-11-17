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