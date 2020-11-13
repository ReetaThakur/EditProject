package com.xyz.livedatademo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_mutable_live_data.*

class MutableLiveDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mutable_live_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*
        cast activity to MutableLiveDataActivity and access the liveData object
         */
        (activity as MutableLiveDataActivity).liveData.observe(this, observer)
    }

    /**
     * This observer listens to the changes liveData changes defined in the Activity
     */
    private val observer = Observer<String> { value ->
        value?.let {
            tvRandomNumber.text = value
        }
    }
}