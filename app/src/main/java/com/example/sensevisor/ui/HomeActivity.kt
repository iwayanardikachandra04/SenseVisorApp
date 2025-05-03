package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityHomeBinding
import com.example.sensevisor.utils.DateTimeUtils

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnalyze.setOnClickListener {
            Toast.makeText(this, "Analyze clicked!", Toast.LENGTH_SHORT).show()
            goToQuestionOne()
        }

        binding.btnHistory.setOnClickListener {
            goToHistory()
        }

        DateTimeUtils.startDateTimeUpdater(binding.tvTime, binding.tvDate)

    }

    private fun goToQuestionOne() {
        val intent = Intent(this, QuestionOneActivity::class.java)
        startActivity(intent)
    }

    private fun goToHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

}
