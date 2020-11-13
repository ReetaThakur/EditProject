package com.xyz.coroutine_parallel_jobs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis

/**
 * This activity the use of coroutines to run multiple tasks in parallel
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

    private fun callFakeApi() {
        val startTime = System.currentTimeMillis()
        val parentJob = CoroutineScope(IO).launch {

            val job1 = launch {
                val time1 = measureTimeMillis {
                    val result = callFirstAPI()
                    setTextOnMainThread(result)
                }
                println("Execution time for task1 is $time1 ")
            }

            val job2 = launch {
                val time2 = measureTimeMillis {
                    val result = callSecondAPI()
                    setTextOnMainThread(result)
                }
                println("Execution time for  task2 is $time2 ")
            }
        }

        parentJob.invokeOnCompletion {
            //Called once both the jobs have finished their execution
            println("Total time taken to execute both the taks: ${System.currentTimeMillis() - startTime} ms")
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
     * This will call the second fake API and runs in a background thread
     */
    private suspend fun callSecondAPI(): String {
        println("debug: Second API Runs on the thread ${Thread.currentThread().name}")
        delay(1500)
        return RESULT_2
    }

    /**
     * This method is used to set text view on the main thread
     */
    private suspend fun setTextOnMainThread(result: String) {
        withContext(Dispatchers.Main) {
            val data = "${tvResult.text} \n $result"
            tvResult.text = data
        }
    }
}