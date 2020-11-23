package com.xyz.cordinatorlayoutdemo

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
        btnFabAndSnackBar.setOnClickListener(this)
        btnCollapseToolBar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFabAndSnackBar -> {
                launchFabAndSnackBarActivity()
            }

            R.id.btnCollapseToolBar ->{
                launchCollapseToolBarActivity()
            }
        }
    }

    private fun launchFabAndSnackBarActivity() {
        val intent = Intent(this, FabAndSnackBarActivity::class.java)
        startActivity(intent)
    }

    private fun launchCollapseToolBarActivity() {
        val intent = Intent(this, CollapseToolBarActivity::class.java)
        startActivity(intent)
    }
}