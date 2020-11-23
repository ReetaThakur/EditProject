package com.xyz.cordinatorlayoutdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class FabAndSnackBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        btnShowSnackBar.setOnClickListener {
            val snackbar =
                Snackbar.make(coordinatorLayout, "This is a simple snackbar", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Close") {
                snackbar.dismiss()
            }
            snackbar.show()

        }
    }
}