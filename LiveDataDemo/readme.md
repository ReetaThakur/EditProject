### MutableLiveData

This is the most simplest LiveData, where it would just get updated and notify it’s observer.

```
// Declaring it
val liveDataA = MutableLiveData<String>()
// Trigger the value change
liveDataA.value = someValue
// Optionally, one could use liveDataA.postValue(value) 
// to get it set on the UI thread
```

Check out the below, even when the Fragment died, the LiveData value generate i.e. 8960 is not causing crashes due to setting on an inactive Fragment.

[![Watch the Video](https://drive.google.com/uc?export=view&id=1OnSeMY-6-LTtpQipuq2V6jP56ZVonoEM)](https://drive.google.com/uc?export=view&id=1gClZs6lvpY2ogAcqIfAWe8bIfvv2BuON)

**Transformations.Map**

Imagine if you are loading data from a repository. Before you pass to the view, you would like to have it modified first.
We could still use LiveData, to pass the data across various entities as below.


![](https://miro.medium.com/max/875/1*74PldJNHq8F5js0FvXtqIQ.png)

We could transform the data from one LiveData and pass on to the other one using Transformations.map() function.

```
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
```
The behavior as shown below, where the data i.e. 5116 is transformed to a new form i.e. A:5116, before passing over to the Fragment.

![](https://miro.medium.com/max/400/1*6cTWqtHlJgnzF701QFtZQg.gif)

**MediatorLiveData**

If you observe the above code, the main interesting part of MediatorLiveData is the ability to add a source to it, and the code that changes the content of the data.
This means we could have multiple LiveData feed to one destination through the MediatorLiveData as below.

![](https://miro.medium.com/max/875/1*5f34-pseY8UySNaEJKTfuA.png)

We could use MediatorLiveData directly as below

```
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
```

![](https://miro.medium.com/max/400/1*eMsbIZVbY36kC2CmuWwOBQ.gif)
