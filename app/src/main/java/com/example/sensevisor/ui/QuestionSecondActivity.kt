package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityQuestionSecondBinding

class QuestionSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuestionSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            goToThird()
        }
    }

    private fun goToThird() {
        val intent = Intent(this, QuestionThirdActivity::class.java)
        startActivity(intent)
    }
}
