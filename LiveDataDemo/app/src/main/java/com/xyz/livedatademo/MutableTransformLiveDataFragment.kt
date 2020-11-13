package com.xyz.livedatademo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.android.synthetic.main.fragment_mutable_transform_live_data.*

class MutableTransformLiveDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mutable_transform_live_data, container, false)
    }

    private val observer = Observer<String?> {
        it?.let {
            tvRandomNumber.text = it
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Get the live data from the MutableTransformLiveDataActivity
        val liveData = (activity as MutableTransformLiveDataActivity).liveData

        /*
        Once the live data is received from the activity, transform the output to a different output that you need
         */
        val transformLiveData = Transformations.map(liveData)
        {
            it?.let {
                "Masai: $it"
            }
        }
        transformLiveData.observe(this, observer)
    }

}