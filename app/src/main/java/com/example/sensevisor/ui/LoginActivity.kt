package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityLoginBinding
import com.example.sensevisor.utils.ViewUtils
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            goToHome()
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewUtils.setupPasswordToggle(binding.etPassword)
        setupListeners()
    }


    private fun setupListeners() {
        binding.tvRegisterNow.setOnClickListener {
            goToRegister()
        }

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.tvForgotPassword.setOnClickListener {
            goToForgotPassword()
        }
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            showToast("Please enter email and password")
            return
        }

        showLoading(true)

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                showLoading(false)
                if (task.isSuccessful) {
                    showToast("Login successful")
                    goToHome()
                } else {
                    showToast("Login failed: ${task.exception?.message}")
                }
            }
    }


    private fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun goToForgotPassword() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    private fun showLoading(show: Boolean) {
        binding.loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
