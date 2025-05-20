package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        setupListeners()
    }

    private fun initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun setupListeners() {
        binding.tvLoginNow.setOnClickListener {
            navigateToLogin()
        }

        binding.btnLogin.setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        val email = binding.etEmail.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (!validateInput(email, username, password, confirmPassword)) return

        registerUser(email, password)
    }

    private fun validateInput(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return when {
            email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                showToast("Please fill all fields")
                false
            }
            password != confirmPassword -> {
                showToast("Passwords do not match")
                false
            }
            else -> true
        }
    }

    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Registration successful")
                    navigateToLogin()
                } else {
                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
