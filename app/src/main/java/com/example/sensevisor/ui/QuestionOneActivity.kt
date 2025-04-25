package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityQuestionOneBinding

class QuestionOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuestionOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPersonal.setOnClickListener {
            goToSecond()
        }

    }

    private fun goToSecond() {
        val intent = Intent(this, QuestionSecondActivity::class.java)
        startActivity(intent)
    }
}
