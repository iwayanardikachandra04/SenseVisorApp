package com.example.sensevisor.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityResultPredictBinding

class ResultPredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultPredictBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityResultPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
