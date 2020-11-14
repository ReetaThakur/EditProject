package com.xyz.roomwithlivedataandcoroutines

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()

    }

    private fun initListeners() {
        btnSave.setOnClickListener(this)
        btnFetch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSave -> {

                if (isDataValid()) {
                    insertDataToDatabase()
                }
            }

            R.id.btnFetch -> {
                val intent = Intent(this@MainActivity, UserListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun insertDataToDatabase() {
        val insertJob = CoroutineScope(IO).launch {
            val user =
                User(firstName = etName.text.toString(), lastName = etLastName.text.toString())
            UserDatabase.getInstance(this@MainActivity).userDao.insertUser(user)
        }

        insertJob.invokeOnCompletion {
            println(" debug: Inserted data into Room ${Thread.currentThread().name}")
        }
    }


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