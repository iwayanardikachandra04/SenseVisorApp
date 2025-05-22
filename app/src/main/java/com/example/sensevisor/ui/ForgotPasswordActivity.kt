package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSubmit.setOnClickListener {
            val email = binding.tfEmail.editText?.text.toString().trim()
            if (email.isEmpty()) {
                showSnackbar("Please enter your email")
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showSnackbar("Please enter a valid email")
            } else {
                sendPasswordResetEmail(email)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                showSnackbar("Password reset email sent. Please check your inbox.")
                Handler(Looper.getMainLooper()).postDelayed({
                    navigateToLogin()
                }, 2000)
            }
            .addOnFailureListener {
                showSnackbar("Failed to send reset email: ${it.localizedMessage}")
            }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
