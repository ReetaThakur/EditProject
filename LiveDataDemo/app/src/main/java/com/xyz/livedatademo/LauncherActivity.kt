package com.xyz.livedatademo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        initListeners()
    }

    private fun initListeners() {
        btnMutableLiveDataDemo.setOnClickListener(this)
        btnLiveDataTransformDemo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMutableLiveDataDemo -> {
                val intent = Intent(this@LauncherActivity, MutableLiveDataActivity::class.java)
                startActivity(intent)
            }

            R.id.btnLiveDataTransformDemo -> {
                val intent = Intent(this@LauncherActivity,MutableTransformLiveDataActivity::class.java)
                startActivity(intent)
            }
        }
    }
}