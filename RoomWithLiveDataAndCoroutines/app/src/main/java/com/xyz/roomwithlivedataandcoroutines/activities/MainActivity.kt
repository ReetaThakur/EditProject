package com.xyz.roomwithlivedataandcoroutines.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xyz.roomwithlivedataandcoroutines.R
import com.xyz.roomwithlivedataandcoroutines.viewmodel.UserViewModel
import com.xyz.roomwithlivedataandcoroutines.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This activity is used to insert the data into the database using kotlin coroutines
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
        /*
        Here a viewmodelFactory is created since we need a context in the view model class
        to make a database insertion
         */
        userViewModel = UserViewModelFactory(this).create(UserViewModel::class.java)
    }

    private fun initListeners() {
        btnSave.setOnClickListener(this)
        btnFetch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSave -> {

                if (isDataValid()) {
                    // Insert the data
                    userViewModel.insertDataToDatabase(
                        firstName = etName.text.toString(),
                        lastName = etLastName.text.toString()
                    )
                }
            }

            R.id.btnFetch -> {
                val intent = Intent(this@MainActivity, UserListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * Check if the user has entered the data in the edit text fields, if the data is empty the return false else true
     */
    fun isDataValid(): Boolean {
        if (etName.text.toString().isEmpty()) {
            etName.error = "First Name cannot be empty"
            return false
        }

        if (etLastName.text.toString().isEmpty()) {
            etLastName.error = "Last Name cannot be empty"
            return false
        }
        return true
    }
}