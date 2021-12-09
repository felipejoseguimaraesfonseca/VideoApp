package com.example.videoapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        splashTransaction()
    }

    private fun splashTransaction() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, SignInOrSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
