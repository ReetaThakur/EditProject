package com.xyz.coroutines_sequential_async_await

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

/**
 * This activity demonstrates the use of kotlin coroutines using async and await to perform
 * tasks in background sequentially
 */
class MainActivity : AppCompatActivity() {

    private val RESULT_1 = "RESULT #1"
    private val RESULT_2 = "RESULT #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClick.setOnClickListener {
            callFakeApi()
        }
    }

    /**
     * This will call two fakeAPI's in background thread and the execution happens sequentially
     */
    private fun callFakeApi() {
        CoroutineScope(IO).launch {

            val executionTime = measureTimeMillis {
                //This will run the job1 in the background thread and since await is used it will wait till the execution is finished
                val job1 = async {
                    return@async callFirstAPI()
                }.await()
                println("debug : job1 done")
                setTextOnMainThread(job1)

                //Once the job1 is done this job2 will run in a background thread and wait till the execution is finished
                val job2 = async {
                    return@async callSecondAPI(job1)
                }.await()

                setTextOnMainThread(job2)
            }

            println("Total execution time is $executionTime in ms")
        }
    }

    /**
     * This will call the first fake API in a background thread and returns a result which is used as
     * an input for calling second API
     */
    private suspend fun callFirstAPI(): String {
        println("debug: First API Runs on the thread ${Thread.currentThread().name}")
        delay(1000)
        return RESULT_1
    }

    /**
     * This will call the second fake API with the result received from first API and runs in a background thread
     */
    private suspend fun callSecondAPI(result: String): String {
        println("debug: Second API Runs on the thread ${Thread.currentThread().name}")
        delay(1500)
        if (result.equals(RESULT_1)) {
            return RESULT_2
        }
        return "Invalid Result 1"
    }

    /**
     * This method is used to set text view on the main thread
     */
    private suspend fun setTextOnMainThread(result: String) {
        withContext(Main){
            val data = "${tvResult.text} \n $result"
            tvResult.text = data
        }
    }
}