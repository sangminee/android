package com.example.activitiyandfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        Log.d("mylifecycle", "onCreate() 호출 ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("mylifecycle", "onStart() 호출 ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("mylifecycle", "onRestart() 호출 ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("mylifecycle", "onResume() 호출 ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("mylifecycle", "onPause() 호출 ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("mylifecycle", "onStop() 호출 ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mylifecycle", "onDestroy() 호출 ")
    }

}