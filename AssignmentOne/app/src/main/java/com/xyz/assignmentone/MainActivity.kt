package com.xyz.assignmentone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnCallApi.setOnClickListener {
            userViewModel.callAPI()
        }


        userViewModel.mutableLiveData.observe(this, {
            it?.let {
                when (it) {
                    is UserUI.Success -> {
                        tvEmail.text = it.response.data?.email
                    }

                    is UserUI.Failure -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Failure message ${it.throwable.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

}