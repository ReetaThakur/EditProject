package com.xyz.livedatademo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_mediator_live_data.*

class MediatorLiveDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mediator_live_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val liveDataA = (activity as MediatorLiveDataActivity).liveDataA
        val liveDataB = (activity as MediatorLiveDataActivity).liveDataB

        val mediatorLiveData = MediatorLiveData<String>()

        mediatorLiveData.addSource(liveDataA) {
            mediatorLiveData.value = "A: $it"
        }
        mediatorLiveData.addSource(liveDataB) {
            mediatorLiveData.value = "B: $it"
        }
        mediatorLiveData.observe(this,observer)

    }

    private val observer = Observer<String?> {
        it?.let {
            tvRandomNumber.text = it
        }
    }

}