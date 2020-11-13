package com.xyz.livedatademo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This activity demonstrates the use of mutable live data, how to set the value to the live data,
 * how to observe the data
 */
class MutableLiveDataActivity : AppCompatActivity(), View.OnClickListener {

    val liveData = MutableLiveData<String>()

    /**
     * This is the observer listening to the live data changes i.e called whenever we do liveData.value = ""
     */
    private val observer = Observer<String> {
        it?.let {
            tvLiveData.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveData.observe(this, observer)
        initListeners()
    }

    private fun initListeners() {
        btnControlFragment.setOnClickListener(this)
        btnGenerate.setOnClickListener(this)
    }

    /**
     * Launch the fragment if its not already launched, if its already present in the backstack
     * then remove it
     */
    private fun launchOrPopFragment() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MutableLiveDataFragment(), "")
                    .addToBackStack("mutableLiveDataFragment")
                    .commit()
        } else {
            supportFragmentManager.popBackStack()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }
    }

    /**
     *Change the button text depending on the back stack entry count i.e if the back stack has some fragments
     * then Show the change the button text to "Remove Fragment" else "Add Fragment"
     */
    private fun setFragmentControlButtonText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            btnControlFragment.text = "Add Fragment"
        } else {
            btnControlFragment.text = "Remove Fragment"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnControlFragment -> {
                launchOrPopFragment()
            }

            R.id.btnGenerate -> {
                liveData.value = (1..9999).random().toString()
            }
        }
    }
}