package com.example.lookup.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lookup.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

    }
}