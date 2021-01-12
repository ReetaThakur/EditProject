package com.xyz.customviews

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val countryList = listOf("India", "Aus", "Japan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner.onItemSelectedListener = this

//Creating the ArrayAdapter instance having the bank name list

//Creating the ArrayAdapter instance having the bank name list
        val aa: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//Setting the ArrayAdapter data on the Spinner
//Setting the ArrayAdapter data on the Spinner
        spinner.adapter = aa
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        print("Item clicked")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}