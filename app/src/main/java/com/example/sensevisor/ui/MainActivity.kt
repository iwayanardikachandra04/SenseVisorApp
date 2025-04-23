package com.example.sensevisor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sensevisor.databinding.ActivityMainBinding
import com.example.sensevisor.utils.AnimationUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Dashboard Swipe
        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        bottomSheetBehavior = AnimationUtils.setupBottomSheet(
            bottomSheet = binding.bottomCard,
            peekHeight = 150,
            expandOnStart = true,
            hideable = false,
            onSlide = { slideOffset ->

            }
        )
    }

    private fun setupListeners() {
        binding.btnGetStarted.setOnClickListener {
            AnimationUtils.toggleBottomSheet(bottomSheetBehavior)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
