package com.example.videoapp.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth

        splashTransaction()
    }

    private fun splashTransaction() {
        val user = auth.currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null) {
                val navigationActivityIntent =
                    Intent(this@SplashActivity, NavigationActivity::class.java)

                startActivity(navigationActivityIntent)
                finish()
            } else {
                val signInWithActivityIntent =
                    Intent(this@SplashActivity, SignInWithActivity::class.java)

                startActivity(signInWithActivityIntent)
                finish()
            }
        }, 3000)
    }
}