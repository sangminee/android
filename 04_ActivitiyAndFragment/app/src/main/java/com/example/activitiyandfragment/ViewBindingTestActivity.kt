package com.example.activitiyandfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.activitiyandfragment.databinding.ActivityButtonTestBinding

class ViewBindingTestActivity : AppCompatActivity() {

    private lateinit var binding:ActivityButtonTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_binding_test_activity)

        binding = ActivityButtonTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button1.setOnClickListener {
//            Log.d("viewbinding", "clicked~ button1")
//            Toast.makeText(applicationContext, "clicked~ button1", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.button2.setOnClickListener {
//            Log.d("viewbinding", "clicked~ button2")
//            Toast.makeText(applicationContext, "clicked~ button2", Toast.LENGTH_SHORT).show()
//        }

    }
}