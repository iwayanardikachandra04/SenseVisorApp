package com.example.sensevisor.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityHomeBinding
import com.example.sensevisor.utils.DateTimeUtils
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUserInfo()
        setupListeners()
        DateTimeUtils.startDateTimeUpdater(binding.tvTime, binding.tvDate)
    }

    @SuppressLint("SetTextI18n")
    private fun setupUserInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        val username = user?.displayName ?: user?.email ?: "User"
        val userId = user?.uid?.take(8) ?: "Unknown"

        binding.tvWelcome.text = "Welcome, $username"
        binding.tvUserId.text = userId
    }

    private fun setupListeners() {
        binding.btnAnalyze.setOnClickListener {
            Toast.makeText(this, "Analyze clicked!", Toast.LENGTH_SHORT).show()
            goToQuestionOne()
        }

        binding.btnHistory.setOnClickListener {
            goToHistory()
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { _, _ ->
                    showLoading(true)
                    Handler(Looper.getMainLooper()).postDelayed({
                        FirebaseAuth.getInstance().signOut()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }, 2000)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

    }

    private fun goToQuestionOne() {
        startActivity(Intent(this, QuestionOneActivity::class.java))
    }

    private fun goToHistory() {
        startActivity(Intent(this, HistoryActivity::class.java))
    }

    private fun showLoading(show: Boolean) {
        binding.loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
    }

}
