package com.example.videoapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R

class SignUpWithGoogleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_VideoApp)
        setContentView(R.layout.activity_sign_up_with_google)
    }
}