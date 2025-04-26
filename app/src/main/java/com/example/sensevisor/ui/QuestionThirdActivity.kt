package com.example.sensevisor.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityQuestionThirdBinding

class QuestionThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuestionThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
