package com.xyz.livedatademo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.btnControlFragment
import kotlinx.android.synthetic.main.activity_mediator_live_data.*

class MediatorLiveDataActivity : AppCompatActivity(), View.OnClickListener {

    val liveDataA = MutableLiveData<String>()
    val liveDataB = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator_live_data)
        initListeners()
        liveDataA.observe(this, observerA)
        liveDataB.observe(this, observerB)
    }

    private fun initListeners() {
        btnControlFragment.setOnClickListener(this)
        btnGenerateOne.setOnClickListener(this)
        btnGenerateTwo.setOnClickListener(this)
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

            R.id.btnGenerateOne -> {
                liveDataA.value = (1..9999).random().toString()
            }

            R.id.btnGenerateTwo -> {
                liveDataB.value = (1..9999).random().toString()
            }
        }
    }

    /**
     * Launch the fragment if its not already launched, if its already present in the backstack
     * then remove it
     */
    private fun launchOrPopFragment() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MediatorLiveDataFragment(), "")
                .addToBackStack("MediatorLiveDataFragment")
                .commit()
        } else {
            supportFragmentManager.popBackStack()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }
    }

    /**
     * Observer to listen to the  liveDataA changes
     */
    private val observerA = Observer<String> {
        it?.let {
            tvLiveDataOne.text = it
        }
    }

    /**
     * Observer to listen to the liveDataB changes
     */
    private val observerB = Observer<String> {
        it?.let {
            tvLiveDataTwo.text = it
        }
    }

}